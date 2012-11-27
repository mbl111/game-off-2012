package com.mbl111.ggo12.inventory;

import com.mbl111.ggo12.Game;
import com.mbl111.ggo12.Util.BoundingBox;
import com.mbl111.ggo12.gfx.Font;
import com.mbl111.ggo12.gfx.Screen;
import com.mbl111.ggo12.input.Input;

public class DropItemContainer extends Container {

	private Slot dropItem;
	public int x, y;

	public DropItemContainer(int x, int y) {
		this.x = x;
		this.y = y;
		slots.add(new Slot(this, 0));
	}

	public boolean isMouseOver(Slot slot) {
		int i = slot.slotId;
		int xa = x + 24;
		int ya = y + 10;
		int mx = input.x;
		int my = input.y;
		return new BoundingBox(xa, ya, xa + slot.WIDTH, ya + slot.HEIGHT).intersects(mx, my, mx, my);
	}

	public void render(Screen screen) {
		Font.draw("Drop Item", x, y, 0xFFFFFFFF, screen);
		slots.get(0).render(screen, x + 24, y + 10);
	}

}
