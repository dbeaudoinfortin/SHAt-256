package com.dbf.shat.tree.nodes.ops;

import com.dbf.shat.tree.nodes.NodeBase;
import com.dbf.shat.tree.nodes.NodeType;
import com.dbf.shat.util.Utils;

public class ConstantNode extends NodeBase {

	public ConstantNode(int value) {
		super(value);
	}

	@Override
	public NodeType type() {
		return NodeType.constant;
	}
	
	@Override
	public void toString(StringBuilder sb, String offset) {
		sb.append(System.lineSeparator());
		sb.append(offset);
		sb.append("CONST ");
		sb.append(Utils.intToHexString(getResult()));
	}

	@Override
	public int ComputeResult() {
		//Doesn't apply
		return 0;
	}
}
