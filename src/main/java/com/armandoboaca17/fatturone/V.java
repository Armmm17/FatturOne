package com.armandoboaca17.fatturone;


import java.util.ArrayList;
import java.util.List;

public class V {
    public static int WIDTH = 720;
    public static int HEIGHT = 600;

    public static String DBURL = "jdbc:mysql://localhost:3306/dipendenti";
    public static String DBUSER = "root";
    public static String DBPASSWORD = "";
    public static int ORDER_NUMBER;

    public static List<OrderDetailResult> LISTAORDINI = new ArrayList<>();
}
