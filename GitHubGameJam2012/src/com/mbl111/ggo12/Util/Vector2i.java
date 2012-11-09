package com.mbl111.ggo12.Util;

public class Vector2i {

	public int x, y;

	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int lengthSq() {
		return (x * x) + (y * y);
	}

	public double length() {
		return Math.sqrt(lengthSq());
	}

	public Vector2i clone() {
		return new Vector2i(x, y);
	}

	public String toString() {
		return "[" + x + "," + y + "]";
	}

}
