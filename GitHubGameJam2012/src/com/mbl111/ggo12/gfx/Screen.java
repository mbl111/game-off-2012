package com.mbl111.ggo12.gfx;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Screen extends Bitmap {

	public BufferedImage image;
	private int xOffset;
	private int yOffset;

	public Screen(int w, int h) {
		super(w, h);
		image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0xFFFFFFFF;
		}

	}

	public void draw(Bitmap bitmap, int x, int y, int data) {
		super.draw(bitmap, x - xOffset, y - yOffset, data);
	}

	public void draw(Bitmap bitmap, int x, int y) {
		super.draw(bitmap, x - xOffset, y - yOffset, 0);
	}

	public void setOffset(int x, int y) {
		this.xOffset = x;
		this.yOffset = y;
	}

	public void drawShaddow(Bitmap bitmap, int x, int y, int col, int data) {
		super.drawShaddow(bitmap, x - xOffset, y - yOffset, col, data);
	}

	public void drawWithWash(Bitmap bitmap, int x, int y, int col, int data, boolean b) {
		if (b) {
			super.drawWithWash(bitmap, x - xOffset, y - yOffset, col, data);
		} else {
			super.drawWithWash(bitmap, x, y, col, data);
		}

	}
}
