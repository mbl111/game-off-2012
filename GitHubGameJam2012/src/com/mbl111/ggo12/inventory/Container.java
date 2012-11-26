package com.mbl111.ggo12.inventory;

import java.util.ArrayList;
import java.util.List;

import com.mbl111.ggo12.Game;
import com.mbl111.ggo12.Util.BoundingBox;
import com.mbl111.ggo12.gfx.Screen;
import com.mbl111.ggo12.input.Input;

public class Container {

	public int amountPerRow = 4;
	public Input input;

	public List<Slot> slots = new ArrayList<Slot>();

	public void init(Input input) {
		this.input = input;
	}

	public void tick(Input input) {
		this.input = input;
	}

	public boolean isMouseOver(Slot slot) {
		int i = slot.slotId;
		int row = i / amountPerRow;
		int posInRow = i % amountPerRow;
		int xa = (Game.GAME_WIDTH) / 2 - (amountPerRow * 11) + (posInRow * 22);
		int ya = 11 * row + 5;
		int mx = input.x;
		int my = input.y;
		return new BoundingBox(xa, ya, xa + slot.WIDTH, ya + slot.HEIGHT).intersects(mx, my, mx, my);
	}

	public void render(Screen screen) {
		for (int i = 0; i < slots.size(); i++) {
			int row = i / amountPerRow;
			int posInRow = i % amountPerRow;
			slots.get(i).render(screen, (screen.w) / 2 - (amountPerRow * 11) + (posInRow * 22), 11 * row + 5);
		}
	}
}
