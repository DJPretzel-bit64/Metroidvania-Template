package com.Dylan;

import com.Dylan.calculations.Vector2D;
import com.Dylan.render.Render;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player {
    public final Vector2D pos = new Vector2D(Render.WIDTH / 2.0 - Render.tileSize * Render.scale / 2.0, Render.HEIGHT / 2.0 - Render.tileSize * Render.scale / 2.0);
    public BufferedImage player;
    public Shape hitBox = new Rectangle(Render.WIDTH / 2 - Render.tileSize * Render.scale / 2, Render.HEIGHT / 2 - Render.tileSize * Render.scale / 2, Render.tileSize * Render.scale, Render.tileSize * Render.scale);

    public void getPlayerImage() {
        try {
            player = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/com/Dylan/resources/player/player1.png")));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics g) {
        getPlayerImage();
        g.drawImage(player, (int) pos.x, (int) pos.y, Render.renderSize, Render.renderSize, null);
    }
}
