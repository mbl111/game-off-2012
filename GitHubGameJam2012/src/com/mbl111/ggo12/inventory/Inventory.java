package com.mbl111.ggo12.inventory;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

	public ItemStack[] items;
	public int size;
	public String title;

	public Inventory(String title, int slots) {
		this.title = title;
		this.size = slots;
		this.items = new ItemStack[slots];
	}

	public ItemStack getStackInSlot(int i) {
		if (i >= size) {
			throw new IllegalArgumentException("Accessed invalid inventory index");
		} else {
			return this.items[i];
		}
	}

	public String getName() {
		return title;
	}

	public boolean removeItems(ItemStack itemStack) {
		return removeItems(itemStack.itemID, itemStack.stackSize);
	}

	public boolean removeItems(int id) {
		return removeItems(id, 1);
	}

	public boolean removeItems(int id, int count) {
		if (hasItems(id, count)) {
			int itemsToGo = count;
			for (int i = 0; i < size; i++) {
				ItemStack is = items[i];
				if (is != null) {
					if (id == is.itemID) {
						if (itemsToGo >= is.stackSize) {
							items[i] = null;
							itemsToGo -= is.stackSize;
							continue;
						} else {
							is.stackSize -= itemsToGo;
							return true;
						}
					}
				}
				if (itemsToGo <= 0) return true;
			}
		}
		return false;
	}

	public boolean hasItems(ItemStack itemStack) {
		return hasItems(itemStack.itemID, itemStack.stackSize);
	}

	public boolean hasItems(int id, int count) {
		int itemsToGo = count;
		for (int i = 0; i < size; i++) {
			ItemStack is = items[i];
			if (is != null) {
				if (id == is.itemID) {
					itemsToGo -= is.stackSize;
				}
			}
			if (itemsToGo <= 0) return true;
		}
		if (itemsToGo > 0) return false;
		return true;
	}

	public int getInventoryStackLimit() {
		return 64;
	}

}
