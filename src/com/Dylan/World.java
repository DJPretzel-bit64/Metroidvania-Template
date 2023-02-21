package com.Dylan;

import com.Dylan.calculations.Boolean2D;
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
    private BufferedImage[][] lvl = new BufferedImage[Render.WORLD_SIZE][Render.WORLD_SIZE];
    private String dir = "";
    StringBuilder strBuilder = new StringBuilder(dir);

    public void init() throws IOException {
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
                dir = "";
                if(lvlBoolean[i][j]) {
                    if(j > 0) if (lvlBoolean[i][j - 1] || j == 0) dir += "N";
                    if(i < Render.WORLD_SIZE - 1) if (lvlBoolean[i + 1][j] || i == Render.WORLD_SIZE - 2) dir += "E";
                    if(j < Render.WORLD_SIZE - 1) if (lvlBoolean[i][j + 1] || j == Render.WORLD_SIZE - 2) dir += "S";
                    if(i > 0) if (lvlBoolean[i - 1][j] || i == 0) dir += "W";
                    lvl[i][j] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/com/Dylan/resources/world/world" + dir + ".png")));
                }
            }
        }
    }

    public void render(Graphics g) {
        for(int i = 0; i < Render.WORLD_SIZE; i++) {
            for(int j = 0; j < Render.WORLD_SIZE; j++) {
                if(lvlBoolean[i][j]) g.drawImage(lvl[i][j], i * Render.tileSize * Render.scale, j * Render.tileSize * Render.scale, Render.tileSize * Render.scale, Render.tileSize * Render.scale, null);
            }
        }
    }

    public void update(Boolean2D movWorld) {

    }
}