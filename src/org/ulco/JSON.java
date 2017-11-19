package org.ulco;
import java.lang.reflect.*;
public class JSON {

    static public Interface parse(String json) {
        Object o = null;
        String str = json.replaceAll("\\s+", "");
        String type = str.substring(str.indexOf("type") + 5, str.indexOf(","));
        type = type.substring(0, 1).toUpperCase() + type.substring(1);
        try {
            Class classe = Class.forName("org.ulco." + type);
            Constructor constructeur = classe.getConstructor(String.class);
            o = constructeur.newInstance(str);
        } catch (ClassNotFoundException e) {
        } catch (NoSuchMethodException e) {
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        } catch (IllegalArgumentException e) {
        }
        return (Interface)o;
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