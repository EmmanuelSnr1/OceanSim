/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ocean;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Victoria
 */
public class Simulator{
    private final Field field;
    private final SimulatorView simView;
    private final ArrayList<Creature> creatures = new ArrayList<>();
    private int currentStep;
    private final ArrayList<Creature> newCreatures = new ArrayList<>();
    private final ArrayList<Creature> dead = new ArrayList<>();
    
    
    public Simulator(int x, int y){
        RandomGenerator.initialiseWithSeed(44);
        if(x>0 && y>0){
            field = new Field(x, y);
            simView = new SimulatorView(x, y);
        }
        else{
            x = ModelConstants.DEFAULT_DEPTH;
            y = ModelConstants.DEFAULT_WIDTH;
            field = new Field(x, y);
            simView = new SimulatorView(x, y);
        }
        simView.getState();
        simView.setColor(Plankton.class, Color.black);
        simView.setColor(Shark.class, Color.green);
        simView.setColor(Sardine.class, Color.red);
        
    }
    
    public void populate(){
        Random rand = RandomGenerator.getRandom();
        double sharkPop = ModelConstants.SHARK_CREATION;
        double sardinePop = ModelConstants.SARDINE_CREATION;
        double planktonPop = ModelConstants.PLANKTON_CREATION;
        
        for(int i=0; i < field.getWidth(); i++){
            for(int j=0; j < field.getDepth(); j++){
                double randomNum = rand.nextDouble();
                boolean randBool = rand.nextBoolean();
                
                if(randomNum <=sharkPop){
                    int age = 0;
                    if(randBool==true){
                        age = rand.nextInt(ModelConstants.SHARK_MAX_AGE);
                    }
                    Shark sh = new Shark(i, j, age);
                    field.place(sh, i, j);
                    creatures.add(sh);
                }
                else if (randomNum > sharkPop && randomNum <= (sharkPop+sardinePop)){
                    int age = 0;
                    if(randBool==true){
                        age = rand.nextInt(ModelConstants.SARDINE_MAX_AGE);
                    }
                    Sardine s = new Sardine(i, j, age);
                    field.place(s, i, j);
                    creatures.add(s);
                }
                else if(randomNum>(sharkPop+sardinePop) && randomNum <=(sharkPop+sardinePop+planktonPop)) {
                    int age = 0;
                    if(randBool==true){
                        age = rand.nextInt(ModelConstants.PLANKTON_MAX_AGE);
                    }
                    Plankton p = new Plankton(i, j, age);
                    field.place(p, i, j);
                    creatures.add(p);
                }

            }
        }
    }
    
    public void start(){
        populate();
        simView.showStatus(1, field);
        simulate(1000);
    }
    
    public void simulate(int steps){
        for(int i=0; i<steps; i++){
            if(simulateOneStep());
        }
        dispose();
    }
    
    public boolean simulateOneStep(){
        Collections.shuffle(creatures, RandomGenerator.getRandom());
        for(Creature c : creatures){
            if(c != null){
                if(c.getAlive()){
                    Creature c1 = c.act(field);
                    field.place(c, c.getLocation());
                    if(c1 != null){
                        newCreatures.add(c1);
                        field.place(c1, c1.getLocation());
                    }
                }
                if(!c.getAlive()){
                    dead.add(c);
                    field.place(null, c.getLocation());
                }
                
            }
        }
       
        creatures.removeAll(dead);
        dead.clear();
        
        creatures.addAll(newCreatures);
        newCreatures.clear();

        simView.getState();
        simView.showStatus(currentStep, field);
        currentStep++;

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(Thread.interrupted()){
            currentStep = 0;
            return true;
       }
       return false;
    }
    
    public void dispose() {
        simView.dispose();
    }

}
