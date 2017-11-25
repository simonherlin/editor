package org.ulco;

public class Rectangle extends GraphicsObject {
    protected Point origin;
    protected double height;
    protected double width;
    protected JSON myParse;

    public Rectangle(){}

    public Rectangle(Point center, double height, double width) {
        this.origin = center;
        this.height = height;
        this.width = width;
    }

    public Rectangle(String json) {
        this.myParse = new JSON();
        origin = this.myParse.parsePoint(json, "height");
        height = this.myParse.parseDouble(json, "height", "width");
        width = this.myParse.parseDouble(json, "width", "}");
    }

    public GraphicsObject copy() {
        return new Rectangle(origin.copy(), height, width);
    }

    public Point getOrigin() { return origin; }

    Point center() {
        return this.origin;
    }

    void move(Point delta) { origin.move(delta); }

    public String toString() {
        return "rectangle[" + origin.toString() + "," + height + "," + width + "]";
    }

    public double getHeight () {
        return this.height;
    }

    public double getWidth () {
        return this.width;
    }

    public String getType () {
        return "rectangle";
    }
}