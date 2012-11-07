package com.mbl111.ggo12.level;

import com.mbl111.ggo12.gfx.Screen;
import com.mbl111.ggo12.level.tile.Tile;

public class Level {

	public byte[] data, tiles;
	public int w, h;

	public Level(int w, int h) {
		data = new byte[w * h];
		tiles = new byte[w * h];
		this.w = w;
		this.h = h;
	}

	private void tick() {

	}

	private void render(Screen screen) {

	}

	public Tile getTile(int x, int y) {
		return Tile.byid(tiles[x + y * w]);
	}

	public byte getData(int x, int y) {
		return data[x + y * w];
	}
	
}
