package com.mbl111.ggo12.inventory;

public class InventoryContainer extends Container {

	public InventoryContainer(Inventory inventory) {
		int length = inventory.items.size();
		for (int i = 0; i < length; i++) {
			slots.add(new Slot(this, i, inventory.getStackInSlot(i)));
		}
	}

}
