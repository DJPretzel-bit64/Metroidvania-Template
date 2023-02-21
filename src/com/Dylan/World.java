package com.Dylan;

import com.Dylan.calculations.Boolean2D;
import com.Dylan.calculations.Vector2D;
import com.Dylan.input.UserInput;
import com.Dylan.render.Render;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class World {
    private final boolean[][] lvlBoolean = new boolean[Render.WORLD_SIZE][Render.WORLD_SIZE];
    private final BufferedImage[][] lvl = new BufferedImage[Render.WORLD_SIZE][Render.WORLD_SIZE];
    private final Vector2D offset = new Vector2D();
    private UserInput userInput;

    public void init(UserInput userInput) {
        this.userInput = userInput;

        try {
            File file = new File("src/com/Dylan/resources/world/lvl1.dat");
            Scanner reader = new Scanner(file);
            for(int i = 0; i < Render.WORLD_SIZE; i++) {
                for(int j = 0; j < Render.WORLD_SIZE; j++) {
                    if(reader.hasNext())
                        lvlBoolean[i][j] = Objects.equals(reader.next(), "1");
                }
            }
            reader.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < Render.WORLD_SIZE; i++) {
            for (int j = 0; j < Render.WORLD_SIZE; j++) {
                String dir = "";
                if(lvlBoolean[i][j]) {
                    if(j > 0) if (lvlBoolean[i][j - 1]) dir += "N";
                    if(i < Render.WORLD_SIZE - 1) if (lvlBoolean[i + 1][j]) dir += "E";
                    if(j < Render.WORLD_SIZE - 1) if (lvlBoolean[i][j + 1]) dir += "S";
                    if(i > 0) if (lvlBoolean[i - 1][j]) dir += "W";
                    try {
                        lvl[i][j] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/com/Dylan/resources/world/world" + dir + ".png")));
                    } catch(IOException ignored) {}
                }
            }
        }
    }

    public void render(Graphics g) {
        for(int i = 0; i < Render.WORLD_SIZE; i++) {
            for(int j = 0; j < Render.WORLD_SIZE; j++) {
                if(lvlBoolean[i][j]) g.drawImage(lvl[i][j], i * Render.tileSize * Render.scale + (int) offset.x, j * Render.tileSize * Render.scale + (int) offset.y, Render.tileSize * Render.scale, Render.tileSize * Render.scale, null);
            }
        }
    }

    public void update(Boolean2D movWorld) {
        this.userInput.keyboard.update();

        if(movWorld.x) {
            if (this.userInput.keyboard.getLeft()) {
                this.offset.x += Render.speed * Render.scale;
            }

            if (this.userInput.keyboard.getRight()) {
                this.offset.x -= Render.speed * Render.scale;
            }
        }
        if(movWorld.y) {
            if(this.userInput.keyboard.getUp()) {
                this.offset.y += Render.speed * Render.scale;
            }

            if(this.userInput.keyboard.getDown()) {
                this.offset.y -= Render.speed * Render.scale;
            }
        }
    }
}