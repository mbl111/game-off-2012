package com.mbl111.ggo12.inventory;

import com.mbl111.ggo12.Util.BoundingBox;
import com.mbl111.ggo12.gfx.Art;
import com.mbl111.ggo12.gfx.Font;
import com.mbl111.ggo12.gfx.Screen;

public class Slot {

	public ItemStack items;
	public final int WIDTH = 19;
	public final int HEIGHT = 10;
	public Container parent;
	public int slotId;

	public Slot(Container container, int id) {
		this.parent = container;
		this.slotId = id;
	}

	public Slot(Container container, int id, ItemStack is) {
		this.parent = container;
		this.items = is;
		this.slotId = id;
	}

	public void updateSlot(ItemStack is) {
		this.items = is;
	}

	public boolean hasItems() {
		return this.getItem() != null;
	}

	public ItemStack getItem() {
		return items;
	}

	public void putItem(ItemStack is) {
		items = is;
	}

	public void render(Screen screen, int x, int y) {
		screen.draw(Art.INVENTORYSQUARE, x, y);
		if (parent.isMouseOver(this)) {
			screen.fill(0x99AAAAAA, x + 1, y + 1, WIDTH - 1, HEIGHT - 1, 0);
		}
		if (hasItems()) {
			screen.draw(items.getItem().getIcon(), x + 1, y + 2);
			Font.draw("" + items.stackSize, x + 11, y + 2, 0xFFFFFFFF, screen);
		}
	}

}
