package com.dbf.shat.tree;


import com.dbf.shat.util.ByteArrayAccess;
import com.dbf.shat.util.DigestBase;
import com.dbf.shat.util.Utils;

import java.util.Arrays;

import com.dbf.shat.tree.nodes.MemStore;
import com.dbf.shat.tree.nodes.Node;
import com.dbf.shat.tree.nodes.ops.AddNode;
import com.dbf.shat.tree.nodes.ops.AndNode;
import com.dbf.shat.tree.nodes.ops.ArrayNode;
import com.dbf.shat.tree.nodes.ops.ConstantNode;
import com.dbf.shat.tree.nodes.ops.LeftShiftNode;
import com.dbf.shat.tree.nodes.ops.LoadNode;
import com.dbf.shat.tree.nodes.ops.NotNode;
import com.dbf.shat.tree.nodes.ops.OrNode;
import com.dbf.shat.tree.nodes.ops.RightShiftNode;
import com.dbf.shat.tree.nodes.ops.XorNode;

public class SHA256Tree extends DigestBase {
	
	private static final int COMPRESSION_ITERATIONS = 64;
	
	private ArrayNode treeRoot;
	private MemStore W = new MemStore(64);
	private MemStore state = new MemStore(8);

	public SHA256Tree() {
		super("SHAt-256", 32, 64);
		implReset();
	}
	
	private ArrayNode buildCompressLoop() {
		
		Node a = new LoadNode(0, state);
		Node b = new LoadNode(1, state);
		Node c = new LoadNode(2, state);
		Node d = new LoadNode(3, state);
		Node e = new LoadNode(4, state);
		Node f = new LoadNode(5, state);
		Node g = new LoadNode(6, state);
		Node h = new LoadNode(7, state);
		
		for (int round = 0; round < COMPRESSION_ITERATIONS; round++) {
			Node s0  = buildSigma0Node(a);
			Node s1  = buildSigma1Node(e);
			
			Node ch  = buildChNode(e, f, g);
			Node maj = buildMajNode(a, b, c);
			
			Node t1 =  buildT1Node(h, s1, ch, round);
			Node t2 =  buildT2Node(s0, maj);
					
			h = g;
			g = f;
            f = e;
            e = new AddNode(d, t1);
            d = c;
            c = b;
            b = a;
            a = new AddNode(t1, t2);
		}
		
		Node s0 = new AddNode(new LoadNode(0, state), a);
		Node s1 = new AddNode(new LoadNode(1, state), b);
		Node s2 = new AddNode(new LoadNode(2, state), c);
		Node s3 = new AddNode(new LoadNode(3, state), d);
		Node s4 = new AddNode(new LoadNode(4, state), e);
		Node s5 = new AddNode(new LoadNode(5, state), f);
		Node s6 = new AddNode(new LoadNode(6, state), g);
		Node s7 = new AddNode(new LoadNode(7, state), h);
		
		return new ArrayNode(s0, s1, s2, s3, s4, s5, s6, s7);
	}

	private Node buildT1Node(Node H, Node S1, Node ch, int round) {
		Node loadW = new LoadNode(round, W);
		Node k = new ConstantNode(Utils.MAGIC_CONSTANTS[round]);
		return new AddNode(H, new  AddNode(S1, new AddNode(ch, new  AddNode(k, loadW))));
	}
	
	private Node buildT2Node(Node S0, Node maj) {
		return new AddNode(S0,maj);
	}
	
	private Node buildChNode(Node E, Node F, Node G) {
		//(E & F) ^ (!E & G)
		Node EandF    = new AndNode(E, F);
		Node notE     = new NotNode(E);
		Node notEAndG = new AndNode(notE, G);
		return new XorNode(EandF, notEAndG);
	}
	
	private Node buildMajNode(Node A, Node B, Node C) {
		//(A & B) ^ (A & C) ^ (B & C)
		Node aAndB = new AndNode(A, B);
		Node aAndC = new AndNode(A, C);
		Node bAndC = new AndNode(B, C);
		return new XorNode(bAndC, new XorNode(aAndC, aAndB));
	}
	
	private Node buildSigma0Node(Node A) {
		//(A right_rotate 2) ^ (A right_rotate 13) ^ (A right_rotate 22)
		Node aRightRotate2  = buildRightRotateNode(A, 2);
		Node aRightRotate13 = buildRightRotateNode(A, 13);
		Node aRightRotate22 = buildRightRotateNode(A, 22);
		return new XorNode(aRightRotate2, new XorNode(aRightRotate13, aRightRotate22));
	}
	
	private Node buildSigma1Node(Node E) {
		//(E right_rotate 6) ^ (E right_rotate 11) ^ (E right_rotate 25)
		Node eRightRotate6  = buildRightRotateNode(E, 6);
		Node eRightRotate11 = buildRightRotateNode(E, 11);
		Node eRightRotate25 = buildRightRotateNode(E, 25);
		return new XorNode(eRightRotate6, new XorNode(eRightRotate11, eRightRotate25));
	}
	
	private Node buildRightRotateNode(Node startValue, int amount) {
		if(amount > 31) throw new IllegalArgumentException("Cannot rotate more than 31.");
		Node rightShift  = new RightShiftNode(startValue, amount);
		Node leftShift   = new LeftShiftNode (startValue, 32-amount);
		return new OrNode(rightShift, leftShift);
	}
	
	public byte[] digest(byte[] input)
    {
    	engineUpdate(input, 0, input.length);
    	return engineDigest();
    }
	
	/**
     * Resets the digest for further use.
     */
    public void reset() {
        engineReset();
    }

	@Override
	protected void implCompress(byte[] buf, int ofs) {
		//Load the initial state into W
		ByteArrayAccess.b2iBig64(buf, ofs, W.getRawArray());

		for (int t = 16; t < COMPRESSION_ITERATIONS; t++) {
			W.getRawArray()[t] = (((W.getRawArray()[t - 02] >>> 17) | (W.getRawArray()[t - 2] << 15))
					^ ((W.getRawArray()[t - 02] >>> 19) | (W.getRawArray()[t - 02] << 13))
					^ (W.getRawArray()[t - 02] >>> 10))
					+ W.getRawArray()[t - 7]
					+ (((W.getRawArray()[t - 15] >>> 7) | (W.getRawArray()[t - 15] << 25))
							^ ((W.getRawArray()[t - 15] >>> 18) | (W.getRawArray()[t - 15] << 14))
							^ (W.getRawArray()[t - 15] >>> 3))
					+ W.getRawArray()[t - 16];
		}
		
		//TODO: Clear the cached values but don't rebuild the tree for every block of 512 bytes
		treeRoot = buildCompressLoop();
		state.setRawArray(treeRoot.getResults());
	}

	@Override
	protected void implDigest(byte[] out, int ofs) {
		long bitsProcessed = bytesProcessed << 3;

		int index = (int) bytesProcessed & 0x3f;
		int padLen = (index < 56) ? (56 - index) : (120 - index);
		engineUpdate(padding, 0, padLen);

		ByteArrayAccess.i2bBig4((int) (bitsProcessed >>> 32), buffer, 56);
		ByteArrayAccess.i2bBig4((int) bitsProcessed, buffer, 60);
		implCompress(buffer, 0);

		ByteArrayAccess.i2bBig(state.getRawArray(), 0, out, ofs, 32);
	}

	@Override
	protected void implReset() {
		state.setRawArray(Arrays.copyOf(Utils.STATE_INIT_CONST, 8));
	}

	@Override
	public Object clone() {
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(2000);
		treeRoot.toString(sb,"");
		return sb.toString();
	}
}
