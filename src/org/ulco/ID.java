package org.ulco;

public class ID {
    //static public int ID = 0;
    private int Id = 0;
    /** Constructeur privé */
    private ID()
    {}

    /** Instance unique pré-initialisée */
    private static ID INSTANCE = new ID();

    /** Point d'accès pour l'instance unique du singleton */
    public static ID getInstance()
    {	return INSTANCE;
    }

    public int getId(){
        return ++Id;
    }
}