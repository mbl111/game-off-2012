package com.mbl111.ggo12.gfx.menu.component;

import com.mbl111.ggo12.gfx.menu.Menu;

public abstract class ClickListener {

	public Menu menu;

	public ClickListener(Menu parent) {
		this.menu = parent;
	}

	public abstract void onClick();

}
