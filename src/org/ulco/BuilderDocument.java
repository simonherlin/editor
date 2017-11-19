package org.ulco;

import java.util.Vector;

public class BuilderDocument {
    private Document document;
    private  Vector<Layer> m_layers;
    private Layer layer;

    public BuilderDocument(Document doc){
        this.document = doc;
        m_layers = this.document.getLayer();
        m_layers = new Vector<Layer>();
        layer = this.document.createLayer();
    }

    public Document constDocument(Point origin, int line, int column, double length) {
        for (int indexX = 0; indexX < column; ++indexX) {
            for (int indexY = 0; indexY < line; ++indexY) {
                layer.add(new Square(new Point(origin.getX() + indexX * length, origin.getY() + indexY * length), length));
            }
        }
        return this.document;
    }

    public Document constDocument(Point center, int number, double radius, double delta) {
        for (int index = 0; index < number; ++index) {
            layer.add(new Circle(center, radius + index * delta));
        }
        return this.document;
    }
}
