package com.mbl111.ggo12.inventory;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

	public List<ItemStack> items;
	public String title;

	public Inventory(String title) {
		this.title = title;
		this.items = new ArrayList<ItemStack>();
	}

	public ItemStack getStackInSlot(int i) {
		if (i >= items.size()) {
			throw new IllegalArgumentException("Accessed invalid inventory index");
		} else {
			return this.items.get(i);
		}
	}

	public String getName() {
		return title;
	}

	public void addItem(ItemStack itemStack) {
		if (hasItems(itemStack.itemID, 1)) {
			int itemCountToAdd = itemStack.stackSize;
			boolean fillingStacks = true;
			boolean createStacks = false;
			int i = 0;
			while (fillingStacks) {
				if (i >= items.size()) {
					fillingStacks = false;
					createStacks = true;
					break;
				}
				ItemStack is = items.get(i);
				if (is.itemID == itemStack.itemID) {
					if (is.stackSize < itemStack.getItem().getMaxStackSize()) {
						if (is.stackSize + itemCountToAdd <= itemStack.getItem().getMaxStackSize()) {
							is.stackSize += itemCountToAdd;
							fillingStacks = false;
						} else {
							int maxStack = itemStack.getItem().getMaxStackSize();
							itemCountToAdd -= maxStack - is.stackSize;
							is.stackSize = maxStack;
						}
					}
				}
				i++;
			}
			if (createStacks) {
				while (itemCountToAdd > 0) {
					int addedThisCycle = 0;
					if (itemCountToAdd <= itemStack.getItem().getMaxStackSize()) {
						itemStack.stackSize = itemCountToAdd;
						items.add(itemStack);
						addedThisCycle = itemCountToAdd;
					} else {
						items.add(new ItemStack(itemStack.itemID, itemStack.getItem().getMaxStackSize()));
						addedThisCycle = itemStack.getItem().getMaxStackSize();
					}
					itemCountToAdd -= addedThisCycle;
				}
			}
		} else {
			int itemCountToAdd = itemStack.stackSize;
			while (itemCountToAdd > 0) {
				int addedThisCycle = 0;
				if (itemCountToAdd <= itemStack.getItem().getMaxStackSize()) {
					itemStack.stackSize = itemCountToAdd;
					items.add(itemStack);
					addedThisCycle = itemCountToAdd;
				} else {
					items.add(new ItemStack(itemStack.itemID, itemStack.getItem().getMaxStackSize()));
					addedThisCycle = itemStack.getItem().getMaxStackSize();
				}
				itemCountToAdd -= addedThisCycle;
			}
		}
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
			for (int i = 0; i < items.size(); i++) {
				ItemStack is = items.get(i);
				if (is != null) {
					if (id == is.itemID) {
						if (itemsToGo >= is.stackSize) {
							items.remove(i--);
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
		for (int i = 0; i < items.size(); i++) {
			ItemStack is = items.get(i);
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
