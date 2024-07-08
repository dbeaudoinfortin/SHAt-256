package com.dbf.shat.tree.nodes.ops;

import java.util.Random;

import com.dbf.shat.tree.nodes.NodeType;
import com.dbf.shat.util.Utils;

public class MagicConstantNode extends ConstantNode {
	
	private static final int[] MAGIC_CONSTANTS = new int[]{1116352408, 1899447441, -1245643825, -373957723, 961987163,
			1508970993, -1841331548, -1424204075, -670586216, 310598401, 607225278, 1426881987, 1925078388, -2132889090,
			-1680079193, -1046744716, -459576895, -272742522, 264347078, 604807628, 770255983, 1249150122, 1555081692,
			1996064986, -1740746414, -1473132947, -1341970488, -1084653625, -958395405, -710438585, 113926993,
			338241895, 666307205, 773529912, 1294757372, 1396182291, 1695183700, 1986661051, -2117940946, -1838011259,
			-1564481375, -1474664885, -1035236496, -949202525, -778901479, -694614492, -200395387, 275423344, 430227734,
			506948616, 659060556, 883997877, 958139571, 1322822218, 1537002063, 1747873779, 1955562222, 2024104815,
			-2067236844, -1933114872, -1866530822, -1538233109, -1090935817, -965641998, 1779033703, 1150833019, 1013904242,
			1521486534, 1359893119, 1694144372, 528734635, 1541459225};
	
	private static final int CONSTANT_DISTRIBUTION_LENGTH = MAGIC_CONSTANTS.length * 2;
	private static final int DISTRIBUTION_ZERO_CUTOFF = (int) (MAGIC_CONSTANTS.length * 1.5);
	
	public MagicConstantNode(Random rand) {
		super(getRandomMagicConstant(rand));
	}
	
	private static int getRandomMagicConstant(Random rand) {
		int random = rand.nextInt(CONSTANT_DISTRIBUTION_LENGTH);
		if(random < MAGIC_CONSTANTS.length)
			return MAGIC_CONSTANTS[random];
		else if(random < DISTRIBUTION_ZERO_CUTOFF)
			return 0;
		else
			return -1;
	}
	
	@Override
	public NodeType type() {
		return NodeType.magicConstant;
	}

	@Override
	public void toString(StringBuilder sb, String offset) {
		sb.append(System.lineSeparator());
		sb.append(offset);
		sb.append("MCONST ");
		sb.append(Utils.intToHexString(getResult()));
	}
}
