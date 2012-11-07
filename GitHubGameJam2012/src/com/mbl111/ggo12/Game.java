package com.mbl111.ggo12;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.mbl111.ggo12.gfx.Art;
import com.mbl111.ggo12.gfx.Bitmap;
import com.mbl111.ggo12.gfx.Screen;

public class Game extends Canvas implements Runnable {

	public static final int GAME_WIDTH = 220;
	public static final int GAME_HEIGHT = GAME_WIDTH * 3 / 4;
	public static final int SCALE = 3;
	public static final String GAME_NAME = "";

	public JFrame frame;
	private boolean running = true;
	private boolean CLOSE_REQUESTED = false;
	private boolean limitFps = false;
	private int fps;
	private int ups;
	private int gameTicks;
	private Screen screen;

	public Game() {
		Dimension d = new Dimension(GAME_WIDTH * SCALE, GAME_HEIGHT * SCALE);

		setPreferredSize(d);
		setMaximumSize(d);
		setMinimumSize(d);

	}

	public void createWindow() {
		frame = new JFrame(GAME_NAME);
		// frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.addWindowListener(new GameCloseHandler(this));
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.pack();

		frame.setVisible(true);
	}

	public void limitFps(boolean limit) {
		limitFps = limit;
	}

	public void start() {
		new Thread(this).start();
	}

	public void stop() {
		running = false;
	}

	public void init() {
		screen = new Screen(GAME_WIDTH, GAME_HEIGHT);
	}

	public void run() {

		double nsPerTick = 1000000000.0 / 60.0;
		double unprocessed = 0;
		long lastTime = System.nanoTime();
		long lastTimer = System.currentTimeMillis();
		int frames = 0;
		int ticks = 0;

		init();

		while (running && !CLOSE_REQUESTED) {

			// if (!hasFocus()) keys.release();

			long now = System.nanoTime();
			unprocessed += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = !limitFps;

			while (unprocessed >= 1) {
				ticks++;
				gameTicks++;
				tick();
				unprocessed -= 1;
				shouldRender = true;
			}

			try {
				Thread.sleep(1L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (shouldRender) {
				render();
				frames++;
			}

			if (System.currentTimeMillis() - lastTimer > 1000) {
				lastTimer += 1000;
				this.fps = frames;
				this.ups = ticks;
				System.out.println("Updates " + this.ups + " - Frames "
						+ this.fps);
				frames = 0;
				ticks = 0;
			}

		}

		System.exit(0);
	}

	private void tick() {

	}

	private void render() {

		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.fillRect(0, 0, getWidth(), getHeight());


		int fi = 0;
		for (int y = -1; y < GAME_HEIGHT / 16; y++){
			for (int x = -1; x < GAME_WIDTH / 16; x++){
				fi++;
				//screen.draw(Art.TILES[0][0], x * 16 + 16, y * 16 + 16, fi % 4);
				screen.draw(Art.TILES[1][0], x * 16 + 16, y * 16 + 16, fi % 4);
			}	
		}
		

		int ww = GAME_WIDTH * SCALE;
		int hh = GAME_HEIGHT * SCALE;
		int xo = (getWidth() - ww) / 2;
		int yo = (getHeight() - hh) / 2;

		g.drawImage(screen.image, xo, yo, ww, hh, null);

		bs.show();
		g.dispose();

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
