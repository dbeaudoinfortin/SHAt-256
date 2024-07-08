/*
 * Copyright (c) 2002, 2013, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

/**
 * Copied and partially rewritten from sun.security.provider.SHA2.java of JDK 1.8
 */

package com.dbf.shat.procedural;

import com.dbf.shat.util.ByteArrayAccess;
import com.dbf.shat.util.DigestBase;
import com.dbf.shat.util.Utils;

public final class SHA2 extends DigestBase
{ 
    
    private static final int ITERATIONS = 64;

    // buffer used by implCompress()
    private final int[] W;

    // state of this object
    private final int[] state;

    /**
     * Creates a new SHA object.
     */
    public SHA2() {
        super("SHA-256", 32, 64);
        state = new int[8];
        W = new int[64];
        implReset();
    }

    /**
     * Creates a SHA2 object.with state (for cloning)
     */
    private SHA2(SHA2 base) {
        super(base);
        this.state = base.state.clone();
        this.W = new int[64];
    }

    public Object clone() {
        return new SHA2(this);
    }

    /**
     * Resets the buffers and hash value to start a new hash.
     */
    public void implReset() {
        state[0] = 0x6a09e667;
        state[1] = 0xbb67ae85;
        state[2] = 0x3c6ef372;
        state[3] = 0xa54ff53a;
        state[4] = 0x510e527f;
        state[5] = 0x9b05688c;
        state[6] = 0x1f83d9ab;
        state[7] = 0x5be0cd19;
    }

    protected void implDigest(byte[] out, int ofs) {
        long bitsProcessed = bytesProcessed << 3;

        int index = (int)bytesProcessed & 0x3f;
        int padLen = (index < 56) ? (56 - index) : (120 - index);
        engineUpdate(padding, 0, padLen);

        ByteArrayAccess.i2bBig4((int)(bitsProcessed >>> 32), buffer, 56);
        ByteArrayAccess.i2bBig4((int)bitsProcessed, buffer, 60);
        implCompress(buffer, 0);

        ByteArrayAccess.i2bBig(state, 0, out, ofs, 32);
    }

    /**
     * Process the current block to update the state variable state.
     */
    protected void implCompress(byte[] buf, int ofs) {
    	ByteArrayAccess.b2iBig64(buf, ofs, W);
        //16 STORE
        
        
        // The first 16 ints are from the byte stream, compute the rest of the W[]'s
        for (int t = 16; t < ITERATIONS; t++) {
        	W[t] = (  ((W[t-02] >>> 17) | (W[t-2] << 15)) ^ ((W[t-02] >>> 19) | (W[t-02] << 13)) ^ (W[t-02] >>> 10) )
        		 + W[t-7] 
        		 + (((W[t-15] >>> 7) | (W[t-15] << 25)) ^ ((W[t-15] >>> 18) | (W[t-15] << 14)) ^ (W[t-15] >>> 3))
        		 + W[t-16];
        	//6 right shift
        	//4 left shift
        	//4 XOR
        	//4 OR
        	//3 ADD
        	//1 STORE
        	//12 LOAD
        	
        } //48 times

        //288 right shift
    	//192 left shift
    	//192 XOR
    	//192 OR
    	//144 ADD
    	//48 STORE
    	//576 LOAD
        
        int a = state[0];
        int b = state[1];
        int c = state[2];
        int d = state[3];
        int e = state[4];
        int f = state[5];
        int g = state[6];
        int h = state[7];
        //8 store
        //8 magic const
       
        for (int i = 0; i < ITERATIONS; i++) {
        	
        	int T1 = h + 
        			(((e >>> 06) | (e << 26))
        		   ^ ((e >>> 11) | (e << 21)) 
        		   ^ ((e >>> 25) | (e << 07)))
        		   + ((e   & f) ^ 
        	         ((~e) & g)) 
        		   + Utils.MAGIC_CONSTANTS[i] 
        	       + W[i];
        	//3 right shift
        	//3 left shift
        	//3 or
        	//3 xor
        	//4 add
        	//2 and
        	//1 not
        	//1 magic const
        	//12 load
        	//1 store
        	
        	int T2 = (((a >>> 02) | (a << 30))
        		      ^ ((a >>> 13) | (a << 19))
        		      ^ ((a >>> 22) | (a << 10)))
        			+ ((a & b)
        		      ^ (a & c)
        		      ^ (b & c));
        	//3 right shift
        	//3 left shift
        	//3 or
        	//4 xor
        	//1 add
        	//3 and
        	//12 load
        	//1 store
        	
            h = g;
            g = f;
            f = e;
            e = d + T1;
            d = c;
            c = b;
            b = a;
            a =  T1 + T2;
         //2 add
         //8 store
         //10 load
      
        //SUB-TOTAL
        //6 right shift
    	//6 left shift
    	//6 or
    	//7 xor
    	//5 add
    	//5 and
    	//1 not
    	//1 magic const
    	//34 load
    	//10 store
            
        } //64 times
        
        //384 right shift
    	//384 left shift
    	//384 or
    	//448 xor
    	//320 add
    	//320 and
    	//64 not
    	//64 magic const
    	//2176 load
    	//640 store
        
        state[0] += a;
        state[1] += b;
        state[2] += c;
        state[3] += d;
        state[4] += e;
        state[5] += f;
        state[6] += g;
        state[7] += h; 
        //8 store
        //8 load
        //8 add
        
        
      //GRAND-TOTAL:
      //720 STORE
      //672 right shift
      //576 left shift
      //640 XOR
      //576 or
      //472 add
      //320 and
      //64 not
      //72 magic const
      //2760 LOAD
    }
    
    /**
     * Performs a final update on the digest using the specified array
     * of bytes, then completes the digest computation. That is, this
     * method first calls {@link #update(byte[]) update(input)},
     * passing the <i>input</i> array to the <code>update</code> method,
     * then calls {@link #digest() digest()}.
     *
     * @param input the input to be updated before the digest is
     * completed.
     *
     * @return the array of bytes for the resulting hash value.
     */
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
  
}
