package com.chris.tictactoe.soap.model.transformers;

import com.chris.tictactoe.service.model.GamePlayer;
import com.chris.tictactoe.soap.model.PlayerResource;

public class PlayerTransformer{

	public static PlayerResource toPlayerResource(GamePlayer complexObject) {
		PlayerResource resource = new PlayerResource();
		
		if(complexObject == null){
			return resource;
		}
		
		resource.setId(complexObject.getId());
		resource.setShape(complexObject.getShape());
		
		String status = "";
		if(complexObject.getStatus() != null){
			status = complexObject.getStatus().name();
		}
		
		resource.setStatus(status);
		
		return resource;
	}


}
