package com.chris.tictactoe.soap.model;

import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.chris.tictactoe.soap.model.adapters.MatrixAdapter;

@XmlRootElement
public class GameResource extends Resource{
	
	private String status;
	
	private PlayerResource circlePlayer;
	
	private PlayerResource crossPlayer;
	
	private Map<String, String> matrix;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PlayerResource getCirclePlayer() {
		return circlePlayer;
	}

	public void setCirclePlayer(PlayerResource circlePlayer) {
		this.circlePlayer = circlePlayer;
	}

	public PlayerResource getCrossPlayer() {
		return crossPlayer;
	}

	public void setCrossPlayer(PlayerResource crossPlayer) {
		this.crossPlayer = crossPlayer;
	}
	
	@XmlJavaTypeAdapter(MatrixAdapter.class)
	public Map<String, String> getMatrix() {
		return matrix;
	}

	public void setMatrix(Map<String, String> matrix) {
		this.matrix = matrix;
	}
}
