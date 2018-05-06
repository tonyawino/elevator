/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

A class that represents the buttons inside the elevator, floor 1 is ground floor.
 */
package Elevator;

/**
 *
 * @author Tony Awino
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class ButtonPressed implements Runnable{
    private static SortedSet pressedButtons;
    protected JButton onebtn;
    protected JButton twobtn;
    protected JButton threebtn;
    protected JButton fourbtn;
    protected static JButton openbtn;
    protected static JButton closebtn;
    protected JButton stopbtn;
    private JPanel panel;
    public ButtonPressed(){
        pressedButtons=Collections.synchronizedSortedSet(new TreeSet<>());
        onebtn=new JButton("1");
        twobtn=new JButton("2");
        threebtn=new JButton("3");
        fourbtn=new JButton("4");
        openbtn=new JButton("|< >|");
        closebtn=new JButton(">||<");
        stopbtn=new JButton("STOP");
        panel=new JPanel();
        panel.setLayout(new GridLayout(4, 2, 2,2));
        Font myFont=new Font("times new roman", Font.BOLD, 30);
        onebtn.setFont(myFont);
        twobtn.setFont(myFont);
        threebtn.setFont(myFont);
        fourbtn.setFont(myFont);
        openbtn.setFont(myFont);
        closebtn.setFont(myFont);
        stopbtn.setFont(myFont);
        onebtn.addActionListener(new ButtonListener());
        twobtn.addActionListener(new ButtonListener());
        threebtn.addActionListener(new ButtonListener());
        fourbtn.addActionListener(new ButtonListener());
        openbtn.addActionListener(new ButtonListener());
        closebtn.addActionListener(new ButtonListener());
        stopbtn.addActionListener(new ButtonListener());
        panel.add(fourbtn);
        panel.add(threebtn);
        panel.add(twobtn);
        panel.add(onebtn);
        panel.add(openbtn);
        panel.add(closebtn);
        panel.add(stopbtn);
        stopbtn.setForeground(Color.red);
        
    }
    public JPanel getPanel(){
        return panel;
    }
    public static TreeSet<Integer> getPressedButtons(){
        TreeSet<Integer> myPressedButtons=new TreeSet<>();
        myPressedButtons.addAll(pressedButtons);
        return myPressedButtons;
    }
    public static SortedSet getPressedButtonsSync(){
        return pressedButtons;
    }
    public void run(){
        while (true){
            try{
                Thread.sleep(100);
            }
            catch(InterruptedException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            if (onebtn.getForeground()==Color.BLUE||twobtn.getForeground()==Color.BLUE||threebtn.getForeground()==Color.BLUE||fourbtn.getForeground()==Color.BLUE){
                if(onebtn.getForeground()==Color.BLUE)
                    pressedButtons.add(Integer.parseInt(onebtn.getText()));
                if(twobtn.getForeground()==Color.BLUE)
                    pressedButtons.add(Integer.parseInt(twobtn.getText()));
                if(threebtn.getForeground()==Color.BLUE)
                    pressedButtons.add(Integer.parseInt(threebtn.getText()));
                if(fourbtn.getForeground()==Color.BLUE)
                    pressedButtons.add(Integer.parseInt(fourbtn.getText()));
            }
        }
    }
    public static JButton getOpenBtn(){
        return openbtn;
    }
    public static JButton getCloseBtn(){
        return closebtn;
    }
    public static void setButtonFore(String name){
        if ("open".equals(name))
            openbtn.setForeground(Color.BLACK);
        else
            closebtn.setForeground(Color.BLACK);
    }
    public class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ev){
            if (ev.getSource()==onebtn)
                onebtn.setForeground(Color.BLUE);
            else if (ev.getSource()==twobtn)
                twobtn.setForeground(Color.BLUE);
            else if (ev.getSource()==threebtn)
                threebtn.setForeground(Color.BLUE);
            else if (ev.getSource()==fourbtn)
                fourbtn.setForeground(Color.BLUE);
            else if (ev.getSource()==openbtn){
                if(!Elevator.getMoving()&&closebtn.getForeground()!=Color.BLUE){
                    openbtn.setForeground(Color.BLUE);
                    Thread.currentThread().interrupt();
                    Elevator.openDoor();
                }
            }
            else if (ev.getSource()==closebtn){
                if (Elevator.getMoving()==false&&openbtn.getForeground()!=Color.BLUE){
                    closebtn.setForeground(Color.BLUE);
                    Thread.currentThread().interrupt();
                }
            }
            else if (ev.getSource()==stopbtn){
                Elevator.stop();
            }
            
        }
    }
    
}
