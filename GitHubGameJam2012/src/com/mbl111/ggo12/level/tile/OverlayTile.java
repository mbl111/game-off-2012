package com.mbl111.ggo12.level.tile;

import com.mbl111.ggo12.gfx.Screen;
import com.mbl111.ggo12.level.Level;

public class OverlayTile extends Tile {

	protected Tile underTile;

	public OverlayTile(Tile tile) {
		underTile = tile;
	}

	public void init(Level level, int xt, int yt) {
		underTile.init(level, xt, yt);
		super.init(level, xt, yt);
	}

	@Override
	public void render(Screen screen) {
		underTile.render(screen);
		super.render(screen);
	}

}
