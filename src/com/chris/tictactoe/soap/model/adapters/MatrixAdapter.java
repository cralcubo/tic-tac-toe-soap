package com.chris.tictactoe.soap.model.adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class MatrixAdapter extends XmlAdapter<MatrixHashMapType, Map<String, String>> {

	@Override
	public Map<String, String> unmarshal(MatrixHashMapType v) throws Exception {
		Map<String, String> matrix = new HashMap<String, String>();
		
		if(v == null){
			return matrix;
		}
		
		for(MatrixEntry entry : v.getEntry()){
			matrix.put(entry.getKey(), entry.getValue());
		}
		
		return matrix;
	}

	@Override
	public MatrixHashMapType marshal(Map<String, String> v) throws Exception {
		
		MatrixHashMapType mapType = new MatrixHashMapType();
		
		if(v == null){
			return mapType;
		}
		
		List<MatrixEntry> entries = new ArrayList<MatrixEntry>();
		for(String key : v.keySet()){
			MatrixEntry entry = new MatrixEntry();
			entry.setKey(key);
			entry.setValue(v.get(key));
			entries.add(entry);
		}
		
		mapType.setEntry(entries);
		
		return mapType;
	}
	
}
