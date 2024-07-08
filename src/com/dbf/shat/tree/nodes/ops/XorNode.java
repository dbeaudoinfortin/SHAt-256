package com.dbf.shat.tree.nodes.ops;

import com.dbf.shat.tree.nodes.Node;
import com.dbf.shat.tree.nodes.NodeBase;
import com.dbf.shat.tree.nodes.NodeType;

public class XorNode extends NodeBase {
	
	private final Node node1;
	private final Node node2;
	
	public XorNode(Node node1, Node node2) {
		this.node1 = node1;
		this.node2 = node2;
	}
	
	@Override
	public int ComputeResult() {
		return node1.getResult() ^ node2.getResult();
	}
	
	@Override
	public NodeType type() {
		return NodeType.xor;
	}

	@Override
	public void toString(StringBuilder sb, String offset) {
		sb.append(System.lineSeparator());
		sb.append(offset);
		offset += "  ";
		sb.append("XOR");
		node1.toString(sb, offset);
		node2.toString(sb, offset);
	}
}
