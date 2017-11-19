package org.ulco;

import java.util.Vector;

public class Group extends GraphicsObject {
    private Vector<GraphicsObject> m_objectList;
    private Parse myParse;

    public Group() {
        super();
        m_objectList = new Vector<GraphicsObject>();
    }

    public Group(String json) {
        this.myParse = new Parse();
        Vector<String> separators = new Vector<String>();
        separators.add("objects");
        separators.add("groups");
        separators.add("}");
        m_objectList = this.myParse.parseItems(json, separators);
    }

    protected boolean isObject() {
        return false;
    }

    Point center() {
        return null;
    }

    public void add(Object object) {
        addObject((GraphicsObject) object);
    }

    private void addObject(GraphicsObject object) {
        m_objectList.add(object);
    }

    public boolean isClosed(Point pt, double distance) {
        int i = 0;
        boolean close;
        do {
            GraphicsObject element = m_objectList.elementAt(i);
            close = element.isClosed(pt, distance);
            i++;
        } while (close != true && i != m_objectList.size());
        return close;
    }

    public Group copy() {
        Group g = new Group();
        for (Object o : m_objectList) {
            GraphicsObject element = (GraphicsObject) (o);
            g.add(element.copy());
        }
        return g;
    }

    public void move(Point delta) {
        for (Object o : m_objectList) {
            GraphicsObject element = (GraphicsObject) (o);
            element.move(delta);
        }
    }

    public String toJson() {
        return this.makeDataToExport(false);
    }

    public String toString() {
        return this.makeDataToExport(true);
    }

    private String makeDataToExport (boolean toString){
        String begin, middle, end;
        if (toString){
            begin = "group[[";
            middle = "],[";
            end = "]]";
        }
        else{
            begin = "{ type: group, objects : { ";
            middle = " }, groups : { ";
            end = " } }";
        }
        return makeStringToExport(begin, middle, end, toString);
    }

    private String makeStringToExport(String begin, String middle, String end, boolean toString){
        for (GraphicsObject element : m_objectList) {
            if (element.isObject()) {
                begin += ((toString) ? element.toString() : element.toJson()) + ", ";
            }
            else {
                middle += toString ? element.toString() : element.toJson();
            }
        }
        return begin.substring(0, begin.length() -2) + middle + end;
    }

    public int getID() {
        return super.getID();
    }

    public Vector<GraphicsObject> getObjects(){
        Vector<GraphicsObject> objects = new Vector<GraphicsObject>();
        for(GraphicsObject obj : m_objectList){
            if((obj.isObject()))
                objects.add(obj);
        }
        return objects;
    }

    public Vector<GraphicsObject> getList(){
        return this.m_objectList;
    }

    public int size() {
        int size = 0;
        for (int i = 0; i < m_objectList.size(); ++i) {
            GraphicsObject element = m_objectList.elementAt(i);
            size += element.size();
        }
        return size;
    }
}