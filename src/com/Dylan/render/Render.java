package com.Dylan.render;

import com.Dylan.Player;
import com.Dylan.World;
import com.Dylan.calculations.Boolean2D;
import com.Dylan.input.UserInput;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.Serial;

public class Render extends Canvas implements Runnable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Thread thread;
    private final World world = new World();
    private final Player player = new Player();
    private final JFrame frame;
    public static int tileSize = 32;
    public static int scale = 3;
    public static int renderSize = tileSize * scale;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int WORLD_SIZE = 12;
    private static boolean running = false;
    private static final int fps = 144;
    public static int speed = 1;
    static String title = "Metroidvania Game";
    public Boolean2D movWorld;

    private final UserInput userInput;

    public Render() {
        this.frame = new JFrame();

        Dimension size = new Dimension(WIDTH, HEIGHT);
        this.setPreferredSize(size);

        this.userInput = new UserInput();

        this.addMouseListener(this.userInput.mouse);
        this.addMouseMotionListener(this.userInput.mouse);
        this.addMouseWheelListener(this.userInput.mouse);
        this.addKeyListener(this.userInput.keyboard);
    }

    public static void main(String[] args) {
        Render render = new Render();
        render.frame.setTitle(title);
        render.frame.add(render);
        render.frame.pack();
        render.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        render.frame.setLocationRelativeTo(null);
        render.frame.setResizable(false);
        render.frame.setVisible(true);

        render.start();
    }

    public synchronized void start() {
        running = true;
        this.thread = new Thread(this, "render.Render");
        this.thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            this.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / fps;
        double delta = 0;
        int frames = 0;

        this.player.init(this.userInput);
        this.world.init(this.userInput);

        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                update();
                delta --;
                render();
                frames ++;
            }
            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                this.frame.setTitle(title + " | " + frames + " fps");
                frames = 0;
            }
        }

        stop();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        world.render(g);
        player.render(g);

        g.dispose();
        bs.show();
    }

    private void update() {
        movWorld = player.update();
        world.update(movWorld);
    }
}
