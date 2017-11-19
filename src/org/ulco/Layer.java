package org.ulco;

import java.util.Vector;

public class Layer implements Interface {
    private Vector<GraphicsObject> m_list;
    private int m_ID;
    private Parse myParse;

    public Layer() {
        m_list = new Vector<GraphicsObject>();
        m_ID = ID.getInstance().getId();
    }

    public Layer(String json) {
        this.myParse = new Parse();
        Vector<String> separators = new Vector<String>();
        separators.add("objects");
        separators.add("groups");
        separators.add("}");
        m_list = this.myParse.parseItems(json, separators);
    }

    public void add(GraphicsObject o) {
        m_list.add(o);
    }

    public GraphicsObject get(int index) {
        return m_list.elementAt(index);
    }

    public int getObjectNumber() {
        return m_list.size();
    }

    public int getID() {
        return m_ID;
    }

    public String toJson() {
        String str = "{ type: layer, objects : { ";
        for (int i = 0; i < m_list.size(); ++i) {
            GraphicsObject element = m_list.elementAt(i);
            if (element.isObject()) {
                str += element.toJson();
                if (i < m_list.size() - 1) {
                    str += ", ";
                }
            }
        }
        str += " }, groups : { ";
        for (int i = 0; i < m_list.size(); ++i) {
            GraphicsObject element = m_list.elementAt(i);
            if (!element.isObject())
                str += element.toJson();
        }
        return str + " } }";
    }

    public Vector<GraphicsObject> getLayer(){
        return this.m_list;
    }

    public int size() {
        int size = 0;
        for (int i = 0; i < m_list.size(); ++i) {
            GraphicsObject element = m_list.elementAt(i);
            size += element.size();
        }
        return size;
    }
}
