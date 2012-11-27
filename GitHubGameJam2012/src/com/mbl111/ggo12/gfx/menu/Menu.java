package com.mbl111.ggo12.gfx.menu;

import java.util.ArrayList;
import java.util.List;

import com.mbl111.ggo12.Game;
import com.mbl111.ggo12.gfx.Font;
import com.mbl111.ggo12.gfx.Screen;
import com.mbl111.ggo12.gfx.menu.component.MenuComponent;
import com.mbl111.ggo12.input.Input;

public class Menu {

	protected Game game;
	protected int tickCount;
	protected int selectedIndex;
	protected List<MenuComponent> components = new ArrayList<MenuComponent>();
	private int actionCooldown = 0;
	private boolean initialised = false;
	public int lastClickedIndex = -1;
	protected Input input;

	public void tick(Input input) {
		if (initialised) {
			int x = input.x;
			int y = input.y;
			this.input = input;
			for (MenuComponent e : components) {
				boolean over = e.isOver(x, y);
				e.tick(tickCount, input);
				if (over && input.b0Released) {
					e.click();
					break;
				}
			}
		}
	}

	public void render(Screen screen) {
		if (initialised) {
			for (int i = 0; i < components.size(); i++) {
				components.get(i).render(screen);
			}
		} else {
			Font.draw("MENU NOT INITIALISED", 0, 0, 0xFFFFFF, screen);
		}
	}

	public void init(Game game) {
		this.game = game;
		this.initialised = true;
	}

	public boolean isFullScreen() {
		return true;
	}

	public void addComponent(MenuComponent mc) {
		this.components.add(mc);
		mc.init(this);
	}

}
