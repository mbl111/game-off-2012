package com.mbl111.ggo12.gfx.menu;

import com.mbl111.ggo12.Game;
import com.mbl111.ggo12.Util.BoundingBox;
import com.mbl111.ggo12.gfx.Screen;
import com.mbl111.ggo12.input.Input;

public class InGameGUI {

	public Game game;
	public BoundingBox guiBB = new BoundingBox(0, 0, 10, 10);
	public boolean itemSelected = false;

	// public ItemStack item; the item that is selected

	public InGameGUI(Game game) {
		this.game = game;
	}

	public void tick(Input input) {
		if (input.b0Clicked) {
			if (guiBB.intersects(input.x, input.y, input.x, input.y)) {
				System.out.println("Clicked the GUI @ " + "(" + input.x + "," + input.y + ")");
			} else {
				game.getCurrentLevel().onClick(input.x, input.y);
			}
		}
	}

	public void render(Screen screen) {
		
	}

}
