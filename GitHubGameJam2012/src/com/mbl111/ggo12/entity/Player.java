package com.mbl111.ggo12.entity;

import com.mbl111.ggo12.Game;
import com.mbl111.ggo12.Util.Vector2i;
import com.mbl111.ggo12.gfx.Art;
import com.mbl111.ggo12.gfx.Screen;
import com.mbl111.ggo12.input.Input;

public class Player extends Mob {

	public Game game;
	public int dir;

	public Player(Game game) {
		this.game = game;
	}

	public void tick() {
		Input input = game.getInput();
		Vector2i move = new Vector2i(0, 0);
		int speed = 1;
		if (input.down.down) {
			move.y += speed;
			dir = 0;
		}
		if (input.up.down) {
			move.y -= speed;
			dir = 1;
		}
		if (input.right.down) {
			move.x += speed;
			dir = 3;
		}
		if (input.left.down) {
			move.x -= speed;
			dir = 2;
		}
		move(move);
	}

	public void render(Screen screen) {
		screen.draw(Art.PLAYERS[textureIndex % 16][textureIndex / 16], x - radius.x, y - radius.y, 0);
	}
	
	public void onClick() {
		System.out.println("Clicked the player!");
	}

}
