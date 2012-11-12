package com.mbl111.ggo12.level;

import java.util.ArrayList;
import java.util.List;

import com.mbl111.ggo12.entity.Entity;
import com.mbl111.ggo12.gfx.Screen;
import com.mbl111.ggo12.level.tile.Tile;

public class Level {

	public byte[] data, tiles;
	public int w, h;
	
	public List<Entity>[] entitiesInTiles;
	public List<Entity> entities = new ArrayList<Entity>();

	public Level(int w, int h) {
		data = new byte[w * h];
		tiles = new byte[w * h];
		this.w = w;
		this.h = h;
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = 0;
		}
	}

	public void tick() {

	}

	public void render(Screen screen, int xScroll, int yScroll) {
		int w = (screen.w + 15) >> 4;
		int h = (screen.h + 15) >> 4;
		int xo = xScroll >> 4;
		int yo = yScroll >> 4;

		screen.setOffset(xScroll, yScroll);
		for (int y = yo; y <= h + yo; y++) {
			for (int x = xo; x <= w + xo; x++) {
				getTile(x, y).render(screen, this, x << 4, y << 4);
			}
		}
		screen.setOffset(0, 0);
	}

	public Tile getTile(int x, int y) {
		if (x < 0 | x >= w | y < 0 | y >= h) return Tile.WALL;
		return Tile.byid(tiles[x + y * w]);
	}

	public byte getData(int x, int y) {
		if (x < 0 | x >= w | y < 0 | y >= h) return 0;
		return data[x + y * w];
	}

}
