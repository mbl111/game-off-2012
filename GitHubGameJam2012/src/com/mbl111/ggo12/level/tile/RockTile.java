package com.mbl111.ggo12.level.tile;

import com.mbl111.ggo12.entity.Entity;
import com.mbl111.ggo12.gfx.Art;
import com.mbl111.ggo12.gfx.Screen;
import com.mbl111.ggo12.level.Level;

public class RockTile extends OverlayTile {


	public RockTile(Tile floor) {
		super(floor);
		img = 2 + 1 * 16;
	}

	public boolean mayPass(Entity entity) {
		return false;
	}

}
