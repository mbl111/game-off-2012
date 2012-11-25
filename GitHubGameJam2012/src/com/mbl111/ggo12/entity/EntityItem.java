package com.mbl111.ggo12.entity;

import com.mbl111.ggo12.Util.SyncRandom;
import com.mbl111.ggo12.Util.Vector2i;
import com.mbl111.ggo12.gfx.Art;
import com.mbl111.ggo12.gfx.Screen;
import com.mbl111.ggo12.inventory.ItemStack;

public class EntityItem extends Entity {

	public ItemStack itemStack;
	private double zz;
	private int xx, yy;
	private int lifeTime;
	private int time;
	private double xa, ya, za;

	public EntityItem(ItemStack itemStack, int x, int y) {
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		radius = new Vector2i(4, 4);
		this.itemStack = itemStack;
		textureIndex = itemStack.getIconIndex();
		lifeTime = 60 * 15 + SyncRandom.nextInt(60);
		za = (SyncRandom.nextInt(6) + 5) * 0.04 + 0.9;
	}

	public void tick() {
		time++;
		if (time >= lifeTime) {
			remove();
			return;
		}
		zz += za;
		if (zz < 0) {
			zz = 0;
			za *= -0.7;
		}
		za -= 0.15;
	}

	protected void touchBy(Entity entity) {
		if (entity instanceof Player) {
			((Player) entity).getInventory().addItem(itemStack);
			this.remove();
		}
	}

	public void render(Screen screen) {
		screen.drawShaddow(Art.ITEMS[textureIndex % 16][textureIndex / 16], x - radius.x, y - radius.y, 0xFF111111, 0);
		screen.draw(Art.ITEMS[textureIndex % 16][textureIndex / 16], x - radius.x, y - radius.y - (int) zz, 0);

	}
}
