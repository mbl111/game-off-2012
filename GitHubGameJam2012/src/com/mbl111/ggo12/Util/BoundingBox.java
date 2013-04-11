package com.mbl111.ggo12.Util;

public class BoundingBox {

	public int x0, y0, x1, y1;

	public BoundingBox(int x0, int y0, int x1, int y1) {
		this.x0 = Math.min(x0, x1);
		this.x1 = Math.max(x0, x1);
		this.y0 = Math.min(y0, y1);
		this.y1 = Math.max(y0, y1);
	}

	//RIGHT = MaxX
	//LEFT = MinX
	//UP = MinY
	//DOWN = MaxY
	
	//Left1 > Right2 OR Right1 < Left 2
	public BoundingBox grow(int s) {
		return new BoundingBox(x0 - s, y0 - s, x1 + s, y1 + s);
	}

	public boolean intersects(int xxx0, int yyy0, int xxx1, int yyy1) {
		int xx0 = Math.min(xxx0, xxx1);
		int xx1 = Math.max(xxx0, xxx1);
		int yy0 = Math.min(yyy0, yyy1);
		int yy1 = Math.max(yyy0, yyy1); 
		return !(xx0 > x1 || xx1 < x0 || yy0 > x1 || yy1 < y0);
	}

	public boolean intersects(BoundingBox bb) {
		return intersects(bb.x0, bb.y0, bb.x1, bb.y1);
	}

}
