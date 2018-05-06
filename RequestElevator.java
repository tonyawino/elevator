/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

A class that represents the buttons to call an elevator from any floor, requesting to go up or down. The bottom row represents floor 1(ground floor),
and the top row represents floor 4
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

public class RequestElevator implements Runnable {
    private static TreeSet<Integer> floorRequestUp;
    private static TreeSet<Integer> floorRequestDown;
    private String btname, direction;
    private int ind,floor;
    protected JButton btn1;
    protected JButton btn2;
    protected JButton btn3;
    protected JButton btn4;
    protected JButton btn5;
    protected JButton btn6;
    private JPanel panel;
    public RequestElevator(){
        floorRequestUp=new TreeSet<>();
        floorRequestDown=new TreeSet<>();
        panel=new JPanel();
        btn1=new JButton("^");
        btn2=new JButton("^");
        btn3=new JButton("v");
        btn4=new JButton("^");
        btn5=new JButton("v");
        btn6=new JButton("v");
        Font myFont=new Font("sanserif", Font.BOLD, 30);
        btn1.setFont(myFont);
        btn2.setFont(myFont);
        btn3.setFont(myFont);
        btn4.setFont(myFont);
        btn5.setFont(myFont);
        btn6.setFont(myFont);
        btn1.setName("up,1");
        btn2.setName("up,2");
        btn3.setName("down,2");
        btn4.setName("up,3");
        btn5.setName("down,3");
        btn6.setName("down,4");
        btn1.addActionListener(new ButtonListener());
        btn2.addActionListener(new ButtonListener());
        btn3.addActionListener(new ButtonListener());
        btn4.addActionListener(new ButtonListener());
        btn5.addActionListener(new ButtonListener());
        btn6.addActionListener(new ButtonListener());
        panel.setLayout(new GridLayout(4, 2, 3, 3));
        panel.add(new JLabel());
        panel.add(btn6);
        panel.add(btn4);
        panel.add(btn5);
        panel.add(btn2);
        panel.add(btn3);
        panel.add(btn1);    
    }
    public static TreeSet<Integer> getFloorRequestUp(){
        return floorRequestUp;
    }
    public static TreeSet<Integer> getFloorRequestDown(){
        return floorRequestDown;
    }
    public JPanel getPanel(){
        return panel;
    }
    public class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            if(ev.getSource()==btn1)
                btn1.setForeground(Color.BLUE);
            else if(ev.getSource()==btn2)
                btn2.setForeground(Color.BLUE);
            else if(ev.getSource()==btn3)
                btn3.setForeground(Color.BLUE);
            else if(ev.getSource()==btn4)
                btn4.setForeground(Color.BLUE);
            else if(ev.getSource()==btn5)
                btn5.setForeground(Color.BLUE);
            else if(ev.getSource()==btn6)
                btn6.setForeground(Color.BLUE);
        }
    }
    public void run(){
        while(true){
            try{
                Thread.sleep(100);
            }
            catch(InterruptedException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            if (btn1.getForeground()==Color.BLUE||btn2.getForeground()==Color.BLUE||btn3.getForeground()==Color.BLUE||btn4.getForeground()==Color.BLUE||btn5.getForeground()==Color.BLUE||btn6.getForeground()==Color.BLUE){
                if(btn1.getForeground()==Color.BLUE){
                    btname=btn1.getName();
                    ind=btname.indexOf(",");
                    direction=btname.substring(0, ind);
                    floor=Integer.parseInt(btname.substring(ind+1, btname.length()));
                    if("up".equals(direction))
                        floorRequestUp.add(floor);
                    else
                        floorRequestDown.add(floor);
                }
                if(btn2.getForeground()==Color.BLUE){
                    btname=btn2.getName();
                    ind=btname.indexOf(",");
                    direction=btname.substring(0, ind);
                    floor=Integer.parseInt(btname.substring(ind+1, btname.length()));
                    if("up".equals(direction))
                        floorRequestUp.add(floor);
                    else
                        floorRequestDown.add(floor);
                }
                if(btn3.getForeground()==Color.BLUE){
                    btname=btn3.getName();
                    ind=btname.indexOf(",");
                    direction=btname.substring(0, ind);
                    floor=Integer.parseInt(btname.substring(ind+1, btname.length()));
                    if("up".equals(direction))
                        floorRequestUp.add(floor);
                    else
                        floorRequestDown.add(floor);
                }
                if(btn4.getForeground()==Color.BLUE){
                    btname=btn4.getName();
                    ind=btname.indexOf(",");
                    direction=btname.substring(0, ind);
                    floor=Integer.parseInt(btname.substring(ind+1, btname.length()));
                    if("up".equals(direction))
                        floorRequestUp.add(floor);
                    else
                        floorRequestDown.add(floor);
                }
                if(btn5.getForeground()==Color.BLUE){
                    btname=btn5.getName();
                    ind=btname.indexOf(",");
                    direction=btname.substring(0, ind);
                    floor=Integer.parseInt(btname.substring(ind+1, btname.length()));
                    if("up".equals(direction))
                        floorRequestUp.add(floor);
                    else
                        floorRequestDown.add(floor);
                }
                if(btn6.getForeground()==Color.BLUE){
                    btname=btn6.getName();
                    ind=btname.indexOf(",");
                    direction=btname.substring(0, ind);
                    floor=Integer.parseInt(btname.substring(ind+1, btname.length()));
                    if("up".equals(direction))
                        floorRequestUp.add(floor);
                    else
                        floorRequestDown.add(floor);
                }
            }
        }
    }
}
