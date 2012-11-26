package com.mbl111.ggo12.gfx.menu;

import com.mbl111.ggo12.Game;
import com.mbl111.ggo12.gfx.Font;
import com.mbl111.ggo12.gfx.Screen;
import com.mbl111.ggo12.gfx.menu.component.Button;
import com.mbl111.ggo12.gfx.menu.component.ClickListener;
import com.mbl111.ggo12.input.Input;
import com.mbl111.ggo12.inventory.Inventory;

public class InventoryMenu extends Menu {

	private Inventory inventory;

	public InventoryMenu(Inventory inventory) {
		this.inventory = inventory;
		Button play = new Button(Game.GAME_WIDTH / 2 - 36, Game.GAME_HEIGHT - 20, 72, 16, 0).setText("Close!");
		play.setClickListener(new ClickListener(this) {
			public void onClick() {
				game.setMenu(null);
			}
		});
		addComponent(play);
	}

	public void tick(Input input) {
		super.tick(input);
	}

	public boolean isFullScreen() {
		return true;
	}

	public void render(Screen screen) {
		super.render(screen);
	}
}
