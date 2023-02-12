package com.Dylan;

import com.Dylan.calculations.Vector2D;
import com.Dylan.render.Render;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player {
    public Vector2D pos = new Vector2D(0, 0);
    public BufferedImage player;

    public void getPlayerImage() {
        try {
            player = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/com/Dylan/resources/player/player1.png")));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics g) {
        g.drawImage(player, (int) pos.x, (int) pos.y, Render.tileSize, Render.tileSize, null);
    }
}
