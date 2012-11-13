package com.mbl111.ggo12.level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.mbl111.ggo12.Util.SyncRandom;
import com.mbl111.ggo12.entity.Entity;
import com.mbl111.ggo12.entity.Player;
import com.mbl111.ggo12.gfx.Screen;
import com.mbl111.ggo12.level.tile.Tile;

public class Level {

	public byte[] data, tiles;
	public int w, h;

	public List<Entity>[] entitiesInTiles;
	public List<Entity> entities = new ArrayList<Entity>();
	private Player player;

	private Comparator<Entity> spriteSorter = new Comparator<Entity>() {

		public int compare(Entity e1, Entity e2) {
			if (e2.y > e1.y) return -1;
			if (e2.y < e1.y) return +1;
			return 0;
		}
	};
	
	public Level(int w, int h) {
		data = new byte[w * h];
		tiles = new byte[w * h];
		entitiesInTiles = new ArrayList[w * h];
		this.w = w;
		this.h = h;
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = 0;
			data[i] = (byte) SyncRandom.nextInt(4);
		}

		for (int i = 0; i < w * h; i++) {
			entitiesInTiles[i] = new ArrayList<Entity>();
		}
	}

	public void tick() {

		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			int oxt = entity.x >> 4;
			int oyt = entity.y >> 4;

			entity.tick();
			if (entity.isRemoved()) {
				entities.remove(i--);
				removeEntity(oxt, oyt, entity);
			} else {
				int xt = entity.x >> 4;
				int yt = entity.y >> 4;
				removeEntity(oxt, oyt, entity);
				insertEntity(xt, yt, entity);
			}

		}

	}

	public void add(Entity entity) {
		if (entity instanceof Player) {
			player = (Player) entity;
		}
		entities.add(entity);
		entity.init(this);
		insertEntity(entity.x >> 4, entity.y >> 4, entity);

	}

	public int getEntityCount() {
		return entities.size();
	}

	public void remove(Entity entity) {
		if (entity instanceof Player) {
			player = null;
		}
		entities.remove(entity);
		removeEntity(entity.x >> 4, entity.y >> 4, entity);

	}

	private void insertEntity(int xt, int yt, Entity entity) {
		if (xt < 0 | xt >= w | yt < 0 | yt >= h) return;
		entitiesInTiles[xt + yt * w].add(entity);
	}

	private void removeEntity(int xt, int yt, Entity entity) {
		if (xt < 0 | xt >= w | yt < 0 | yt >= h) return;
		entitiesInTiles[xt + yt * w].remove(entity);
	}

	public void renderTile(Screen screen, int xScroll, int yScroll) {
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
	
	private List<Entity> rowSprites = new ArrayList<Entity>();
	
	public void renderEntity(Screen screen, int xScroll, int yScroll) {
		int w = (screen.w + 15) >> 4;
		int h = (screen.h + 15) >> 4;
		int xo = xScroll >> 4;
		int yo = yScroll >> 4;

		screen.setOffset(xScroll, yScroll);
		for (int y = yo; y <= h + yo; y++) {
			for (int x = xo; x <= w + xo; x++) {
				if (x < 0 || y < 0 || x >= this.w || y >= this.h) continue;
				rowSprites.addAll(entitiesInTiles[x + y * this.w]);
			}
			if (rowSprites.size() > 0) {
				sortAndRender(screen, entities);
			}
			rowSprites.clear();

		}

		screen.setOffset(0, 0);
	}
	
	private void sortAndRender(Screen screen, List<Entity> list) {
		Collections.sort(list, spriteSorter);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).render(screen);
		}
	}

	public Tile getTile(int x, int y) {
		if (x < 0 | x >= w | y < 0 | y >= h) return Tile.ROCK;
		return Tile.byid(tiles[x + y * w]);
	}

	public byte getData(int x, int y) {
		if (x < 0 | x >= w | y < 0 | y >= h) return 0;
		return data[x + y * w];
	}

}
