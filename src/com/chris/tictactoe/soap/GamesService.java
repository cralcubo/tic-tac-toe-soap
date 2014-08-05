package com.chris.tictactoe.soap;

import java.util.ArrayList;
import java.util.Collection;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.chris.tictactoe.service.business.GameManagerImpl;
import com.chris.tictactoe.service.dao.DAO;
import com.chris.tictactoe.service.dao.GameManagerStaticRepository;
import com.chris.tictactoe.service.dao.GameStaticRepository;
import com.chris.tictactoe.service.dao.PlayerStaticRepository;
import com.chris.tictactoe.service.model.Game;
import com.chris.tictactoe.service.model.GamePlayer;
import com.chris.tictactoe.soap.model.GameResource;
import com.chris.tictactoe.soap.model.transformers.GameTransformer;

@WebService
public class GamesService {
	
	private DAO<Game> gameRepository;
	private DAO<GamePlayer> playerRepository;
	private DAO<GameManagerImpl> managerRepository;
	
	public GamesService(){
		gameRepository = GameStaticRepository.getInstance();
		playerRepository = PlayerStaticRepository.getInstance();
		managerRepository = GameManagerStaticRepository.getInstance();
	}
	
	@WebMethod
	public void createGame(){
		Game game = new Game();
		String id = gameRepository.save(game);
		game.setId(id);
		
		GamePlayer circlePlayer = new GamePlayer();
		circlePlayer.setId(String.format("circle-%s", id));
		circlePlayer.setShape("O");
		game.setCirclePlayer(circlePlayer);
		playerRepository.save(circlePlayer);
		
		GamePlayer crossPlayer = new GamePlayer();
		crossPlayer.setId(String.format("cross-%s", id));
		crossPlayer.setShape("X");
		game.setCrossPlayer(crossPlayer);
		playerRepository.save(crossPlayer);
		
		GameManagerImpl manager = new GameManagerImpl(game);
		manager.setId(id);
		
		managerRepository.save(manager);
	}
	
	@WebMethod
	@WebResult(partName="game", name="game")
	public GameResource getGame(String id){
		return GameTransformer.toGameResource(gameRepository.get(id));
	}
	
	@WebMethod
	@WebResult(partName="games", name="games")
	public Collection<GameResource> getGames(){
		Collection<GameResource> resources = new ArrayList<GameResource>();
		for(Game game : gameRepository.getAll()){
			resources.add(GameTransformer.toGameResource(game));
		}
		
		return resources;
	}

}
