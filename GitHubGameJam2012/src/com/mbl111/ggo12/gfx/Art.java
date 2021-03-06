package com.mbl111.ggo12.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.mbl111.ggo12.Game;

public class Art {

	public static final Bitmap[][] PLAYERS = load(16, 16, "/entity/player.png");
	public static final Bitmap[][] TILES = load(16, 16, "/tiles.png");
	public static final Bitmap[][] FONT = load(8, 8, "/font.png");
	public static final Bitmap[][] ITEMS = load(8, 8, "/items.png");
	public static final Bitmap[][] GUI = load(4, 4, "/GUI.png");
	public static final Bitmap INVENTORYSQUARE = load(0, 12, 20, 11, "/GUI.png")[0][0];

	public static Bitmap[][] load(int sx, int sy, int w, int h, String file) {
		try {
			BufferedImage bi = ImageIO.read(Game.class.getResource(file));

			int xTiles = (bi.getWidth() - sx) / w;
			int yTiles = (bi.getHeight() - sy) / h;

			Bitmap[][] result = new Bitmap[xTiles][yTiles];

			for (int x = 0; x < xTiles; x++) {
				for (int y = 0; y < yTiles; y++) {
					result[x][y] = new Bitmap(w, h);
					bi.getRGB(sx + x * w, sy + y * h, w, h, result[x][y].pixels, 0, w);
				}
			}

			return result;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Bitmap[][] load(int w, int h, String file) {
		return load(0, 0, w, h, file);
	}

}
