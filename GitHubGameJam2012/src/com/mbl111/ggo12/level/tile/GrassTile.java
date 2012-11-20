package com.mbl111.ggo12.level.tile;

import com.mbl111.ggo12.Util.SyncRandom;
import com.mbl111.ggo12.entity.Entity;
import com.mbl111.ggo12.gfx.Art;
import com.mbl111.ggo12.gfx.Screen;
import com.mbl111.ggo12.level.Level;

public class GrassTile extends Tile {

	
	public GrassTile() {
		data = SyncRandom.nextInt(4);
		img = 2 + 0 * 16;
	}
	
}
