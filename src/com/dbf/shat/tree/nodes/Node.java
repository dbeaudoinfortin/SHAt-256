package com.dbf.shat.tree.nodes;

public interface Node {
	
	public Integer getResult();
	
	public NodeType type();
	
	public void toString(StringBuilder sb, String offset);
}
