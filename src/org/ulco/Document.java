package org.ulco;

import java.util.Vector;

public class Document implements Interface {
    private Vector<Layer> m_layers;
    private Parse parse;

    public Document() {
        m_layers = new Vector<Layer>();
    }

    public Document(String json) {
        parse = new Parse();
        Vector<String> separators = new Vector<String>();
        separators.add("layers");
        separators.add("}");
        m_layers = parse.parseItems(json, separators);
    }

    public Layer createLayer() {
        Layer layer = new Layer();
        m_layers.add(layer);
        return layer;
    }

    public int getLayerNumber() {
        return m_layers.size();
    }

    public int getObjectNumber() {
        int size = 0;

        for (int i = 0; i < m_layers.size(); ++i) {
            size += m_layers.elementAt(i).getObjectNumber();
        }
        return size;
    }

    public Vector<Layer> getLayer(){
        return this.m_layers;
    }

    public String toJson() {
        String str = "{ type: document, layers: { ";
        for (int i = 0; i < m_layers.size(); ++i) {
            Layer element = m_layers.elementAt(i);
            str += element.toJson();
            if (i < m_layers.size() - 1) {
                str += ", ";
            }
        }
        return str + " } }";
    }
}
