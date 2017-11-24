package org.ulco;

import java.util.Vector;

public interface Child extends Interface {
    String[] getTypes();
    Vector<?> getChildren();
}
