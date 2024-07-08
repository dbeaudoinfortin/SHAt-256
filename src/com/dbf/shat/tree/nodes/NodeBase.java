package com.dbf.shat.tree.nodes;

public abstract class NodeBase implements Node {
	
	private Integer result;
	
	public NodeBase() {}
	
	public NodeBase(Integer result) {
		this.result = result;
	}
	
	public Integer getResult() {
		if (null == result) {
			result = ComputeResult();
		}
		return result;
	}

	public abstract int ComputeResult();
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		toString(sb, "");
		return sb.toString();
	}

}
