package com.Dylan;

import com.Dylan.calculations.Boolean2D;
import com.Dylan.calculations.Vector2D;
import com.Dylan.input.UserInput;
import com.Dylan.render.Render;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player {
    public Vector2D pos = new Vector2D(0, 0);
    public Vector2D renderPos = new Vector2D(0, 0);
    public BufferedImage player;
    private UserInput userInput;
    public Boolean2D movWorld = new Boolean2D(false, false);

    public void init(UserInput userInput) {
        this.userInput = userInput;
    }

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
        g.drawImage(player, (int) renderPos.x, (int) renderPos.y, Render.renderSize, Render.renderSize, null);
    }

    public Boolean2D update() {
        if(this.userInput.keyboard.getLeft()) {
            this.pos.x -= Render.speed * Render.scale;
        }

        if(this.userInput.keyboard.getRight()) {
            this.pos.x += Render.speed * Render.scale;
        }

        if(this.userInput.keyboard.getUp()) {
            this.pos.y -= Render.speed * Render.scale;
        }

        if(this.userInput.keyboard.getDown()) {
            this.pos.y += Render.speed * Render.scale;
        }

        this.movWorld.x = this.pos.x >= Render.WIDTH / 2.00 - Render.tileSize;
        this.movWorld.y = this.pos.y >= Render.HEIGHT / 2.00 - Render.tileSize;

        if(!movWorld.x) {
            if (this.userInput.keyboard.getLeft()) {
                this.renderPos.x -= Render.speed * Render.scale;
            }

            if (this.userInput.keyboard.getRight()) {
                this.renderPos.x += Render.speed * Render.scale;
            }
        }
        if(!movWorld.y) {
            if(this.userInput.keyboard.getUp()) {
                this.renderPos.y -= Render.speed * Render.scale;
            }

            if(this.userInput.keyboard.getDown()) {
                this.renderPos.y += Render.speed * Render.scale;
            }
        }

        this.userInput.keyboard.update();
        return movWorld;
    }
}
