package com.dbf.shat.tree.nodes.ops;

import com.dbf.shat.tree.nodes.Node;
import com.dbf.shat.tree.nodes.NodeType;

public class ArrayNode implements Node {
	
	private final Node[] nodes;
	
	public ArrayNode(Node... nodes) {
		this.nodes = nodes;
	}
	
	@Override
	public NodeType type() {
		return NodeType.array;
	}

	@Override
	public Integer getResult() {
		for (int i = 0; i < nodes.length; i++) {
			nodes[i].getResult();
		}
		return nodes.length;
	}
	
	public int[] getResults() {
		int[] results = new int[nodes.length];
		for (int i = 0; i < nodes.length; i++) {
			results[i] = nodes[i].getResult();
		}
		return results;
	}
	
	@Override
	public void toString(StringBuilder sb, String offset) {
		sb.append(System.lineSeparator());
		sb.append(offset);
		offset += "  ";
		sb.append("ARRAY");
		for (int i = 0; i < nodes.length; i++) {
			nodes[i].toString(sb, offset);
		}
	}
}
