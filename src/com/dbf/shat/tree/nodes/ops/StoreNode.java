package com.dbf.shat.tree.nodes.ops;

import com.dbf.shat.tree.nodes.MemStore;
import com.dbf.shat.tree.nodes.MemStoreNode;
import com.dbf.shat.tree.nodes.Node;
import com.dbf.shat.tree.nodes.NodeType;

public class StoreNode extends MemStoreNode {
	
	private final Node node1;
	private final int position;
	
	public StoreNode(int position, MemStore memStore, Node node1) {
		super(memStore);
		this.position = position;
		this.node1 = node1;
	}
	
	@Override
	public int ComputeResult() {
		int node1Value = node1.getResult();
		memStore.store(node1Value, position);
		return node1Value;
	}
	
	@Override
	public NodeType type() {
		return NodeType.store;
	}

	@Override
	public void toString(StringBuilder sb, String offset) {
		sb.append(System.lineSeparator());
		sb.append(offset);
		offset += "  ";
		sb.append("STORE ");
		sb.append(position);
		node1.toString(sb, offset);
	}
}
