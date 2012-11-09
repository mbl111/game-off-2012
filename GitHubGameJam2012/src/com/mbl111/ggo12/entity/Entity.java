package com.mbl111.ggo12.entity;

import com.mbl111.ggo12.gfx.Art;
import com.mbl111.ggo12.gfx.Screen;
import com.mbl111.ggo12.level.Level;
import com.mbl111.ggo12.level.tile.Tile;

public class Entity {

	public int x, y;
	public int textureIndex;
	private boolean removed = false;
	private Level level;

	private void init(Level level) {
		this.level = level;
		init();
	}

	private void init() {
	}

	public void remove() {
		removed = true;
	}

	public void render(Screen screen) {
		screen.draw(Art.TILES[textureIndex % 16][textureIndex / 16], x - Tile.WIDTH / 2, y - Tile.HEIGHT, 0);
	}

	public void tick() {

	}

}
