package com.dbf.shat.tree.nodes.ops;

import com.dbf.shat.tree.nodes.NodeBase;

public abstract class ShiftNode extends NodeBase {
	protected final int amount;
	
	public ShiftNode(int amount) {
		this.amount = amount;
	}
}
