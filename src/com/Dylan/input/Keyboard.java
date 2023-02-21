package com.Dylan.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    private boolean[] keys = new boolean[66568];
    private boolean left, right, up, down;

    public void update() {
        this.left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
        this.right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_S];
        this.up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
        this.down = keys[KeyEvent.VK_SHIFT] || keys[KeyEvent.VK_R];
    }

    public boolean getDown() {
        return this.down;
    }

    public boolean getUp() {
        return this.up;
    }

    public boolean getLeft() {
        return this.left;
    }

    public boolean getRight() {
        return this.right;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;

    }
}
