package org.ulco;

import java.util.Vector;

public class Document implements Child {
    private Vector<Layer> child;
    private JSON parse;

    public Document() {
        child = new Vector<Layer>();
    }

    public Document(String json) {
        parse = new JSON();
        String[] separators = new String[] {"layers", "}"};
        child = parse.parseItems(json, separators);
    }

    public String[] getTypes(){
        return new String[]{"layers"};
    }

    public String getType () {
        return "document";
    }

    public Vector<Layer> getChildren(){
        return child;
    }

    public String getTypeOFContainer() {
        return "documents";
    }

    public Layer createLayer() {
        Layer layer = new Layer();
        child.add(layer);
        return layer;
    }

    public int getLayerNumber() {
        return child.size();
    }

    public int getObjectNumber() {
        int size = 0;

        for (int i = 0; i < child.size(); ++i) {
            size += child.elementAt(i).getObjectNumber();
        }
        return size;
    }
}
