package org.ulco;

import java.util.Vector;

public class StructureTreatment {



    public GraphicsObjects select(Point pt, double distance, Document document) {
        GraphicsObjects list = new GraphicsObjects();
        Vector<Layer> listLayer = document.getLayer();

        for (Layer layer : listLayer) {
            list.addAll(this.select(pt, distance,layer));
        }
        return list;
    }

    public GraphicsObjects select(Point pt, double distance, Layer layer) {
        GraphicsObjects list = new GraphicsObjects();
        Vector<GraphicsObject> listObject = layer.getLayer();

        for (GraphicsObject object : listObject) {
            if (object.isClosed(pt, distance)) {
                list.add(object);
            }
        }
        return list;
    }

}
