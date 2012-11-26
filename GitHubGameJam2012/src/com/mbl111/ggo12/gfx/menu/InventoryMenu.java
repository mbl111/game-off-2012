package com.mbl111.ggo12.gfx.menu;

import com.mbl111.ggo12.Game;
import com.mbl111.ggo12.gfx.Font;
import com.mbl111.ggo12.gfx.Screen;
import com.mbl111.ggo12.gfx.menu.component.Button;
import com.mbl111.ggo12.gfx.menu.component.ClickListener;
import com.mbl111.ggo12.input.Input;
import com.mbl111.ggo12.inventory.Container;
import com.mbl111.ggo12.inventory.Inventory;
import com.mbl111.ggo12.inventory.InventoryContainer;

public class InventoryMenu extends Menu {

	private Inventory inventory;
	private Container container;

	public InventoryMenu(Inventory inventory) {
		this.inventory = inventory;
		container = new InventoryContainer(inventory);
		Button play = new Button(Game.GAME_WIDTH / 2 - 36, Game.GAME_HEIGHT - 20, 72, 16, 0).setText("Close!");
		play.setClickListener(new ClickListener(this) {
			public void onClick() {
				game.setMenu(null);
			}
		});
		addComponent(play);
	}

	@Override
	public void init(Game game) {
		super.init(game);
		container.init(game.getInput());
	}

	public void tick(Input input) {
		super.tick(input);
		container.tick(input);
		if (input.inventory.typed) {
			this.closeInventory();
			game.setMenu(null);
		}
	}

	private void closeInventory() {

	}

	public boolean isFullScreen() {
		return true;
	}

	public void render(Screen screen) {
		super.render(screen);
		container.render(screen);
	}
}
