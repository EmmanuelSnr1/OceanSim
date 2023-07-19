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
public class Sardine extends Fish {

    public Sardine(int x, int y, int age){
        super(x, y, age);
    }
    @Override
    public Creature act(Field theField){
        //breeding
        Sardine newS = (Sardine) breed(theField);
        if(!findFood(theField)){
            nutrition-= ModelConstants.PLANKTON_NUTRITION_VALUE;
            super.move(theField);
            theField.place(this, location);
        }
        //aging
        super.incrementAge();
        if(age>=ModelConstants.SARDINE_MAX_AGE||nutrition<=0){
            kill(theField);
        }
        
        return newS;
        
    }
    @Override    
    public boolean findFood(Field theField){
        Iterator i = theField.adjacentLocations(location);
        while(i.hasNext()){
            Location loc = (Location) i.next();
            Creature c = theField.getObjectAt(loc);
            if(c instanceof Plankton){
                c.kill(theField);
                theField.place(null, location);
                location = c.getLocation();
                theField.place(this, location);
                nutrition += ModelConstants.PLANKTON_NUTRITION_VALUE;
                return true;
            }
        }
        return false;
    }
    
    @Override
    public Creature breed(Field theField){
        Random rand = RandomGenerator.getRandom();
        if(age>=ModelConstants.SARDINE_BREED_AGE && rand.nextDouble()<=ModelConstants.SARDINE_BREED_PROB){
            Location loc = theField.freeAdjacentLocation(location);
            if(loc!=null){
                Sardine s1 = new Sardine(loc.getCol(), loc.getRow(), 0);
                return s1;
            }
        }
        return null;
    }
}
    

