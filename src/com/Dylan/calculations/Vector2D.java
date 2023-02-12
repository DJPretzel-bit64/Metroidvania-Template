package com.Dylan.calculations;

public class Vector2D {
    public double x;
    public double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D normalize() {
        double length = Math.sqrt(this.x * this.x + this.y * this.y);
        Vector2D v = new Vector2D(this.x / length, this.y / length);

        return v;
    }
}
