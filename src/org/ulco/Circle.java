package org.ulco;

public class Circle extends GraphicsObject {
    private final Point center;
    private final double radius;
    JSON myparse;

    public Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public Circle(String json) {
        this.myparse = new JSON();
        center = this.myparse.parsePoint(json, "radius");
        radius = this.myparse.parseDouble(json, "radius", "}");
    }

    public Point center() {
        return center;
    }

    public GraphicsObject copy() {
        return new Circle(center.copy(), radius);
    }

    public Point getCenter() { return center; }

    void move(Point delta) { center.move(delta); }

    public String toString() {
        return "circle[" + center.toString() + "," + radius + "]";
    }

    public double getRadius(){
        return this.radius;
    }

    public String getType () {
        return "circle";
    }
}