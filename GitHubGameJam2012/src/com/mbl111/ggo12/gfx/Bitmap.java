package com.mbl111.ggo12.gfx;

import java.util.Arrays;

public class Bitmap {

	public static final byte X_MIRROR = 0x01;
	public static final byte Y_MIRROR = 0x02;

	public int w, h;
	public int[] pixels;
	private int transparent = 0xFFFF00FF;

	public Bitmap(int w, int h) {
		this.w = w;
		this.h = h;
		pixels = new int[w * h];
	}

	public Bitmap(int w, int h, int color) {
		this.w = w;
		this.h = h;
		pixels = new int[w * h];
		clear(color);
	}

	public void clear(int color) {
		Arrays.fill(pixels, color);
	}

	public void setTransparent(int col) {
		transparent = col;
	}

	public void draw(Bitmap bitmap, int x, int y, int data) {
		for (int yy = 0; yy < bitmap.h; yy++) {
			int ty = y + yy;
			if (ty < 0 | ty >= h) continue;
			int ys = yy;
			if ((Y_MIRROR & data) > 0) ys = bitmap.h - 1 - yy;
			for (int xx = 0; xx < bitmap.w; xx++) {
				int tx = x + xx;
				if (tx < 0 | tx >= w) continue;
				int xs = xx;
				if ((X_MIRROR & data) > 0) xs = bitmap.w - 1 - xx;

				int col = bitmap.pixels[xs + ys * bitmap.w];
				if (col != transparent) pixels[tx + ty * w] = col;
			}
		}
	}

	public void fill(int color, int x, int y, int width, int height, int data) {
		for (int yy = 0; yy < height; yy++) {
			int ty = y + yy;
			if (ty < 0 | ty >= h) continue;
			int ys = yy;
			if ((Y_MIRROR & data) > 0) ys = height - 1 - yy;
			for (int xx = 0; xx < width; xx++) {
				int tx = x + xx;
				if (tx < 0 | tx >= w) continue;
				int xs = xx;
				if ((X_MIRROR & data) > 0) xs = width - 1 - xx;
				pixels[tx + ty * w] = color;
			}
		}
	}

	public void drawWithWash(Bitmap bitmap, int x, int y, int col, int data) {
		for (int yy = 0; yy < bitmap.h; yy++) {
			int ty = y + yy;
			if (ty < 0 | ty >= h) continue;
			int ys = yy;
			if ((Y_MIRROR & data) > 0) ys = bitmap.h - 1 - yy;
			for (int xx = 0; xx < bitmap.w; xx++) {
				int tx = x + xx;
				if (tx < 0 | tx >= w) continue;
				int xs = xx;
				if ((X_MIRROR & data) > 0) xs = bitmap.w - 1 - xx;

				int c = bitmap.pixels[xs + ys * bitmap.w];
				if (c != transparent) pixels[tx + ty * w] = c & col;
			}
		}
	}

	public void drawShaddow(Bitmap bitmap, int x, int y, int col, int data) {
		for (int yy = 0; yy < bitmap.h; yy++) {
			int ty = y + yy;
			if (ty < 0 | ty >= h) continue;
			int ys = yy;
			if ((Y_MIRROR & data) > 0) ys = bitmap.h - 1 - yy;
			for (int xx = 0; xx < bitmap.w; xx++) {
				int tx = x + xx;
				if (tx < 0 | tx >= w) continue;
				int xs = xx;
				if ((X_MIRROR & data) > 0) xs = bitmap.w - 1 - xx;
				int color = bitmap.pixels[xs + ys * bitmap.w];
				if (color != transparent) pixels[tx + ty * w] = col;
			}
		}
	}

}
