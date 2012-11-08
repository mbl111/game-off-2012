package com.mbl111.ggo12;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.mbl111.ggo12.gfx.Art;
import com.mbl111.ggo12.gfx.Font;
import com.mbl111.ggo12.gfx.Screen;
import com.mbl111.ggo12.gfx.menu.ExceptionMenu;
import com.mbl111.ggo12.gfx.menu.Menu;
import com.mbl111.ggo12.gfx.menu.MenuStack;

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
	private int[][] tiles;
	private int[][] data;
	private MenuStack menuStack = new MenuStack(this);

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

		data = new int[GAME_WIDTH / 16 + 1][GAME_HEIGHT / 16 + 1];
		tiles = new int[GAME_WIDTH / 16 + 1][GAME_HEIGHT / 16 + 1];

		for (int y = 0; y < GAME_HEIGHT / 16 + 1; y++) {
			for (int x = 0; x < GAME_WIDTH / 16 + 1; x++) {
				data[x][y] = 0;
				tiles[x][y] = 3;
			}
		}

		tiles[0][0] = 0;
		tiles[0][1] = 0;
		tiles[0][2] = 0;
		tiles[0][3] = 0;
		tiles[0][4] = 0 + 1 * 16;
		data[0][4] = 1;
		tiles[1][4] = 1;
		tiles[2][4] = 1;
		tiles[3][4] = 1;

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
			try {
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
					System.out.println("Updates " + this.ups + " - Frames " + this.fps);
					frames = 0;
					ticks = 0;
				}
			} catch (Exception e) {
				e.printStackTrace();
				setMenu(new ExceptionMenu(e));
			}
		}

		System.exit(0);
	}

	public void setMenu(Menu menu) {
		if (menu == null) {
			menuStack.removeTopMenu();
		} else {
			menuStack.addMenu(menu);
		}
	}

	private void tick() {
		if (menuStack.getMenuSize() > 0) {
			menuStack.tick();
		} else {

		}
	}

	private void render() {

		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.fillRect(0, 0, getWidth(), getHeight());

		if (menuStack.getMenuSize() > 0) {
			menuStack.render(screen);
		} else {
			for (int y = 0; y < GAME_HEIGHT / 16 + 1; y++) {
				for (int x = 0; x < GAME_WIDTH / 16 + 1; x++) {
					int d = data[x][y+232];
					int t = tiles[x][y];
					// screen.draw(Art.TILES[0][0], x * 16 + 16, y * 16 + 16, fi
					// %
					// 4);
					screen.draw(Art.TILES[t % 16][t / 16], x * 16, y * 16, d);
				}
			}
		}

		Font.draw("abcdefghijklmnopqrstuvwxyz", 8, 128, 0xFF000000, screen);
		
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
