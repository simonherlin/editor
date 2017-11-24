package org.ulco;

import java.util.Vector;

public class Group extends GraphicsObject implements Child{
    private Vector<GraphicsObject> child;
    private JSON myParse;

    public Group() {
        super();
        child = new Vector<GraphicsObject>();
    }

    public Group(String json) {
        super();
        this.myParse = new JSON();
        String[] separators = new String[] {"objects", "groups", "}"};
        child = this.myParse.parseItems(json, separators);
    }

    public String getTypeOFContainer(){
        return "groups";
    }

    protected boolean isObject() {
        return false;
    }

    Point center() {
        return null;
    }

    public String getType() {
        return "group";
    }

    public Vector<GraphicsObject> getChildren() {
        return child;
    }

    public String[] getTypes() {
        return new String[] {"objects", "groups"};
    }

    public void add(Object object) {
        addObject((GraphicsObject) object);
    }

    private void addObject(GraphicsObject object) {
        child.add(object);
    }

    public boolean isClosed(Point pt, double distance) {
        int i = 0;
        boolean close;
        do {
            GraphicsObject element = child.elementAt(i);
            close = element.isClosed(pt, distance);
            i++;
        } while (close != true && i != child.size());
        return close;
    }

    public Group copy() {
        Group g = new Group();
        for (Object o : child) {
            GraphicsObject element = (GraphicsObject) (o);
            g.add(element.copy());
        }
        return g;
    }

    public void move(Point delta) {
        for (Object o : child) {
            GraphicsObject element = (GraphicsObject) (o);
            element.move(delta);
        }
    }

    public String toString() {
        return this.makeDataToExport();
    }

    private String makeDataToExport (){
        String begin, middle, end;
            begin = "group[[";
            middle = "],[";
            end = "]]";
        return makeStringToExport(begin, middle, end);
    }

    private String makeStringToExport(String begin, String middle, String end){
        for (GraphicsObject element : child) {
            if (element.isObject()) {
                begin += element.toString()+", ";
            } else {
                middle +=  element.toString();
            }
        }
        return begin.substring(0, begin.length() -2) + middle + end;
    }

    public int getID() {
        return super.getID();
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