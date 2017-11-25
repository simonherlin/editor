package test;

import junit.framework.TestCase;
import org.junit.Test;
import org.ulco.*;

public class TriangleTest extends TestCase {

    @Test
    public void testType() throws Exception {
        Triangle t = new Triangle(new Point(0, 0), 3, 7);

        assertTrue(t instanceof Triangle);
        assertTrue(t instanceof GraphicsObject);
    }

    @Test
    public void testJson() throws Exception {
        Triangle t = new Triangle(new Point(0, 0), 3, 7);
        JSON myJson = new JSON();

        assertEquals(myJson.parseToJSON(t), "{ type: triangle, center: { type: point, x: 0.0, y: 0.0 }, height: 3.0, base: 7.0 }");
    }

    @Test
    public void testCopy() throws Exception {
        Triangle t = new Triangle(new Point(0, 0), 3, 7);
        JSON myJson = new JSON();

        assertEquals(myJson.parseToJSON(t), myJson.parseToJSON(t.copy()));
    }

    @Test
    public void testIsClosed() throws Exception {
        Triangle t = new Triangle(new Point(0, 0), 3, 7);

        assertTrue(t.isClosed(new Point(1, 1), 4));
    }
}