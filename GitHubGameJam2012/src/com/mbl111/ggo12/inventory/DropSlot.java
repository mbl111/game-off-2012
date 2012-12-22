package com.mbl111.ggo12.inventory;

import java.util.Random;

import com.mbl111.ggo12.Game;
import com.mbl111.ggo12.Util.SyncRandom;
import com.mbl111.ggo12.entity.EntityItem;

public class DropSlot extends Slot {

	public DropSlot(Container container, int id, ItemStack is) {
		super(container, id, is);
	}

	public DropSlot(Container container, int id) {
		super(container, id);
	}

	public void putItem(ItemStack is) {
		Game game = Game.instance;

		int xOff = 0;
		int yOff = 0;

		if (game.getPlayer().dir == 0) {
			yOff = 16 + SyncRandom.nextInt(8);
			xOff = SyncRandom.nextInt(8) - 4;
		}
		if (game.getPlayer().dir == 1) {
			yOff = -16 - SyncRandom.nextInt(8);
			xOff = SyncRandom.nextInt(8) - 4;

		}
		if (game.getPlayer().dir == 2) {
			xOff = -16 - SyncRandom.nextInt(8);
			yOff = SyncRandom.nextInt(8) - 4;
		}
		if (game.getPlayer().dir == 3) {
			xOff = 16 + SyncRandom.nextInt(8);
			yOff = SyncRandom.nextInt(8) - 4;
		}

		game.level.add(new EntityItem(is, game.getPlayer().x + xOff, game.getPlayer().y + yOff));
	}

}
