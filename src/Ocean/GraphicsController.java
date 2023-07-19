/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ocean;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;   

/**
 *
 * @author Emmanuel
 */
public class GraphicsController {
    private JFrame frame;
    Thread t;
    
    JButton startButton;
    JButton endButton;
    JButton storeButton;
    
    JTable tblConstants;
    
    
    public GraphicsController () {
        makeFrame();
    }
    
    public static void main (String[] args) {
    
    GraphicsController g=new GraphicsController();
    //g.t.interrupt();
   
    }
    
    private  void makeFrame(){
        frame = new JFrame("frame");
        startButton=new JButton("Start Simulator");
        endButton=new JButton("End Simulator");
        storeButton=new JButton("Store Model Constants");
        //JLabel lbshark = new JLabel("shark constants:");
        //JLabel lbsardine = new JLabel("sardine constants:");
        //JLabel lbplankton = new JLabel("plankton constants:");
        
        String[][] constantInput = new String[4][6];

        String[] colnames = {"","creation","maxAge","breedingProb","breedingAge","nutValue"};
        constantInput[0] = colnames;
        constantInput[1][0] = "Plankton";
        constantInput[2][0] = "Sardine";
        constantInput[3][0] = "Shark";

                
        
        
        tblConstants = new JTable(constantInput,colnames);
        
        
        ActionListener startSimulation=new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                if(t!=null){
                    if(t.isAlive()) return;
                }
                t = new Thread(){
                    public void run(){
                        Simulator sim = new Simulator(100, 100);
                        sim.start();
                    }
                };
                t.start();
            }
        };
        ActionListener setConstants=new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                String[][] s = new String[3][5];
                for(int i = 1; i < 4; i++)
                    for(int j = 1; j < 6; j++)
                        s[i][j] = (String)tblConstants.getValueAt(i, j);
                ModelConstants.setConstants(s);
            }
        };
        ActionListener endSimulation=new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                if(t!=null)
                    if(t.isAlive())
                        t.interrupt();
                
                
            }
        };
            
        frame.addWindowListener(new WindowAdapter() {
            @Override
		public void windowClosing(WindowEvent e) {
		    System.exit(0);
		}
	});
        startButton.addActionListener(startSimulation);
        endButton.addActionListener(endSimulation);
        storeButton.addActionListener(setConstants);
        Container contentPane = frame.getContentPane();
         
        contentPane.setLayout(new GridBagLayout());
         
        contentPane.add(startButton);
        contentPane.add(endButton);
        contentPane.add(storeButton);
        contentPane.add(tblConstants);
         
        frame.pack();
        frame.setVisible(true);
    }
        
}
