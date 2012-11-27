package com.mbl111.ggo12.gfx.menu;

import com.mbl111.ggo12.Game;
import com.mbl111.ggo12.gfx.ItemRenderer;
import com.mbl111.ggo12.gfx.Screen;
import com.mbl111.ggo12.gfx.menu.component.Button;
import com.mbl111.ggo12.gfx.menu.component.ClickListener;
import com.mbl111.ggo12.input.Input;
import com.mbl111.ggo12.inventory.Container;
import com.mbl111.ggo12.inventory.DropItemContainer;
import com.mbl111.ggo12.inventory.Inventory;
import com.mbl111.ggo12.inventory.InventoryContainer;
import com.mbl111.ggo12.inventory.ItemStack;
import com.mbl111.ggo12.inventory.Slot;

public class InventoryMenu extends Menu {

	private Inventory inventory;
	private Container container;
	private DropItemContainer dropItem;
	private ItemStack selected;

	public InventoryMenu(Inventory inventory) {
		this.inventory = inventory;
		container = new InventoryContainer(inventory, 2, 10);
		dropItem = new DropItemContainer(140, 20);
		Button play = new Button(Game.GAME_WIDTH / 2 - 36, Game.GAME_HEIGHT - 20, 72, 16, 0).setText("Close!");
		play.setClickListener(new ClickListener(this) {
			public void onClick() {
				game.setMenu(null);
			}
		});
		addComponent(play);
	}

	public void init(Game game) {
		super.init(game);
		container.init(game.getInput());
		dropItem.init(game.getInput());
	}

	public void tick(Input input) {
		super.tick(input);
		container.tick(input);
		dropItem.tick(input);
		if (input.b0Clicked) {
			Slot slot = container.grabSelected();
			if (slot == null) {
				slot = dropItem.grabSelected();
			}
			if (slot != null) {
				if (slot.getItem() != null) {
					if (selected != null) {
						ItemStack spare = slot.items;
						slot.items = selected;
						selected = spare;
					} else {
						selected = slot.items;
						slot.items = null;
					}
				} else {
					if (selected != null) {
						slot.items = selected;
						selected = null;
					}
				}
			}
		}
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
		dropItem.render(screen);
		if (selected != null) {
			ItemRenderer.renderItemStack(screen, selected, input.x, input.y);
		}
	}
}
