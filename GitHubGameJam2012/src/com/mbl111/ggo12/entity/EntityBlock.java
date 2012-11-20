package com.mbl111.ggo12.entity;

import com.mbl111.ggo12.gfx.Art;
import com.mbl111.ggo12.gfx.Screen;

public class EntityBlock extends Entity {

	private int pushTime = 0;
	private int pushDir = 0;
	public String name;

	public void tick() {
		if (pushDir == 0) move(0, +1);
		if (pushDir == 1) move(0, -1);
		if (pushDir == 2) move(-1, 0);
		if (pushDir == 3) move(+1, 0);
		pushDir = -1;
		if (pushTime > 0) pushTime--;
	}

	protected boolean blocks(Entity e) {
		return true;
	}

	public void touchBy(Entity entity) {
		if (entity instanceof Player && pushTime == 0) {
			pushDir = ((Player) entity).dir;
			pushTime = 10;
		}
	}

	public void render(Screen screen) {
		screen.draw(Art.TILES[0][0], x - radius.x, y - radius.y, 0);
	}

}
