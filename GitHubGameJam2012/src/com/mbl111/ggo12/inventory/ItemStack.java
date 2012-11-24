package com.mbl111.ggo12.inventory;

public class ItemStack {

	public int stackSize;
	public int itemID;
	public int itemDamage;

	public ItemStack(int id) {
		this(id, 1);
	}

	public ItemStack(int id, int stackSize) {
		this(id, stackSize, 0);
	}

	public ItemStack(int id, int stackSize, int damage) {
		this.itemID = id;
		this.stackSize = stackSize;
		this.itemDamage = damage;
	}

	public Item getItem() {
		return Item.itemsList[this.itemID];
	}

	public int getIconIndex() {
		return this.getItem().getItemIndex();
	}

	public String getName() {
		return this.getItem().getName();
	}

}
