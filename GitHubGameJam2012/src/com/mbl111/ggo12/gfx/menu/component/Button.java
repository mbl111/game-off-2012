package com.mbl111.ggo12.gfx.menu.component;

import com.mbl111.ggo12.gfx.Art;
import com.mbl111.ggo12.gfx.Font;
import com.mbl111.ggo12.gfx.Screen;
import com.mbl111.ggo12.input.Input;

public class Button extends MenuComponent {

	public Button(int x, int y, int width, int height, int selectedIndex) {
		super(x, y, width, height, selectedIndex);
		if (height < 12) throw new IllegalArgumentException("Height must be > 12 for buttons!!");
		if (height % 4 != 0) throw new IllegalArgumentException("Height must be a multiple of 4 for buttons!!");
	}

	public void tick(int TickCount, Input input) {
		super.tick(TickCount, input);
	}

	public void render(Screen screen) {
		int col = 0xFF333333;
		String msg = text;
		int yp = h / 4;
		int xp = w / 4;
		int xib = 0;
		int yib = 0;

		if (selected) {
			col = 0xFFFFFFFF;
			xib = 3;
		}
		// derp
		for (int yc = 0; yc < yp; yc++) {
			for (int xc = 0; xc < xp; xc++) {
				if (xc == 0 && yc == 0) screen.draw(Art.GUI[0 + xib][0 + yib], x + xc * 4, y + yc * 4);
				else if (xc == xp - 1 && yc == 0) screen.draw(Art.GUI[2 + xib][0 + yib], x + xc * 4, y + yc * 4);
				else if (xc == 0 && yc == yp - 1) screen.draw(Art.GUI[0 + xib][2 + yib], x + xc * 4, y + yc * 4);
				else if (xc == xp - 1 && yc == yp - 1) screen.draw(Art.GUI[2 + xib][2 + yib], x + xc * 4, y + yc * 4);
				else if (yc == 0) screen.draw(Art.GUI[1 + xib][0 + yib], x + xc * 4, y + yc * 4);
				else if (yc == yp - 1) screen.draw(Art.GUI[1 + xib][2 + yib], x + xc * 4, y + yc * 4);
				else if (xc == 0) screen.draw(Art.GUI[0 + xib][1 + yib], x + xc * 4, y + yc * 4);
				else if (xc == xp - 1) screen.draw(Art.GUI[2 + xib][1 + yib], x + xc * 4, y + yc * 4);
				else screen.draw(Art.GUI[1 + xib][1 + yib], x + xc * 4, y + yc * 4);
			}
		}
		Font.draw(msg, (x + w / 2) - ((msg.length()) / 2 * 8) + (msg.length() % 2 == 0 ? 4 : 0), y + 4, col, screen);
	}

	public Button setText(String text) {
		this.text = text;
		return this;
	}
}
