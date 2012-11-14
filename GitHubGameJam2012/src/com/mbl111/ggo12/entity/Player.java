package com.mbl111.ggo12.entity;

import com.mbl111.ggo12.Game;
import com.mbl111.ggo12.input.Input;

public class Player extends Mob {

	public Game game;

	public Player(Game game) {
		this.game = game;
	}

	public void tick() {
		Input input = game.getInput();
		int speed = 1;
		if (input.down.down) {
			y += speed;
		}
		if (input.up.down) {
			y -= speed;
		}
		if (input.right.down) {
			x += speed;
		}
		if (input.left.down) {
			x -= speed;
		}
	}

}
