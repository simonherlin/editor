package org.ulco;

import java.util.Vector;

public class Parse {

    public <T> Vector<T> parse(String itemsStr) {
        Vector<T> items = new Vector<>();
        while (!itemsStr.isEmpty()) {
            String itemStr;
            int separatorIndex = searchSeparator(itemsStr);
            if (separatorIndex == -1){
                itemStr = itemsStr;
                itemsStr = "";
            }
            else {
                itemStr = itemsStr.substring(0, separatorIndex);
                itemsStr = itemsStr.substring(separatorIndex + 1);
            }
            items.add((T) JSON.parse(itemStr));
        }
        return items;
    }

    public <T> Vector<T> parseItems(String itemsStr, Vector<String> separators) {
        itemsStr = replace(itemsStr);
        Vector<T> items = new Vector<>();
        for (int i = 0; i < separators.size() - 1; i++) {
            int begin = itemsStr.indexOf(separators.elementAt(i));
            if (begin == -1) {
                continue;
            }
            begin = begin + separators.elementAt(i).length() + 2;
            for (int j = i + 1; j < separators.size(); j++) {
                int end;
                if (j == separators.size() - 1) {
                    end = itemsStr.lastIndexOf(separators.elementAt(j)) - 1;
                } else {
                    end = itemsStr.indexOf(separators.elementAt(j)) - 2;
                }
                if (end < 0) {
                    continue;
                }
                String substring = itemsStr.substring(begin, end);
                items.addAll(parse(substring));
                break;
            }
        }
        return items;
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
}
