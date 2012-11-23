package com.mbl111.ggo12.level.tile;

import com.mbl111.ggo12.Util.SyncRandom;

public class GrassTile extends Tile {

	
	public GrassTile() {
		data = SyncRandom.nextInt(4);
		img = 2 + 0 * 16;
	}
	
}
