package org.ulco;

public class Rectangle extends GraphicsObject {
    protected Point m_origin;
    protected double m_height;
    protected double m_width;
    protected JSON myParse;

    public Rectangle(){}

    public Rectangle(Point center, double height, double width) {
        this.m_origin = center;
        this.m_height = height;
        this.m_width = width;
    }

    public Rectangle(String json) {
        this.myParse = new JSON();
        m_origin = this.myParse.parsePoint(json, "height");
        m_height = this.myParse.parseDouble(json, "height", "width");
        m_width = this.myParse.parseDouble(json, "width", "}");
    }

    public GraphicsObject copy() {
        return new Rectangle(m_origin.copy(), m_height, m_width);
    }

    public Point getOrigin() { return m_origin; }

    Point center() {
        return this.m_origin;
    }

    void move(Point delta) { m_origin.move(delta); }

    public String toString() {
        return "rectangle[" + m_origin.toString() + "," + m_height + "," + m_width + "]";
    }

    public double getHeight () {
        return this.m_height;
    }

    public double getWidth () {
        return this.m_width;
    }

    public String getType () {
        return "rectangle";
    }
}