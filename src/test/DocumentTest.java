package test;

import junit.framework.TestCase;
import org.junit.Test;
import org.ulco.*;

public class DocumentTest extends TestCase {

    @Test
    public void testSelect() throws Exception {
        Document document = new Document();
        Layer layer = document.createLayer();
        StructureTreatment structureTreatment = new StructureTreatment();
        Circle c = new Circle(new Point(2, 8), 10);

        layer.add(c);

        assertTrue(structureTreatment.select(new Point(1,1), 8, document).size() == 1);
        assertTrue(structureTreatment.select(new Point(1,1), 8, document).firstElement().getID() == c.getID());
    }

    @Test
    public void testSelect2() throws Exception {
        Document document = new Document();
        Layer layer = document.createLayer();
        StructureTreatment dataManagement = new StructureTreatment();
        Circle c = new Circle(new Point(2, 8), 10);
        Square s = new Square(new Point(-2, -3), 3);

        layer.add(c);
        layer.add(s);

        assertTrue(dataManagement.select(new Point(1,1), 8, document).size() == 2);
    }

    @Test
    public void testJSON() throws Exception {
        Document document = new Document();
        Layer l1 = document.createLayer();
        Square s = new Square(new Point(0, 0), 5);
        Circle c1 = new Circle(new Point(5, 5), 4);
        Layer l2 = document.createLayer();
        Rectangle r = new Rectangle(new Point(-5, 1), 4, 2);
        Circle c2 = new Circle(new Point(-4, 8), 1);

        l1.add(s);
        l1.add(c1);
        l2.add(r);
        l2.add(c2);
        assertEquals(document.toJson(), "{ type: document, layers: { { type: layer, objects : { { type: square, center: { type: point, x: 0.0, y: 0.0 }, length: 5.0 }, { type: circle, center: { type: point, x: 5.0, y: 5.0 }, radius: 4.0 } }, groups : {  } }, { type: layer, objects : { { type: rectangle, center: { type: point, x: -5.0, y: 1.0 }, height: 4.0, width: 2.0 }, { type: circle, center: { type: point, x: -4.0, y: 8.0 }, radius: 1.0 } }, groups : {  } } } }");
    }

    @Test
    public void testJSON2() throws Exception {
        Document document = new Document();
        Layer l1 = document.createLayer();
        Square s = new Square(new Point(0, 0), 5);
        Circle c1 = new Circle(new Point(5, 5), 4);
        Layer l2 = document.createLayer();
        Rectangle r = new Rectangle(new Point(-5, 1), 4, 2);
        Circle c2 = new Circle(new Point(-4, 8), 1);

        l1.add(s);
        l1.add(c1);
        l2.add(r);
        l2.add(c2);
        assertEquals(document.toJson(), "{ type: document, layers: { { type: layer, objects : { { type: square, center: { type: point, x: 0.0, y: 0.0 }, length: 5.0 }, { type: circle, center: { type: point, x: 5.0, y: 5.0 }, radius: 4.0 } }, groups : {  } }, { type: layer, objects : { { type: rectangle, center: { type: point, x: -5.0, y: 1.0 }, height: 4.0, width: 2.0 }, { type: circle, center: { type: point, x: -4.0, y: 8.0 }, radius: 1.0 } }, groups : {  } } } }");
    }

    public void testSize() throws Exception {

        Document document = new Document();
        Layer l = document.createLayer();
        Group g = new Group();
        Square s = new Square(new Point(0,0), 5);
        Circle c = new Circle(new Point(5,5), 4);
        Group g2 = new Group();
        Rectangle r = new Rectangle(new Point(-6,10), 5.2, 9);

        g.add(s);
        g.add(c);
        g2.add(g);
        g2.add(r);
        l.add(g2);
        assertEquals(l.size(), 3);
    }

    @Test
    public void testConstructorGrid() throws Exception {
        Document document = new Document();
        BuilderDocument BDoc = new BuilderDocument(document);
        document = BDoc.constDocument(new Point(0,0), 3, 3, 5);

        assertEquals(document.getObjectNumber(), 9);
        assertEquals(document.getLayerNumber(), 1);
    }

    @Test
    public void testConstructorCircle() throws Exception {
        Document document = new Document();
        BuilderDocument BDoc = new BuilderDocument(document);
        document = BDoc.constDocument(new Point(0,0), 4, 3., 4.);
        assertEquals(document.getObjectNumber(), 4);
    }
}