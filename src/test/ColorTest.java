package test;

import junit.framework.TestCase;
import org.junit.Test;
import org.ulco.*;

public class ColorTest extends TestCase {

    @Test
    public void testColor1() throws Exception {
        Rectangle r = new Rectangle(new Point(0,0), 4.0,7.0);
        r.setBorderColor("Bleu");
        r.setInsideColor("Red");
        Square s = new Square(new Point(0,0), 5);
        Circle c = new Circle(new Point(5,5), 4);
        JSON myJson = new JSON();

        assertEquals(myJson.parseToJSON(r), "{ type: rectangle, center: { type: point, x: 0.0, y: 0.0 }, height: 4.0, width: 7.0, color inside: Red, color border: Bleu }");
    }

    @Test
    public void testColor2() throws Exception {
        Square s = new Square(new Point(0,0), 5);
        s.setBorderColor("Green");
        JSON myJson = new JSON();

        assertEquals(myJson.parseToJSON(s), "{ type: square, center: { type: point, x: 0.0, y: 0.0 }, length: 5.0, color border: Green }");
    }

    @Test
    public void testColor3() throws Exception {
        Circle c = new Circle(new Point(5,5), 4);
        c.setInsideColor("Black");
        JSON myJson = new JSON();

        assertEquals(myJson.parseToJSON(c), "{ type: circle, center: { type: point, x: 5.0, y: 5.0 }, radius: 4.0, color inside: Black }");
    }
}
