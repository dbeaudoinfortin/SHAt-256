package com.dbf.shat.tree.nodes.ops;

import com.dbf.shat.tree.nodes.MemStore;
import com.dbf.shat.tree.nodes.MemStoreNode;
import com.dbf.shat.tree.nodes.NodeType;

public class LoadNode extends MemStoreNode {

	private final int position;
	
	public LoadNode(int position, MemStore memStore) {
		super(memStore);
		this.position = position;
	}
	
	@Override
	public Integer getResult() {
		return memStore.load(position);
	}
	
	@Override
	public NodeType type() {
		return NodeType.load;
	}

	@Override
	public void toString(StringBuilder sb, String offset) {
		sb.append(System.lineSeparator());
		sb.append(offset);
		sb.append("LOAD ");
		sb.append(position);
	}

	@Override
	public int ComputeResult() {
		//Ignored.
		//Always loaded fresh from memory, not pre-computed.
		return 0;
	}
}
