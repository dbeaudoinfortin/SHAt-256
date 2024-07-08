package com.dbf.shat.tree.nodes;

public abstract class MemStoreNode extends NodeBase {
	
	protected MemStore memStore;
	
	public MemStoreNode(MemStore memStore) {
		this.setMemStore(memStore);
	}

	public MemStore getMemStore() {
		return memStore;
	}

	public void setMemStore(MemStore memStore) {
		this.memStore = memStore;
	}
}
