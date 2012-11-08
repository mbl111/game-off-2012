package com.mbl111.ggo12.gfx;

public class Font {

	public static String characters = //
	"ABCDEFGHIJKLMNOP" + //
			"QRSTUVWXYZ";

	public static void draw(String msg, int x, int y, int color, int data, Screen screen) {
		int length = msg.length();
		msg = msg.toUpperCase();
		for (int i = 0; i < length; i++) {
			String c = msg.charAt(i) + "";
			int icon = characters.indexOf(c);
			screen.drawWithWash(Art.FONT[icon / 16][icon & 16], (i * 16) + x, y, color, data, false);
		}
	}

	public static void draw(String msg, int x, int y, int color, Screen screen) {
		draw(msg, x, y, color, 0, screen);
	}

	public static void drawToLevel(String msg, int x, int y, int color, int data, Screen screen) {
		int length = msg.length();
		msg = msg.toUpperCase();
		for (int i = 0; i < length; i++) {
			String c = msg.charAt(i) + "";
			int icon = characters.indexOf(c);
			screen.drawWithWash(Art.FONT[icon / 16][icon & 16], (i * 16) + x, y, color, data, true);
		}
	}

	public static void drawToLevel(String msg, int x, int y, int color, Screen screen) {
		drawToLevel(msg, x, y, color, 0, screen);
	}

}
