package com.chris.tictactoe.soap.model.transformers;

import com.chris.tictactoe.service.model.Game;
import com.chris.tictactoe.soap.model.GameResource;

public class GameTransformer{

	public static GameResource toGameResource(Game complexObject) {
		GameResource resource = new GameResource();
		
		resource.setId(complexObject.getId());
		resource.setMatrix(complexObject.getGameMatrix());
		
		String status = "";
		if(complexObject.getStatus() != null){
			status = complexObject.getStatus().name();
		}
		
		resource.setStatus(status);
		resource.setCirclePlayer(PlayerTransformer.toPlayerResource(complexObject.getCirclePlayer()));
		resource.setCrossPlayer(PlayerTransformer.toPlayerResource(complexObject.getCrossPlayer()));
		
		return resource;
	}

}
