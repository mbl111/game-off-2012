package com.mbl111.ggo12.level.tile;

import com.mbl111.ggo12.Util.SyncRandom;

public class DryGrass extends OverlayTile {

	public DryGrass(Tile tile) {
		super(tile);
		data = SyncRandom.nextInt(2);
		img = 3 + 0 * 16;
	}

	public void onClick() {
		level.setTile(x, y, underTile, false);
	}

}
