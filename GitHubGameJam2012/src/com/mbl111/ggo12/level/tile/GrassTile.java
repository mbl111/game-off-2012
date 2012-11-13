package com.mbl111.ggo12.level.tile;

import com.mbl111.ggo12.gfx.Art;
import com.mbl111.ggo12.gfx.Screen;
import com.mbl111.ggo12.level.Level;

public class GrassTile extends Tile{

	
	public GrassTile(int id) {
		super(id);
	}

	public void render(Screen screen, Level level, int x, int y) {
		screen.draw(Art.TILES[2][0], x, y, level.getData(x >> 4, y >> 4));
	}
	
}
