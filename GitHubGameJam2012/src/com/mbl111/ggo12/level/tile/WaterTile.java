package com.mbl111.ggo12.level.tile;

import com.mbl111.ggo12.Util.SyncRandom;

public class WaterTile extends Tile {

	
	public WaterTile() {
		img = 4 + 0 * 16;
		data = SyncRandom.nextInt(8 / 2);
	}
	
}
