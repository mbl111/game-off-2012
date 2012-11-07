package com.mbl111.ggo12;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	public static final int GAME_WIDTH = 220;
	public static final int GAME_HEIGHT = GAME_WIDTH * 3 / 4;
	public static final int SCALE = 3;
	public static final String GAME_NAME = "";

	public JFrame frame;
	private boolean running = true;
	private boolean CLOSE_REQUESTED = false;

	public Game() {
		Dimension d = new Dimension(GAME_WIDTH * SCALE, GAME_HEIGHT * SCALE);

		setPreferredSize(d);
		setMaximumSize(d);
		setMinimumSize(d);
	}

	public void createWindow() {
		frame = new JFrame(GAME_NAME);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new GameCloseHandler(this));
		frame.add(this);
		frame.pack();

		frame.setVisible(true);
	}

	public void start() {
		new Thread(this).start();
	}

	public void stop() {
		running = false;
	}

	public void run() {

		while (running && !CLOSE_REQUESTED) {

			
			
		}
		
		System.exit(0);
	}

	public void requestClose() {
		CLOSE_REQUESTED = true;
		System.out.println("Close has been requested on the window");
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.createWindow();
		game.start();
	}

}
