package com.Dylan;

import com.Dylan.calculations.Vector2D;
import com.Dylan.input.UserInput;
import com.Dylan.render.Render;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Game {
    public Player player = new Player();
    public World world = new World();
    Vector2D position = new Vector2D(0.0, 1000.0);
    Vector2D newPos = new Vector2D(0.0, 0.0);
    Vector2D difference = new Vector2D(0.0, 0.0);
    UserInput userInput;
    double gravity = 0.1;
    double velocity = 0;

    public void init(UserInput userInput) {
        this.world.init();
        this.world.update(position);
        this.newPos = this.position;
        this.userInput = userInput;
    }

    public void render(Graphics g) {
        world.render(g);
        player.render(g);
    }

    public void update() {
        this.userInput.keyboard.update();

        if(this.userInput.keyboard.getSpace() && velocity == 0)
            velocity = -5;

        if(this.userInput.keyboard.getLeft()) {
            this.newPos.x += Render.speed * Render.scale;
        }

        if(this.userInput.keyboard.getRight())
            this.newPos.x -= Render.speed * Render.scale;

        for(int i = 0; i < Render.WORLD_SIZE; i ++) {
            for(int j = 0; j < Render.WORLD_SIZE; j ++) {
                if(this.world.lvlBoolean[i][j]) {
                    if(this.player.hitBox.intersects((Rectangle2D) this.world.hitBox[i][j])) {
                        this.velocity = 0;
                        this.difference = Vector2D.diff(this.position, this.newPos);
                        this.difference = Vector2D.normalize(this.difference);
                        while(this.player.hitBox.intersects((Rectangle2D) this.world.hitBox[i][j])) {
                            this.newPos = Vector2D.add(this.newPos, this.difference);
                            this.world.update(this.position);
                        }
                    }
                }
            }
        }

        this.position = this.newPos;

        this.newPos.y -= this.velocity;

        this.velocity += this.gravity;

        this.world.update(this.position);
    }
}
