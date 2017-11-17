package org.ulco;

import java.util.Vector;

public class BuilderDocument {

    public Document constDocument(Point origin, int line, int column, double length, Document doc) {
        Vector<Layer> m_layers = doc.getLayer();
        m_layers = new Vector<Layer>();

        Layer layer = doc.createLayer();

        for (int indexX = 0; indexX < column; ++indexX) {
            for (int indexY = 0; indexY < line; ++indexY) {
                layer.add(new Square(new Point(origin.getX() + indexX * length, origin.getY() + indexY * length), length));
            }
        }
        return doc;
    }

    public Document constDocument(Point center, int number, double radius, double delta, Document doc) {
        Vector<Layer> m_layers = doc.getLayer();
        m_layers = new Vector<Layer>();

        Layer layer = doc.createLayer();

        for (int index = 0; index < number; ++index) {
            layer.add(new Circle(center, radius + index * delta));
        }
        return doc;
    }
}
