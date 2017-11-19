package org.ulco;

public class Rectangle extends GraphicsObject {
    protected Point m_origin;
    protected double m_height;
    protected double m_width;
    protected Parse myParse;

    public Rectangle(){}

    public Rectangle(Point center, double height, double width) {
        this.m_origin = center;
        this.m_height = height;
        this.m_width = width;
    }

    public Rectangle(String json) {
        this.myParse = new Parse();
        m_origin = this.myParse.parsePoint(json, "height");
        m_height = this.myParse.parseDouble(json, "height", "width");
        m_width = this.myParse.parseDouble(json, "width", "}");
    }

    public GraphicsObject copy() {
        return new Rectangle(m_origin.copy(), m_height, m_width);
    }

    public Point getOrigin() { return m_origin; }

    Point center() {
        return new Point(m_origin.getX() + m_width / 2, m_origin.getY() + m_height / 2);
    }

    void move(Point delta) { m_origin.move(delta); }

    public String toJson() {
        return "{ type: rectangle, center: " + m_origin.toJson() + ", height: " + this.m_height + ", width: " + this.m_width + " }";
    }

    public String toString() {
        return "rectangle[" + m_origin.toString() + "," + m_height + "," + m_width + "]";
    }
}