package org.ulco;

abstract public class GraphicsObject {
    private int m_ID;

    public GraphicsObject() {
        m_ID = ++ID.ID;
    }

    abstract public GraphicsObject copy();

    abstract boolean isClosed(Point pt, double distance);

    abstract protected boolean isObject();

    abstract void move(Point delta);

    abstract public String toJson();

    abstract public String toString();

    public int size(){return 1; }

    public int getID() {
        return m_ID;
    }
}