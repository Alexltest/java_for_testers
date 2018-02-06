package ru.jft.sandbox;

class HelloWorld {
    public static void main(String[] args) {
        hello("world");
        hello("alex");

        Rectangle w = new Rectangle(7, 8);
        System.out.println("Площадь равна " + w.square());
    }

    public static void hello(String some) {
        System.out.println("Hello " + some);
    }

}