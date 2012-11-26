package com.mbl111.ggo12.gfx.menu;

import java.util.ArrayList;
import java.util.List;

import com.mbl111.ggo12.Game;
import com.mbl111.ggo12.gfx.Screen;
import com.mbl111.ggo12.input.Input;

public class MenuStack {

	public List<Menu> menus = new ArrayList<Menu>();
	public Game game;

	public MenuStack(Game game) {
		this.game = game;
	}

	public void addMenu(Menu menu) {
		menu.init(game);
		this.menus.add(menu);
	}

	public void removeTopMenu() {
		if (menus.size() > 0) menus.remove(menus.size() - 1);
	}

	public void removeMenu(Menu menu) {
		menus.remove(menu);
	}

	public void tick(Input input) {
		int i = menus.size() - 1;
		Menu m = menus.get(i);
		m.tick(input);
		while (!m.isFullScreen() && i >= 0) {
			i--;
			m = menus.get(i);
			m.tick(input);
		}
	}

	public void render(Screen screen) {
		int i = menus.size() - 1;
		Menu m = menus.get(i);
		m.render(screen);
		while (!m.isFullScreen() & i >= 0) {
			i--;
			m = menus.get(i);
			m.render(screen);
		}
	}

	public int getMenuSize() {
		return menus.size();
	}

}
