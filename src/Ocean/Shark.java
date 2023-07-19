/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ocean;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author Victoria
 */
public class Shark extends Fish {
    public Shark(int x, int y, int age){
        super(x,y, age);
        
    }
    @Override
    public Creature act(Field theField){
        Shark newSh = (Shark) breed(theField);
        if(!findFood(theField)){
            super.move(theField);
            theField.place(this, location);
            nutrition -= ModelConstants.SARDINE_NUTRITION_VALUE;
        }
        super.incrementAge();
        if(age>=ModelConstants.SHARK_MAX_AGE||nutrition<=0){
            kill(theField);
        }
        
        return newSh;
    }
    @Override
    public boolean findFood(Field theField){
        Iterator i = theField.adjacentLocations(location);
        while(i.hasNext()){
            Location loc = (Location) i.next();
            Creature c = theField.getObjectAt(loc);
            if(c instanceof Sardine){
                c.kill(theField);   
                theField.place(null, location);
                location = c.getLocation();
                theField.place(this, location);
                nutrition += ModelConstants.SARDINE_NUTRITION_VALUE;
                return true;
            }
        }
        return false;
        
    }
    
    @Override
    public Creature breed(Field theField){
        Random rand = RandomGenerator.getRandom();
        if(age>=ModelConstants.SHARK_BREED_AGE && rand.nextDouble()<=ModelConstants.SHARK_BREED_PROB){
            Location loc = theField.freeAdjacentLocation(location);
            if(!(loc==null)){
                Shark sh1 = new Shark(loc.getRow(), loc.getCol(), 0);
                return sh1;
            }
        }
        return null;
    
    }
}
