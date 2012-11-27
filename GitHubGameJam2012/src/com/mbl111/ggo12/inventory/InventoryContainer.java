package com.mbl111.ggo12.inventory;

public class InventoryContainer extends Container {

	public int maxRows;

	public InventoryContainer(Inventory inventory, int x, int y) {
		int length = inventory.items.size();
		int lastRowLength = length % amountPerRow;
		for (int i = 0; i < length + (amountPerRow - lastRowLength); i++) {
			if (i >= length) {
				slots.add(new Slot(this, i));
			} else {
				slots.add(new Slot(this, i, inventory.getStackInSlot(i)));
			}
			maxRows = length / amountPerRow;
		}
		this.x = x;
		this.y = y;
	}

}
