package org.ulco;

import java.util.Vector;

public class Layer implements Child {
    private Vector<GraphicsObject> child;
    private int id;
    private JSON myParse;

    public Layer() {
        child = new Vector<GraphicsObject>();
        id = ID.getInstance().getId();
    }

    public Layer(String json) {
        this.myParse = new JSON();
        String[] separators = new String[] {"objects", "groups", "}"};
        child = this.myParse.parseItems(json, separators);
    }

    public Vector<GraphicsObject> getChildren(){
        return this.child;
    }

    public String getType(){
        return "layer";
    }

    public String[] getTypes () {
        return new String[]{"objects", "groups"};
    }

    public String getTypeOFContainer(){
        return "layers";
    }

    public void add(GraphicsObject o) {
        child.add(o);
    }

    public GraphicsObject get(int index) {
        return child.elementAt(index);
    }

    public int getObjectNumber() {
        return child.size();
    }

    public int getID() {
        return id;
    }

    public int size() {
        int size = 0;
        for (int i = 0; i < child.size(); ++i) {
            GraphicsObject element = child.elementAt(i);
            size += element.size();
        }
        return size;
    }
}
