package org.ulco;
import java.lang.reflect.*;import java.lang.reflect.*;
public class JSON {

    static public GraphicsObject parse(String json) {
        GraphicsObject o = null;
        String str = json.replaceAll("\\s+", "");
        String type = str.substring(str.indexOf("type") + 5, str.indexOf(","));
        String typeClass = type.substring(0, 1).toUpperCase() + type.substring(1);
        Class classe=null;
        try {
            classe = Class.forName("org.ulco."+typeClass);
            Constructor constructeur = classe.getConstructor(String.class);
            o = (GraphicsObject) constructeur.newInstance(str);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return o;
    }

    static public Group parseGroup(String json) {
        return new Group(json);
    }

    static public Layer parseLayer(String json) {
        return new Layer(json);
    }

    static public Document parseDocument(String json) {
        return new Document(json);
    }
}