package com.dbf.shat.util;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public final class Utils {
	public static String[] hexArray = {
		"00","01","02","03","04","05","06","07","08","09","0A","0B","0C","0D","0E","0F",
		"10","11","12","13","14","15","16","17","18","19","1A","1B","1C","1D","1E","1F",
		"20","21","22","23","24","25","26","27","28","29","2A","2B","2C","2D","2E","2F",
		"30","31","32","33","34","35","36","37","38","39","3A","3B","3C","3D","3E","3F",
		"40","41","42","43","44","45","46","47","48","49","4A","4B","4C","4D","4E","4F",
		"50","51","52","53","54","55","56","57","58","59","5A","5B","5C","5D","5E","5F",
		"60","61","62","63","64","65","66","67","68","69","6A","6B","6C","6D","6E","6F",
		"70","71","72","73","74","75","76","77","78","79","7A","7B","7C","7D","7E","7F",
		"80","81","82","83","84","85","86","87","88","89","8A","8B","8C","8D","8E","8F",
		"90","91","92","93","94","95","96","97","98","99","9A","9B","9C","9D","9E","9F",
		"A0","A1","A2","A3","A4","A5","A6","A7","A8","A9","AA","AB","AC","AD","AE","AF",
		"B0","B1","B2","B3","B4","B5","B6","B7","B8","B9","BA","BB","BC","BD","BE","BF",
		"C0","C1","C2","C3","C4","C5","C6","C7","C8","C9","CA","CB","CC","CD","CE","CF",
		"D0","D1","D2","D3","D4","D5","D6","D7","D8","D9","DA","DB","DC","DD","DE","DF",
		"E0","E1","E2","E3","E4","E5","E6","E7","E8","E9","EA","EB","EC","ED","EE","EF",
		"F0","F1","F2","F3","F4","F5","F6","F7","F8","F9","FA","FB","FC","FD","FE","FF"
	};
	
	public static final int[] MAGIC_CONSTANTS = { 0x428a2f98, 0x71374491, 0xb5c0fbcf, 0xe9b5dba5, 0x3956c25b, 0x59f111f1,
			0x923f82a4, 0xab1c5ed5, 0xd807aa98, 0x12835b01, 0x243185be, 0x550c7dc3, 0x72be5d74, 0x80deb1fe, 0x9bdc06a7,
			0xc19bf174, 0xe49b69c1, 0xefbe4786, 0x0fc19dc6, 0x240ca1cc, 0x2de92c6f, 0x4a7484aa, 0x5cb0a9dc, 0x76f988da,
			0x983e5152, 0xa831c66d, 0xb00327c8, 0xbf597fc7, 0xc6e00bf3, 0xd5a79147, 0x06ca6351, 0x14292967, 0x27b70a85,
			0x2e1b2138, 0x4d2c6dfc, 0x53380d13, 0x650a7354, 0x766a0abb, 0x81c2c92e, 0x92722c85, 0xa2bfe8a1, 0xa81a664b,
			0xc24b8b70, 0xc76c51a3, 0xd192e819, 0xd6990624, 0xf40e3585, 0x106aa070, 0x19a4c116, 0x1e376c08, 0x2748774c,
			0x34b0bcb5, 0x391c0cb3, 0x4ed8aa4a, 0x5b9cca4f, 0x682e6ff3, 0x748f82ee, 0x78a5636f, 0x84c87814, 0x8cc70208,
			0x90befffa, 0xa4506ceb, 0xbef9a3f7, 0xc67178f2 };
	
	public static final int[] STATE_INIT_CONST = { 0x6a09e667, 0xbb67ae85, 0x3c6ef372, 0xa54ff53a, 0x510e527f,
			0x9b05688c, 0x1f83d9ab, 0x5be0cd19 };
	
	public static long intsToLong(int int1, int int2) {
		return ((long) int1 << 32) | (int2 & 0xFFFFFFFFL);
	}

	public static String toBinaryString(long value) {
		return leftPad(Long.toBinaryString(value), 64, '0');
	}

	public static String toBinaryString(int value) {
		return leftPad(Integer.toBinaryString(value), 32, '0');
	}

	public static String leftPad(final String str, final int size, final char padChar) {
		final int pads = size - str.length();
		if (pads <= 0) {
			return str; // returns original String when possible
		}
		return repeat(padChar, pads).concat(str);
	}

	public static String repeat(final char ch, final int repeat) {
		if (repeat <= 0) {
			return "";
		}
		final char[] buf = new char[repeat];
		for (int i = repeat - 1; i >= 0; i--) {
			buf[i] = ch;
		}
		return new String(buf);
	}

	public static String bytesToHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder(2 * bytes.length);
		for (int i = 0; i < bytes.length; i++) {
			sb.append(hexArray[0xff & bytes[i]]);
		}
		return sb.toString();
	}

	public static String intToHexString(int value) {
		byte[] bytes = new byte[4];
		ByteArrayAccess.i2bLittle4(value, bytes, 0);
		return bytesToHexString(bytes);
	}

	public static String intsToHexString(int[] ints) {
		byte[] bytes = new byte[ints.length * 4];
		ByteArrayAccess.i2bLittle(ints, 0, bytes, 0, bytes.length);
		return bytesToHexString(bytes);
	}

	public static String longsToHexString(long[] longs) {
		byte[] bytes = new byte[longs.length * 8];
		ByteArrayAccess.l2bBig(longs, 0, bytes, 0, bytes.length);
		return bytesToHexString(bytes);
	}

	public static boolean intsMatch(int[] ints1, int[] ints2) {
		for (int i = 0; i < 8; i++) {
			if (ints1[i] != ints2[i])
				return false;
		}
		return true;
	}

	public static boolean longsMatch(long[] longs1, long[] longs2) {
		for (int i = 0; i < 4; i++) {
			if (longs1[i] != longs2[i])
				return false;
		}
		return true;
	}

	public static boolean allZeros(long[] longs) {
		for (int i = 0; i < 4; i++) {
			if (longs[i] != 0)
				return false;
		}
		return true;
	}

	public static boolean allOnes(long[] longs) {
		for (int i = 0; i < 4; i++) {
			if (longs[i] != -1)
				return false;
		}
		return true;
	}

	public static void copy(long[] longs1, long[] longs2) {
		for (int i = 0; i < 4; i++) {
			longs1[i] = longs2[i];
		}
	}

	public static Unsafe getUnsafe() {
		try {
			Field f = Unsafe.class.getDeclaredField("theUnsafe");
			f.setAccessible(true);
			return (Unsafe) f.get(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
