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
public abstract class Creature {
    protected Location location;
    protected int age;
    protected boolean isAlive;
    
    public Creature(int x, int y, int age){
        location = new Location(x, y);
        this.age = age;
        isAlive = true;
    }
    public abstract Creature act(Field theField);
    
    public Location getLocation(){
        return location;
    }
    
    public void setLocation(Location loc){
        location = loc;
    }
    
    public void incrementAge(){
        age++;
    }
    public boolean getAlive(){
        return isAlive;
    }
    
    public void kill(Field theField){
        isAlive = false;
        theField.place(this, location);
    }
    
    public abstract Creature breed(Field theField);
}
