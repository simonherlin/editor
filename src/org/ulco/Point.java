package org.ulco;

public class Point implements Interface {
    private double x;
    private double m_y;

    public Point(double x, double y) {
        this.x = x;
        m_y = y;
    }

    public Point(String json) {
        String str = json.replaceAll("\\s+","");
        int xIndex = str.indexOf("x");
        int separatorIndex = str.indexOf(",", xIndex + 2);
        int yIndex = str.lastIndexOf("y");
        int endIndex = str.lastIndexOf("}");
        x = Double.parseDouble(str.substring(xIndex + 2, separatorIndex));
        m_y = Double.parseDouble(str.substring(yIndex + 2, endIndex));
    }

    public String getType() {
        return "point";
    }

    public String getTypeOFContainer() {
        return "point";
    }

    public Point copy() {
        return new Point(x, m_y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return m_y;
    }

    void move(Point delta){
        x += delta.getX();
        m_y += delta.getY();
    }

    public String toString() {
        return "point[" + x + "," + m_y + "]";
    }

    public double distance(Point p){
        return Math.sqrt(Math.pow(getX() - p.getX(),2) + Math.pow(getY() - p.getY(), 2));
    }
}
