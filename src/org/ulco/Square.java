package org.ulco;

public class Square extends Rectangle {

    public Square(Point center, double length) {
        this.m_origin = center;
        this.m_width = length;
    }

    public Square(String json) {
        this.myParse = new JSON();
        m_origin = this.myParse.parsePoint(json, "length");
        m_width = this.myParse.parseDouble(json, "length", "}");
        m_height = this.myParse.parseDouble(json, "length", "}");
    }

    public GraphicsObject copy() {
        return new Square(this.m_origin.copy(), this.m_width);
    }

    /*public Point getOrigin() { return this.m_origin; }*/

    public String toString() {
        return "square[" + this.m_origin.toString() + "," + this.m_width + "]";
    }

    @Override
    public String getType () {
        return "square";
    }
}