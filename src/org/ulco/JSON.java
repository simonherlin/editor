package org.ulco;
import java.lang.reflect.*;
import java.util.Vector;

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

    private <T> Vector<T> parseOne(String str) {
        Vector<T> items = new Vector<>();
        while (!str.isEmpty()) {
            String itemStr;
            int separatorIndex = this.searchSeparator(str);
            if (separatorIndex == -1){
                itemStr = str;
                str = "";
            }
            else {
                itemStr = str.substring(0, separatorIndex);
                str = str.substring(separatorIndex + 1);
            }
            items.add((T) JSON.parse(itemStr));
        }
        return items;
    }

    private int searchSeparator(String str) {
        int index = 0;
        int level = 0;
        boolean found = false;

        while (!found && index < str.length()) {
            if (str.charAt(index) == '{') {
                ++level;
                ++index;
            } else if (str.charAt(index) == '}') {
                --level;
                ++index;
            } else if (str.charAt(index) == ',' && level == 0) {
                found = true;
            } else {
                ++index;
            }
        }
        if (found) {
            return index;
        } else {
            return -1;
        }
    }

    private String objectToJSON(GraphicsObject graphicsObject){
        String begin = "center: ";
        begin += this.centerToJSON(graphicsObject.center()) + ", ";
        begin += this.attributToJSON(graphicsObject);
        if (graphicsObject.getBorderColor() != "aucune" || graphicsObject.getInsideColor() != "aucune"){
            begin += colorToJSON(graphicsObject);
        }
        return begin;
    }

    private String colorToJSON(GraphicsObject graphicsObject){
        String color = ", ";
        if (graphicsObject.getInsideColor() != "aucune"){
            color += "color inside: ";
            color += graphicsObject.getInsideColor();
            if (graphicsObject.getBorderColor() != "aucune"){
                color += ", color border: ";
                color += graphicsObject.getBorderColor();
            }
        }
        else{
            color += "color border: " +graphicsObject.getBorderColor();
        }
        return color;
    }

    private String centerToJSON(Point center){
        return "{ type: point, x: " + center.getX() + ", y: " + center.getY() + " }";
    }

    private String attributToJSON(GraphicsObject graphicsObject){
        String output = "";
        switch (graphicsObject.getType()){
            case "square" :
                Square square = (Square) graphicsObject;
                output = "length: " + square.getWidth();
                break;
            case "circle" :
                Circle circle = (Circle) graphicsObject;
                output = "radius: " + circle.getRadius();
                break;
            case "rectangle" :
                Rectangle rectangle = (Rectangle) graphicsObject;
                output = "height: " + rectangle.getHeight() + ", width: " + rectangle.getWidth();
                break;
            case "triangle" :
                Triangle triangle = (Triangle) graphicsObject;
                output = "height: "+ triangle.getHeight() + ", base: " + triangle.getBase();
                break;
        }
        return output;
    }

    private String childrenToJSON(Child child){
        String data ="";
        for (String typeOfChild : child.getTypes()){
            data += specifyChild(typeOfChild, child);
        }
        data = data.substring(0, data.length() - 2);
        data.concat("},");
        return data;
    }

    private String specifyChild(String childType, Child child){
        String beginChild = childType + " : { ", dataChild = "", endChild= " }, ";
        for (Object c : child.getChildren()){
            Interface temp = (Interface) c;
            if (temp.getTypeOFContainer().equals(childType)){
                dataChild += this.parseToJSON((Interface) temp) + ", ";
            }
        }
        if (!dataChild.isEmpty()) {
            dataChild = dataChild.substring(0, dataChild.length() - 2);
        }
        return beginChild + dataChild + endChild;
    }

    public <T> Vector<T> parseItems(String str, String[] separators) {
        str = replace(str);
        Vector<T> items = new Vector<>();
        for (int i = 0; i < separators.length - 1; i++) {
            int begin = str.indexOf(separators[i]);
            if (begin != -1) {
                begin = begin + separators[i].length() + 2;
                for (int j = i + 1; j < separators.length; j++) {
                    int end;
                    if (j == separators.length - 1) {
                        end = str.lastIndexOf(separators[j]) - 1;
                    } else {
                        end = str.indexOf(separators[j]) - 2;
                    }
                    if (end >= 0) {
                        String substring = str.substring(begin, end);
                        items.addAll(parseOne(substring));
                        break;
                    }
                }
            }
        }
        return items;
    }

    public String parseToJSON  (Interface i){
        String begin, content, end;
        begin = "{ type: " + i.getType() + ", ";
        end = " }";
        if (i.getType() != "document"
                && i.getType() != "group"
                && i.getType() != "layer"
                && i.getType() != "point"){
            content = this.objectToJSON((GraphicsObject) i);
        }
        else {
            content = this.childrenToJSON((Child) i);
        }
        return begin + content + end;
    }

    public String replace(String str){
        return str.replaceAll("\\s+", "");
    }

    public Point parsePoint(String pointStr, String separator){
        int begin, end;
        begin = pointStr.indexOf("center") + 7;
        end = pointStr.indexOf(separator) - 1;
        return (Point) JSON.parse(pointStr.substring(begin, end));
    }

    public Double parseDouble(String str, String name, String separator){
        str = replace(str);
        int begin, end;
        begin = str.indexOf(name) + name.length() + 1;
        if (separator.equals("}")){
            end = str.lastIndexOf(separator);
        }
        else {
            end = str.indexOf(separator) - 1;
        }
        return Double.parseDouble(str.substring(begin, end));
    }
}