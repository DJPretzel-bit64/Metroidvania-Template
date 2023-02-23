package com.Dylan.calculations;

public class Vector2D {
    public double x;
    public double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2D normalize(Vector2D v) {
        double magnitude = Math.sqrt(v.x * v.x + v.y * v.y);

        return new Vector2D(v.x / magnitude, v.y / magnitude);
    }

    public static Vector2D diff(Vector2D v1, Vector2D v2) {
        return new Vector2D( v1.x - v2.x, v1.y - v2.y);
    }

    public static Vector2D add(Vector2D v1, Vector2D v2) {
        return new Vector2D(v1.x + v2.x, v1.y + v2.y);
    }
}
