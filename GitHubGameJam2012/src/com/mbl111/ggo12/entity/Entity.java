package com.mbl111.ggo12.entity;

import java.util.ArrayList;
import java.util.List;

import com.mbl111.ggo12.Util.Vector2i;
import com.mbl111.ggo12.gfx.Art;
import com.mbl111.ggo12.gfx.Screen;
import com.mbl111.ggo12.level.Level;
import com.mbl111.ggo12.level.tile.Tile;

public class Entity {

	public int x;
	public int y;
	public int textureIndex;
	private boolean removed = false;
	private Level level;
	public Vector2i radius = new Vector2i(8, 8);

	public void init(Level level) {
		x = 8;
		y = 8;
		this.level = level;
		init();
	}

	private void init() {
	}

	public boolean move(Vector2i move) {
		return move(move.x, move.y);
	}

	public boolean move(int xa, int ya) {
		if (xa != 0 | ya != 0) {
			boolean stopped = true;
			if (xa != 0 && movePart(xa, 0)) stopped = false;
			if (ya != 0 && movePart(0, ya)) stopped = false;
			if (!stopped) {
				int tx = x >> 4;
				int ty = y >> 4;
				level.getTile(tx, ty).steppedOn(this);
			}
			return !stopped;
		}
		return true;
	}

	List<List<Entity>> hitResults = new ArrayList<List<Entity>>();

	public boolean movePart(int xa, int ya) {
		if (xa != 0 && ya != 0) throw new IllegalArgumentException("One axis must be 0");

		int xto0 = ((x) - radius.x) >> 4;
		int yto0 = ((y) - radius.y) >> 4;
		int xto1 = ((x) + radius.x + 8) >> 4;
		int yto1 = ((y) + radius.y + 8) >> 4;

		int xt0 = ((x + xa) - radius.x) >> 4;
		int yt0 = ((y + ya) - radius.y) >> 4;
		int xt1 = ((x + xa) + radius.x + 8) >> 4;
		int yt1 = ((y + ya) + radius.y + 8) >> 4;

		boolean blocked = false;
		for (int yt = yt0; yt <= yt1; yt++) {
			for (int xt = xt0; xt <= xt1; xt++) {
				if (xt >= xto0 && xt <= xto1 && yt >= yto0 && yt <= yto1) continue;
				Tile t = level.getTile(xt, yt);
				this.bumpedInto(t);
				t.bumpInto(this);
				if (!t.mayPass(this)) {
					blocked = true;
					return false;
				}
			}
		}

		if (blocked) return false;

		List<Entity> wasInside;
		List<Entity> isInside;
		if (hitResults.size() > 0) {
			wasInside = hitResults.remove(hitResults.size() - 1);
		} else {
			wasInside = new ArrayList<Entity>();
		}

		if (hitResults.size() > 0) {
			isInside = hitResults.remove(hitResults.size() - 1);
		} else {
			isInside = new ArrayList<Entity>();
		}

		int xr = radius.x;
		int yr = radius.y;

		wasInside = level.getEntities(x - xr, y - yr, x + xr, y + yr);
		isInside = level.getEntities(x + xa - xr, y + ya - yr, x + xa + xr, y + ya + yr);
		isInside.removeAll(wasInside);
		for (int i = 0; i < isInside.size(); i++) {
			Entity e = isInside.get(i);
			if (e == this) continue;
			e.touchBy(this);
		}

		for (int i = 0; i < isInside.size(); i++) {
			Entity e = isInside.get(i);
			if (e == this) continue;
			if (e.blocks(this)) {
				return false;
			}

		}
		wasInside.clear();
		isInside.clear();
		hitResults.add(wasInside);
		hitResults.add(isInside);
		x += xa;
		y += ya;
		return true;
	}

	protected boolean blocks(Entity entity) {
		return false;
	}

	protected void touchBy(Entity entity) {
	}

	protected void bumpedInto(Tile t) {
	}

	public void remove() {
		removed = true;
	}

	public void render(Screen screen) {
		screen.draw(Art.TILES[textureIndex % 16][textureIndex / 16], x - radius.x, y - radius.y, 0);
	}

	public void tick() {
		x++;
	}

	public boolean isRemoved() {
		return removed;
	}

	public boolean intersects(int x0, int y0, int x1, int y1) {
		return !(x + radius.x < x0 || y + radius.y < y0 || x - radius.x > x1 || y - radius.y > y1);
	}

	public void onClick() {
	}

}
