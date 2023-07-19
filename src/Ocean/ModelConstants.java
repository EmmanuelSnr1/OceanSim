/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ocean;

/**
 *
 * @author Victoria
 */
public class ModelConstants {
    public static int SIMULATION_LENGTH=1000;
    public static int DEFAULT_DEPTH=100;
    public static int DEFAULT_WIDTH=100;
    

    public static double PLANKTON_CREATION=0.05;
    public static int PLANKTON_MAX_AGE=100;
    public static double PLANKTON_BREED_PROB=0.04;
    public static int PLANKTON_BREED_AGE=0;
    public static int PLANKTON_NUTRITION_VALUE=4;
    
    public static double SARDINE_CREATION=0.05;
    public static int SARDINE_MAX_AGE=75;
    public static double SARDINE_BREED_PROB=0.1;
    public static int SARDINE_BREED_AGE=12;
    public static int SARDINE_NUTRITION_VALUE=14;
    
    public static double SHARK_CREATION=0.05;
    public static int SHARK_MAX_AGE=150;
    public static double SHARK_BREED_PROB=0.1;
    public static int SHARK_BREED_AGE=25;

    
    public static void setConstants(String[][] s){
        PLANKTON_CREATION= Double.parseDouble(s[0][0]);
        PLANKTON_MAX_AGE = Integer.parseInt(s[0][1]);
        PLANKTON_BREED_PROB=Double.parseDouble(s[0][2]);
        PLANKTON_BREED_AGE=Integer.parseInt(s[0][3]);
        PLANKTON_NUTRITION_VALUE=Integer.parseInt(s[0][4]);
    
        SARDINE_CREATION=Double.parseDouble(s[1][0]);
        SARDINE_MAX_AGE= Integer.parseInt(s[1][1]);
        SARDINE_BREED_PROB=Double.parseDouble(s[1][2]);
        SARDINE_BREED_AGE=Integer.parseInt(s[1][3]);
        SARDINE_NUTRITION_VALUE=Integer.parseInt(s[1][4]);
    
        SHARK_CREATION=Double.parseDouble(s[2][0]);
        SHARK_MAX_AGE= Integer.parseInt(s[2][1]);
        SHARK_BREED_PROB= Double.parseDouble(s[2][2]);
        SHARK_BREED_AGE=Integer.parseInt(s[2][3]);

        
    }
}
