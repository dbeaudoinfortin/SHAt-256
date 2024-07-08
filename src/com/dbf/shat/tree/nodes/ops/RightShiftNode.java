package com.dbf.shat.tree.nodes.ops;

import com.dbf.shat.tree.nodes.Node;
import com.dbf.shat.tree.nodes.NodeType;

public class RightShiftNode extends ShiftNode {
	
	private final Node node1;
	
	public RightShiftNode(Node node1, int amount) {
		super(amount);
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
		sb.append("RSHIFT ");
		sb.append(amount);
		node1.toString(sb, offset);
	}
}