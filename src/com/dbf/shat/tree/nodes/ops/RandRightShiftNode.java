package com.dbf.shat.tree.nodes.ops;

import java.util.Random;

import com.dbf.shat.tree.nodes.Node;
import com.dbf.shat.tree.nodes.NodeType;

public class RandRightShiftNode extends RandShiftNode {
	private final Node node1;
	
	public RandRightShiftNode(Node node1, Random rand){
		super(rand);
		this.node1 = node1;
	}
	
	@Override
	public int ComputeResult() {
		return node1.getResult() >>> amount;
	}
	
	@Override
	public NodeType type() {
		return NodeType.rightShift;
	}

	@Override
	public void toString(StringBuilder sb, String offset) {
		sb.append(System.lineSeparator());
		sb.append(offset);
		offset += "  ";
		sb.append("RRSHIFT ");
		sb.append(amount);
		node1.toString(sb, offset);
	}
}
