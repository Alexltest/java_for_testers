package ru.jft.sandbox;

public class Rectangle {
    public double a;
    public double b;

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double square() {
        return this.a * this.b;
    }
}
