package com.mbl111.ggo12.level.tile;

import com.mbl111.ggo12.gfx.Art;
import com.mbl111.ggo12.gfx.Screen;
import com.mbl111.ggo12.level.Level;

public class RockTile extends Tile {

	private int underTileImage;

	public RockTile(Tile floor) {
		this.underTileImage = floor.img;
		img = 2 + 1 * 16;
	}

	public void render(Screen screen) {
		screen.draw(Art.TILES[underTileImage % 16][underTileImage / 16], x * WIDTH, y * HEIGHT, 0);
		super.render(screen);
	}

}
