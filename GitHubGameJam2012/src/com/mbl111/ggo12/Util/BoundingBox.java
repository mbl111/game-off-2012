package com.mbl111.ggo12.Util;

public class BoundingBox {

	public int x0, y0, x1, y1;

	public BoundingBox(int x0, int y0, int x1, int y1) {
		this.x0 = x0;
		this.x1 = x1;
		this.y0 = y0;
		this.y1 = y1;
	}

	public BoundingBox grow(int s) {
		return new BoundingBox(x0 - s, y0 - s, x1 + s, y1 + s);
	}

	public boolean intersects(int xx0, int yy0, int xx1, int yy1) {
		return xx0 >= x0 || yy0 >= y0 || x1 <= xx1 || y1 <= yy1;
	}

	public boolean intersects(BoundingBox bb) {
		return intersects(bb.x0, bb.y0, bb.x1, bb.y1);
	}

}
