package org.ulco;

public class Circle extends GraphicsObject {
    private final Point m_center;
    private final double m_radius;
    Parse myparse;

    public Circle(Point center, double radius) {
        this.m_center = center;
        this.m_radius = radius;
    }

    public Circle(String json) {
        this.myparse = new Parse();
        m_center = this.myparse.parsePoint(json, "radius");
        m_radius = this.myparse.parseDouble(json, "radius", "}");
    }

    public Point center() {
        return m_center;
    }

    public GraphicsObject copy() {
        return new Circle(m_center.copy(), m_radius);
    }

    public Point getCenter() { return m_center; }

    void move(Point delta) { m_center.move(delta); }

    public String toJson() {
        return "{ type: circle, center: " + m_center.toJson() + ", radius: " + this.m_radius + " }";
    }

    public String toString() {
        return "circle[" + m_center.toString() + "," + m_radius + "]";
    }
}