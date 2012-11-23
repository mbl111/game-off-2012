package com.mbl111.ggo12.level.tile;

import com.mbl111.ggo12.entity.Entity;
import com.mbl111.ggo12.gfx.Art;
import com.mbl111.ggo12.gfx.Screen;
import com.mbl111.ggo12.level.Level;

public class Tile {

	public static final int WIDTH = 16;
	public static final int HEIGHT = 16;
	protected int x;
	protected int y;
	public int img = 0;
	protected int data = 0;
	protected Level level;

	public void render(Screen screen) {
		screen.draw(Art.TILES[img % 16][img / 16], x * WIDTH, y * HEIGHT, 0);
	}

	public void neighborUpdate(Tile tile, int tx, int ty) {

	}

	public void init(Level level, int xt, int yt) {
		this.x = xt;
		this.y = yt;
		this.level = level;
	}

	public void steppedOn(Entity entity) {

	}

	public boolean mayPass(Entity entity) {
		return true;
	}

	public void bumpInto(Entity entity) {
	}

	public void onClick() {
	}

}
