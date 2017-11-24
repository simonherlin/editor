package org.ulco;

abstract public class GraphicsObject implements Interface {
    private int m_ID;

    public GraphicsObject() {
        m_ID  = ID.getInstance().getId();
    }

    public String getTypeOFContainer() {
        return "objects";
    }

    public boolean isClosed(Point pt, double distance){
        return center().distance(pt) <= distance;
    }

    public int size(){return 1; }

    public int getID() {
        return m_ID;
    }

    abstract public GraphicsObject copy();

    protected boolean isObject(){return true;};

    abstract Point center();

    abstract void move(Point delta);

    abstract public String toString();
}