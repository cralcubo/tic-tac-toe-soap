package com.chris.tictactoe.soap.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PlayerResource extends Resource {
	
	private String shape;
	
	private String status;

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
