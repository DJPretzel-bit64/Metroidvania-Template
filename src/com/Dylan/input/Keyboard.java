package com.Dylan.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    private final boolean[] keys = new boolean[66568];
    private boolean left, right, space;

    public void update() {
        this.left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
        this.right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_S];
        this.space = keys[KeyEvent.VK_SPACE];
    }

    public boolean getSpace() {
        return this.space;
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
