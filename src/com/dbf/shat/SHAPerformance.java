package com.dbf.shat;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import com.dbf.shat.procedural.SHA2;
import com.dbf.shat.tree.SHA256Tree;

public class SHAPerformance
{	
	public static final int THREADS = 32;
	
	public static void main(String [] args) {
		final String s = "SomeKindOfPasswordString";
		final int iterations = 100_000_000;
		final int iterationsPerThread = iterations / THREADS;
		final byte[] inBytes = (s).getBytes();
		
		System.out.println("Running...");
		try {
			long deltaTime = execute(new Thread() {
				public void run() {
					MessageDigest sha2 = null;
					try {
						sha2 = MessageDigest.getInstance("SHA-256");
					} catch (NoSuchAlgorithmException e) {
						System.out.println("FATAL EXCEPTION:");
						e.printStackTrace();
					}
					for (int i = 0; i < iterationsPerThread;i++) {
						sha2.digest(inBytes);
						sha2.reset();
					}
				}
			});
			System.out.println("Java SHA -> Did " + iterations/1_000_000 + " million iterations in " + deltaTime + "ms. Avg " + ((iterations/deltaTime)/1_000.0) + " MH/s.");
			
			deltaTime = execute(new Thread() {
				public void run() {
					SHA2 sha2 = new SHA2();
					for (int i = 0; i < iterationsPerThread;i++) {
						sha2.digest(inBytes);
						sha2.reset();
					}
				}
			});
			System.out.println("DBF SHA  -> Did " + iterations/1_000_000 + " million iterations in " + deltaTime + "ms. Avg " + ((iterations/deltaTime)/1_000.0) + " MH/s.");
			
			deltaTime = execute(new Thread() {
				public void run() {
					SHA256Tree sha2 = new SHA256Tree();
					for (int i = 0; i < iterationsPerThread/100;i++) {
						sha2.digest(inBytes);
						sha2.reset();
					}
				}
			});
			System.out.println("DBF SHAt -> Did " + iterations/1_000_000_00 + " million iterations in " + deltaTime + "ms. Avg " + ((iterations/deltaTime)/1_000_00.0) + " MH/s.");
		}
		catch (Throwable t) {
			System.out.println("FATAL EXCEPTION:");
			t.printStackTrace();
		}
		
	}
	
	private static long execute(Runnable task) throws InterruptedException {
		ForkJoinPool pool = new ForkJoinPool(THREADS);
		
		long startTime = System.currentTimeMillis();
		for (int t = 0; t < THREADS; t++) {
			pool.execute(task);
		}
		pool.shutdown();
		pool.awaitTermination(1, TimeUnit.HOURS);
		long endTime = System.currentTimeMillis();
		return (endTime-startTime);
	}

}
