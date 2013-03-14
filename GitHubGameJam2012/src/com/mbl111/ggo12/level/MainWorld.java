package com.mbl111.ggo12.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.mbl111.ggo12.Game;
import com.mbl111.ggo12.Util.SyncRandom;
import com.mbl111.ggo12.level.tile.DryGrass;
import com.mbl111.ggo12.level.tile.GrassTile;
import com.mbl111.ggo12.level.tile.Tile;
import com.mbl111.ggo12.level.tile.WaterTile;

public class MainWorld extends Level {

	public MainWorld(int w, int h, Game game, String filename) {
		super(w, h, game);

		BufferedImage worldImg = null;

		try {
			worldImg = ImageIO.read(Game.class.getResource(filename));
			int[] pixels = new int[w * h];
			worldImg.getRGB(0, 0, worldImg.getWidth(), worldImg.getHeight(), pixels, 0, worldImg.getWidth());
			for (int y = 0; y < h; y++) {
				for (int x = 0; x < w; x++) {
					Tile tile = null;
					System.out.println(pixels[x + y * w] + "|" + x + "|" + y);
					if (pixels[x + y * w] == 0xFF0000FF) tile = new WaterTile();
					if (pixels[x + y * w] == 0xFF00FF00) tile = new GrassTile();
					if (pixels[x + y * w] == -1) tile = new GrassTile();

					setTile(x, y, tile, false);
				}
			}

			for (int y = 0; y < h; y++) {
				for (int x = 0; x < w; x++) {
					Tile tile = getTile(x, y);
					if (tile instanceof GrassTile) {
						if (SyncRandom.nextInt(10) == 0) {
							setTile(x, y, new DryGrass(tile), false);
						}
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
