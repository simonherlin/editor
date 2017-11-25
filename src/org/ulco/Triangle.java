package org.ulco;

public class Triangle extends GraphicsObject {
    protected Point origin;
    private double base;
    private double height;
    private JSON myParse;

    public Triangle(Point center, double height, double base) {
        this.origin = center;
        this.height = height;
        this.base = base;
    }

    public Triangle(String json) {
        this.myParse = new JSON();
        this.origin = this.myParse.parsePoint(json, "height");
        this.height = this.myParse.parseDouble(json, "height", "base");
        this.base = this.myParse.parseDouble(json, "base", "}");
    }

    public GraphicsObject copy() {
        return new Triangle(this.origin.copy(), this.height, this.base);
    }

    public Point getOrigin() { return this.origin; }

    Point center() {
        return this.origin;
    }

    void move(Point delta) { origin.move(delta); }

    public String getType () {
        return "triangle";
    }

    public String toString() {
        return "rectangle[" + this.origin.toString() + "," + this.height + "," + this.base + "]";
    }

    public double getBase(){
        return this.base;
    }

    public double getHeight(){
        return this.height;
    }
}
