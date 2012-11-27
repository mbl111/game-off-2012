package com.mbl111.ggo12.gfx;

import com.mbl111.ggo12.inventory.ItemStack;

public class ItemRenderer {

	public static void renderItemStack(Screen screen, ItemStack item, int x, int y) {
		screen.draw(item.getItem().getIcon(), x, y);
		Font.draw("" + item.stackSize, x + 10, y, 0xFFFFFFFF, screen);
	}
	
	public static void renderItem(Screen screen, ItemStack item, int x, int y) {
		screen.draw(item.getItem().getIcon(), x, y);
	}

}
