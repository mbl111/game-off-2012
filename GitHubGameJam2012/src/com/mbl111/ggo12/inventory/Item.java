package com.mbl111.ggo12.inventory;

import com.mbl111.ggo12.gfx.Art;
import com.mbl111.ggo12.gfx.Bitmap;

public class Item {

	public int id;
	protected int maxStackSize = 20;
	private int maxDamage = 0;
	protected int iconIndex;
	private String itemName;

	// If there are different items based on datavalue
	// protected boolean hasSubtypes = false;

	public Item(int id) {
		this.id = id;
		if (itemsList[id] != null) {
			System.out.println("Item Conflict - " + id);
		}
		itemsList[id] = this;
	}

	public Item setIconIndex(int x, int y) {
		iconIndex = x + y * 16;
		return this;
	}

	public Item setName(String name) {
		itemName = name;
		return this;
	}

	public int getItemIndex() {
		return iconIndex;
	}

	public Bitmap getIcon() {
		return Art.ITEMS[iconIndex % 16][iconIndex / 16];
	}

	public String getName() {
		return itemName;
	}

	public int getMaxStackSize() {
		return maxStackSize;
	}

	public Item setMaxStackSize(int maxStackSize) {
		this.maxStackSize = maxStackSize;
		return this;
	}

	public static Item[] itemsList = new Item[256];
	public static Item dryGrass = new Item(0).setName("Dry Grass").setIconIndex(0, 0).setMaxStackSize(2);

}
