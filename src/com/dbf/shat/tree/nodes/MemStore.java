package com.dbf.shat.tree.nodes;

/**
 * Explicitly NOT thread-safe
 *
 */
public class MemStore {
		
	private int[] storageSlots;
	
	public MemStore(int length) {
		storageSlots = new int[length];
	}
		
	public void store(int val, int index) {
		storageSlots[index] = val;
	}
	
	public int load(int index) {
		return storageSlots[index];
	}
	
	public int[] getRawArray() {
		return storageSlots;
	}
	
	public void setRawArray(int[] array) {
		storageSlots = array;
	}
}
