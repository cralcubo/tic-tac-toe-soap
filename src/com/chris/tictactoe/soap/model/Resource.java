package com.chris.tictactoe.soap.model;

import javax.xml.bind.annotation.XmlAttribute;



public class Resource{

	private String id;
	
	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
