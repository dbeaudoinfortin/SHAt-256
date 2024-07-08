package com.dbf.shat.tree.nodes.ops;

import com.dbf.shat.tree.nodes.Node;
import com.dbf.shat.tree.nodes.NodeBase;
import com.dbf.shat.tree.nodes.NodeType;

public class NotNode extends NodeBase {
	private final Node node1;
	
	public NotNode(Node node1) {
		this.node1 = node1;
	}
	
	@Override
	public int ComputeResult() {
		return ~node1.getResult();
	}

	@Override
	public NodeType type() {
		return NodeType.not;
	}
	
	@Override
	public void toString(StringBuilder sb, String offset) {
		sb.append(System.lineSeparator());
		sb.append(offset);
		offset += "  ";
		sb.append("NOT");
		node1.toString(sb, offset);
	}
}
