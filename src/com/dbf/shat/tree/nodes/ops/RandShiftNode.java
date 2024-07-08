package com.dbf.shat.tree.nodes.ops;

import java.util.Random;

public abstract class RandShiftNode extends ShiftNode {
	private static final int[] SHIFT_AMOUNTS = new int[]{17,15,19,13,10,7,25,18,14,3,6,26,11,21,25,7,2,30,13,19,22,10};
	private static final int   SHIFT_POSSIBLE_VALUES = SHIFT_AMOUNTS.length;
	
	public RandShiftNode(Random rand) {
		super(SHIFT_AMOUNTS[rand.nextInt(SHIFT_POSSIBLE_VALUES)]);
	}
}
