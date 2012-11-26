package com.mbl111.ggo12.gfx.menu.component;

import com.mbl111.ggo12.gfx.Screen;
import com.mbl111.ggo12.gfx.menu.Menu;
import com.mbl111.ggo12.input.Input;

public class MenuComponent {

	public String text = "";
	public boolean selected;
	protected int x, y, w, h, index;
	protected Menu menu;
	protected int tick = 0;
	protected ClickListener clickListener;

	public MenuComponent(int x, int y, int w, int h, int selectedIndex) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.index = selectedIndex;
	}

	public void init(Menu menu) {
		this.menu = menu;
	}

	public void tick(int TickCount, Input input) {
		tick++;
	}

	public void render(Screen screen) {
	}

	public MenuComponent setText(String text) {
		this.text = text;
		return this;
	}

	public void setSize(int width, int height) {
		this.w = width;
		this.h = height;
	}

	public boolean isOver(int x2, int y2) {
		if (x2 > x && x2 <= x + w + 1 && y2 > y && y2 <= y + h + 1) {
			selected = true;
		} else {
			selected = false;
		}
		return selected;
	}

	public void click() {
		menu.lastClickedIndex = index;
		if (clickListener != null) {
			clickListener.onClick();
		}
	}

	public MenuComponent setClickListener(ClickListener clickListener2) {
		this.clickListener = clickListener2;
		return this;
	}
}
