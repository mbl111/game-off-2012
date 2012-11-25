package com.mbl111.ggo12.Util;

import java.util.Random;

public class SyncRandom {

	public static Random ran = new Random(System.currentTimeMillis());

	public static int nextInt() {
		return ran.nextInt();
	}

	public static int nextInt(int i) {
		return ran.nextInt(i);
	}

	public static double nextGaussian() {
		return ran.nextGaussian();
	}

	public static float nextFloat() {
		return ran.nextFloat();
	}

}
