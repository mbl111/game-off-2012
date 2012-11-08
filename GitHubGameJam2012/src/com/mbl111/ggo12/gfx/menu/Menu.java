package com.mbl111.ggo12.gfx.menu;

import com.mbl111.ggo12.Game;
import com.mbl111.ggo12.gfx.Screen;

public class Menu {

	public Game game;
	public int aliveTime = 0;

	public void init(Game game) {
		this.game = game;
	}

	public void tick() {
		aliveTime++;
	}

	public boolean isFullScreen() {
		return false;
	}

	public void render(Screen screen) {

	}

}
