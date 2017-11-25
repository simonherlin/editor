package org.ulco;

public class Square extends Rectangle {

    public Square(Point center, double length) {
        this.origin = center;
        this.width = length;
    }

    public Square(String json) {
        this.myParse = new JSON();
        origin = this.myParse.parsePoint(json, "length");
        width = this.myParse.parseDouble(json, "length", "}");
        height = this.myParse.parseDouble(json, "length", "}");
    }

    public GraphicsObject copy() {
        return new Square(this.origin.copy(), this.width);
    }

    /*public Point getOrigin() { return this.origin; }*/

    public String toString() {
        return "square[" + this.origin.toString() + "," + this.width + "]";
    }

    @Override
    public String getType () {
        return "square";
    }
}