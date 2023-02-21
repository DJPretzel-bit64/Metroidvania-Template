package com.Dylan;

import com.Dylan.calculations.Vector2D;
import com.Dylan.input.UserInput;
import com.Dylan.render.Render;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Game {
    public Player player = new Player();
    public World world = new World();
    Vector2D position = new Vector2D();
    UserInput userInput;
    double gravity = 0.1;
    double velocity = 0;

    public void init(UserInput userInput) {
        world.init();
        this.userInput = userInput;
    }

    public void render(Graphics g) {
        world.render(g);
        player.render(g);
    }

    public void update() {
        this.userInput.keyboard.update();

        velocity += gravity;

        for(int i = 0; i < Render.WORLD_SIZE; i ++) {
            for(int j = 0; j < Render.WORLD_SIZE; j ++) {
                if(this.world.lvlBoolean[i][j])
                    if(this.player.hitBox.intersects((Rectangle2D) this.world.hitBox[i][j]))
                        velocity = 0;
            }
        }

        if(this.userInput.keyboard.getSpace() && velocity == 0)
            velocity = -10;

        if(this.userInput.keyboard.getLeft())
            this.position.x += Render.speed * Render.scale;

        if(this.userInput.keyboard.getRight())
            this.position.x -= Render.speed * Render.scale;

        this.position.y -= velocity;

        world.update(this.position);
    }
}
