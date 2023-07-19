/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ocean;

/**
 *
 * @author Emmanuel
 */
public abstract class Fish extends Creature {
    protected int nutrition;
    public Fish(int x, int y, int age){
        super(x, y, age);
        nutrition = 100;
    }
    
    public void move(Field theField){
        Location loc = theField.freeAdjacentLocation(location);
        if(loc==null){
            kill(theField);
        }
        else{
            theField.place(null, location);
            location = loc;
            theField.place(this, location);
            
        }
    }
    
    public abstract boolean findFood(Field theField);
}
