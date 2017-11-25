package org.ulco;

abstract public class GraphicsObject implements Interface {
    private int m_ID;
    private String borderColor;
    private String insideColor;

    public GraphicsObject() {
        this.m_ID  = ID.getInstance().getId();
        this.borderColor = "aucune";
        this.insideColor = "aucune";
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

    public String getBorderColor() {
        return borderColor;
    }

    public String getInsideColor() {
        return insideColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public void setInsideColor(String insideColor){
        this.insideColor = insideColor;
    }

    abstract public GraphicsObject copy();

    protected boolean isObject(){return true;};

    abstract Point center();

    abstract void move(Point delta);

    abstract public String toString();
}