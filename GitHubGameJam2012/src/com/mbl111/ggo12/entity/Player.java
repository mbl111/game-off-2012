package com.mbl111.ggo12.entity;

import com.mbl111.ggo12.Game;
import com.mbl111.ggo12.Util.Vector2i;
import com.mbl111.ggo12.input.Input;

public class Player extends Mob {

	public Game game;

	public Player(Game game) {
		this.game = game;
	}

	public void tick() {
		Input input = game.getInput();
		Vector2i move = new Vector2i(0, 0);
		int speed = 1;
		if (input.down.down) {
			move.y += speed;
		}
		if (input.up.down) {
			move.y -= speed;
		}
		if (input.right.down) {
			move.x += speed;
		}
		if (input.left.down) {
			move.x -= speed;
		}
		move(move);
	}

}
