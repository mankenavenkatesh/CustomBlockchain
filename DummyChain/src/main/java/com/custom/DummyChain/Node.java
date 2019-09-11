package com.custom.DummyChain;

import java.util.ArrayList;

public class Node {

	public String address;
	
	protected String blockchain;
	private ArrayList<Node> nodes;
	
	public Node(String address, Node connectedNode) {
		this.address = address;
		
		if(connectedNode != null) {
			
		}
	}
}
