package com.mbl111.ggo12.level.tile;

import com.mbl111.ggo12.Util.SyncRandom;
import com.mbl111.ggo12.entity.Player;

public class DryGrass extends OverlayTile {

	public DryGrass(Tile tile) {
		super(tile);
		data = SyncRandom.nextInt(2);
		img = 3 + 0 * 16;
	}

	public void onClick() {
		Player p = level.game.getPlayer();
		int xt = p.x >> 4;
		int yt = p.y >> 4;
		double dist = Math.sqrt((x - xt) * (x - xt) + (y - yt) * (y - yt));
		if (dist < 2) {
			level.setTile(x, y, underTile, false);
		}
	}

}
