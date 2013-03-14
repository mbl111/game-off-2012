package com.mbl111.ggo12.level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.mbl111.ggo12.Game;
import com.mbl111.ggo12.Util.SyncRandom;
import com.mbl111.ggo12.Util.Vector2i;
import com.mbl111.ggo12.entity.Entity;
import com.mbl111.ggo12.entity.EntityBlock;
import com.mbl111.ggo12.entity.Player;
import com.mbl111.ggo12.gfx.Screen;
import com.mbl111.ggo12.level.tile.DryGrass;
import com.mbl111.ggo12.level.tile.GrassTile;
import com.mbl111.ggo12.level.tile.RockTile;
import com.mbl111.ggo12.level.tile.Tile;

public class Level {

	public Tile[] tiles;
	public int w, h;
	public Game game;

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

	public Level(int w, int h, Game game) {
		tiles = new Tile[w * h];
		this.game = game;
		entitiesInTiles = new ArrayList[w * h];
		this.w = w;
		this.h = h;
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				Tile tile = new GrassTile();
				setTile(x, y, tile, false);
				if (SyncRandom.nextInt(5) == 0) {
					setTile(x, y, new DryGrass(tile), false);
				}
			}
		}

		for (int i = 0; i < w * h; i++) {
			entitiesInTiles[i] = new ArrayList<Entity>();
		}
		EntityBlock eb = new EntityBlock();
		add(eb);
		eb.x = 64;
		eb.y = 64;
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

	public void setTile(int xt, int yt, Tile tile, boolean physics) {
		if (xt < 0 | xt >= w | yt < 0 | yt >= h) return;  
		tiles[xt + yt * w] = tile;
		tile.init(this, xt, yt);
		if (physics) {
			getTile(xt - 1, yt).neighborUpdate(tile, xt - 1, yt);
			getTile(xt + 1, yt).neighborUpdate(tile, xt + 1, yt);
			getTile(xt, yt - 1).neighborUpdate(tile, xt, yt - 1);
			getTile(xt, yt + 1).neighborUpdate(tile, xt, yt + 1);
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
				getTile(x, y).render(screen);
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
		if (x < 0 || y < 0 || x >= w || y >= h) {
			Tile tile = new RockTile(new GrassTile());
			tile.init(this, x, y);
			return tile;
		}
		return tiles[x + y * w];
	}

	public Tile getTile(Vector2i pos) {
		int x = (int) pos.x / Tile.WIDTH;
		int y = (int) pos.y / Tile.HEIGHT;
		return getTile(x, y);
	}

	public List<Entity> getEntities(int x0, int y0, int x1, int y1) {
		List<Entity> result = new ArrayList<Entity>();
		int xt0 = x0 >> 4;
		int xt1 = x1 >> 4;
		int yt0 = y0 >> 4;
		int yt1 = y1 >> 4;
		for (int y = yt0; y <= yt1; y++) {
			for (int x = xt0; x <= xt1; x++) {
				if (x < 0 || y < 0 || x >= w || y >= h) continue;
				List<Entity> entities = entitiesInTiles[x + y * this.w];
				for (int i = 0; i < entities.size(); i++) {
					Entity e = entities.get(i);
					if (e.intersects(x0, y0, x1, y1)) result.add(e);
				}
			}
		}
		return result;
	}

	public void onClick(int x, int y) {
		int xa = x + game.getXScroll();
		int ya = y + game.getYScroll();
		List<Entity> entities = getEntities(xa, ya, xa, ya);
		if (entities.size() > 0) {
			Collections.sort(entities, spriteSorter);
			entities.get(0).onClick();
		} else {
			int xt = xa >> 4;
			int yt = ya >> 4;
			getTile(xt, yt).onClick();
		}
	}

}
