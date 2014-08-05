package com.chris.tictactoe.soap;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chris.tictactoe.game.exceptions.GameOverException;
import com.chris.tictactoe.game.exceptions.NoPlayersRegisteredException;
import com.chris.tictactoe.game.exceptions.PositionOccupiedException;
import com.chris.tictactoe.game.exceptions.WaitYourTurnException;
import com.chris.tictactoe.service.business.GameManager;
import com.chris.tictactoe.service.business.GameManagerImpl;
import com.chris.tictactoe.service.dao.DAO;
import com.chris.tictactoe.service.dao.GameManagerStaticRepository;
import com.chris.tictactoe.service.dao.PlayerStaticRepository;
import com.chris.tictactoe.service.exceptions.GameNotStartedException;
import com.chris.tictactoe.service.exceptions.UnsupportedCoordinateException;
import com.chris.tictactoe.service.model.GamePlayer;
import com.chris.tictactoe.service.model.PlayerShape;
import com.chris.tictactoe.soap.model.PlayerResource;
import com.chris.tictactoe.soap.model.transformers.PlayerTransformer;

@WebService(name="PlayerService", portName="PlayerPort", serviceName="PlayerService")
public class PlayersService {
	private static final Logger LOG = LoggerFactory.getLogger(PlayersService.class);
	
	private DAO<GamePlayer> playersRepository = PlayerStaticRepository.getInstance();
	private DAO<GameManagerImpl> managerRepository = GameManagerStaticRepository.getInstance();
	
	@WebMethod
	@WebResult(partName="player", name="player")
	public PlayerResource getPlayer(@WebParam(partName="playerId", name="playerId") @XmlElement(required=true) String id){
		GamePlayer gamePlayer = playersRepository.get(id);
		return PlayerTransformer.toPlayerResource(gamePlayer);
	}
	
	@WebMethod
	@WebResult(partName="players", name="players")
	public List<PlayerResource> getPlayers(){
		List<PlayerResource> resources = new ArrayList<PlayerResource>();
		
		for(GamePlayer gamePlayer : playersRepository.getAll()){
			resources.add(PlayerTransformer.toPlayerResource(gamePlayer));
		}
		
		return resources;
	}
	
	@WebMethod
	public void play(@WebParam(partName="playerId", name="playerId") @XmlElement(required=true) String id, 
					 @WebParam(partName="coordinate", name="coordinate") @XmlElement(required=true) String coordinate) throws UnsupportedCoordinateException, PositionOccupiedException, WaitYourTurnException, GameOverException, GameNotStartedException{
		GamePlayer player = playersRepository.get(id);
		
		String[] idSplitted = id.split("-");
		String gameId = idSplitted[1];
		
		GameManager manager = managerRepository.get(gameId);
		try{
			if(player.getShape().equals(PlayerShape.CIRCLE.getShape())){
				manager.playCircle(coordinate);
			}else{
				manager.playCross(coordinate);
			}
		} catch(NoPlayersRegisteredException e){
			LOG.error("Players need to be registered before the game is started, this is supposed to be done when a new game is created, check that method!", e);
		}
	}

}
