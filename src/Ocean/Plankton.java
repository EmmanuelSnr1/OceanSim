
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ocean;
import java.util.Random;

/**
 *
 * @author Victoria
 */
public class Plankton extends Creature {
    public Plankton(int x, int y, int age){
        super(x, y, age);
        
    }
    @Override
    public Creature act(Field theField){
        Plankton newP = (Plankton) breed(theField);
        super.incrementAge();
        if(age>=ModelConstants.PLANKTON_MAX_AGE){
            kill(theField);
        }
        return newP;
    }
    
    @Override
    public Creature breed(Field theField){
        Random rand = RandomGenerator.getRandom();
        if(age>=ModelConstants.PLANKTON_BREED_AGE && rand.nextDouble()<=ModelConstants.PLANKTON_BREED_PROB){
            Location loc = theField.freeAdjacentLocation(location);
            if(loc!=null){
                Plankton p1 = new Plankton(loc.getRow(), loc.getCol(), 0);
                return p1;
            }
        }
        return null;
    }   
}
