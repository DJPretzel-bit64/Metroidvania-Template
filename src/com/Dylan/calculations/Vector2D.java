package com.Dylan.calculations;

public class Vector2D {
    public double x;
    public double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Vector2D() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2D normalize() {
        double length = Math.sqrt(this.x * this.x + this.y * this.y);

        return new Vector2D(this.x / length, this.y / length);
    }
}
