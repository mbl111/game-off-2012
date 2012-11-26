package com.mbl111.ggo12.gfx.menu;

import com.mbl111.ggo12.gfx.Font;
import com.mbl111.ggo12.gfx.Screen;
import com.mbl111.ggo12.input.Input;

public class ExceptionMenu extends Menu {

	private StackTraceElement[] stack;

	public ExceptionMenu(Exception e) {
		stack = e.getStackTrace();
	}

	public void tick(Input input) {
		super.tick(input);
		if (tickCount >= 60 * 10) game.requestClose();
	}

	public boolean isFullScreen() {
		return true;
	}

	public void render(Screen screen) {
		screen.fill(0xFF000000, 0, 0, screen.w, screen.h, 0);
		String error = "The Game Broke!!";
		Font.draw(error, (screen.w - error.length()) / 2, (screen.h / 2) - 4, 0xFFFFFFFF, screen);
	}
}
