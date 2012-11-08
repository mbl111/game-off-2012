package com.mbl111.ggo12.gfx.menu;

import com.mbl111.ggo12.gfx.Font;
import com.mbl111.ggo12.gfx.Screen;

public class ExceptionMenu extends Menu {

	private StackTraceElement[] stack;

	public ExceptionMenu(Exception e) {
		stack = e.getStackTrace();
	}

	public void render(Screen screen) {
		screen.fill(0xFF000000, 0, 0, screen.w, screen.h, 0);
		for (int i = 0; i < stack.length; i++) {
			Font.draw(stack[i].toString(), 8, i * 8, 0xFFFFFFFF, screen);
		}

	}

}
