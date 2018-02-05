package ru.jft.sandbox;

class Second {
    public static void main(String[] args) {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 4);
        double d = distance(p1, p2);
        String msg = String.format("Расстояние между двумя точками с координатами %s %s и %s %s равно %s",
                p1.x, p1.y, p2.x, p2.y, d);
        System.out.println(msg);
    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow((p1.x - p2.x), 2) + Math.pow((p1.y - p2.y), 2));
    }
}