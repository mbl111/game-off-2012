package com.mbl111.ggo12.level.tile;


import com.mbl111.ggo12.gfx.Art;
import com.mbl111.ggo12.gfx.Screen;
import com.mbl111.ggo12.level.Level;

public class Tile {

	public static final int WIDTH = 16;
	public static final int HEIGHT = 16;
	public static Tile[] tiles = new Tile[256];

	public int id;

	public Tile(int id) {
		if (tiles[id] != null) {
			throw new RuntimeException("Duped tile ids - " + id);
		}
		tiles[id] = this;
		this.id = id;
	}

	public static Tile byid(int id) {
		if (id > tiles.length) return null;
		return tiles[id];
	}

	public void render(Screen screen, Level level, int x, int y) {
		screen.draw(Art.TILES[2][0], x, y, level.getData(x >> 4, y >> 4));
	}

	public static Tile WALL = new Tile(0);

}
