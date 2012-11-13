package com.mbl111.ggo12.level.tile;

import com.mbl111.ggo12.gfx.Art;
import com.mbl111.ggo12.gfx.Screen;
import com.mbl111.ggo12.level.Level;

public class RockTile extends Tile{

	private Tile underTile;
	
	public RockTile(int id, Tile floor) {
		super(id);
		this.underTile = floor;
	}

	public void render(Screen screen, Level level, int x, int y) {
		underTile.render(screen, level, x, y);
		screen.draw(Art.TILES[2][1], x, y, 0);
	}
	
}
