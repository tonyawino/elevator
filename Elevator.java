/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

Elevator class with the frame where the outside and inside buttons are placed. Has a text area to show the motions of the elevator
 */
package Elevator;

/**
 *
 * @author Tony Awino
 */
import javax.swing.*;
import java.awt.*;
import java.util.*;
public class Elevator implements Runnable {
    Runnable requested;
    Runnable pressed;
    Thread but;
    Thread req;
    RequestElevator myReq;
    ButtonPressed myButt;
    private static int currentFloor;
    private static boolean moving=false;
    private static String direction;
    private JFrame myFrame;
    protected static JTextArea textArea;
    public Elevator(){
        requested=new RequestElevator();
        pressed=new ButtonPressed();
        but=new Thread(pressed);
        req=new Thread(requested);
        myReq=(RequestElevator)requested;
        myButt=(ButtonPressed)pressed;
        currentFloor=1;
        direction="nowhere";
        textArea=new JTextArea();
        textArea.setLineWrap(true);
        textArea.setRows(20);
        textArea.setEditable(false);
        Font myFont=new Font("san serif", Font.BOLD, 20);
        textArea.setFont(myFont);
        JScrollPane scrollPane=new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        myFrame=new JFrame("Elevator");
        myFrame.getContentPane().add(BorderLayout.CENTER, scrollPane);
        myFrame.getContentPane().add(BorderLayout.WEST, myButt.getPanel());
        myFrame.getContentPane().add(BorderLayout.EAST, myReq.getPanel());
    }
    public void go(){
        myFrame.setSize(800,800);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setVisible(true);
        but.start();
        req.start();
        while (true){
            try{
                Thread.sleep(100);
            }
            catch(InterruptedException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            if (ButtonPressed.getPressedButtons().isEmpty()){
                if (!RequestElevator.getFloorRequestUp().isEmpty()){
                    for(int floor : RequestElevator.getFloorRequestUp()){
                        if (floor<currentFloor)
                            direction="down";
                        else if (floor>currentFloor)
                            direction="up";
                        else
                            direction="nowhere";
                        if ("nowhere".equals(direction))
                            moving=false;
                        else
                            moving=true;
                        textArea.append("Empty lift is moving "+direction+"\n");
                        if (!"nowhere".equals(direction)){
                            try{
                                Thread.sleep(3000);
                            }
                            catch(InterruptedException ex){
                                JOptionPane.showMessageDialog(null, ex.getMessage());
                            }
                        }
                        stop(floor);
                        openDoor();
                        closeDoor();
                        currentFloor=floor;
                        RequestElevator.getFloorRequestUp().remove(floor);
                        if (floor==1)
                            myReq.btn1.setForeground(Color.BLACK);
                        else if (floor==2)
                            myReq.btn2.setForeground(Color.BLACK);
                        else
                            myReq.btn4.setForeground(Color.BLACK);
                        press();
                    }
                }
                else if (!RequestElevator.getFloorRequestDown().isEmpty()){
                    for (int floor : RequestElevator.getFloorRequestDown()){
                        if (floor>currentFloor)
                            direction="up";
                        else if (floor<currentFloor)
                            direction="down";
                        else
                            direction="nowhere";
                        moving = !"nowhere".equals(direction);
                        textArea.append("Empty Lift is moving "+direction+"\n");
                        if (!"nowhere".equals(direction)){
                            try{
                                Thread.sleep(3000);
                            }
                            catch(InterruptedException ex){
                                JOptionPane.showMessageDialog(null, ex.getMessage());
                            }
                        }
                        stop(floor);
                        openDoor();
                        closeDoor();
                        currentFloor=floor;
                        RequestElevator.getFloorRequestDown().remove(floor);
                        if (floor==2)
                            myReq.btn3.setForeground(Color.BLACK);
                        else if (floor==3)
                            myReq.btn5.setForeground(Color.BLACK);
                        else
                            myReq.btn6.setForeground(Color.BLACK);
                        press();
                    }
                }
            }
            else {
                press();
            }
        }
        
    }
    public static void openDoor(){
        textArea.append("Door Opening\n");
        try{
            Thread.sleep(5000);
        }
        catch(InterruptedException ex){
            if(ButtonPressed.getOpenBtn().getForeground()==Color.BLUE)
                Thread.currentThread().interrupt();
            else if(ButtonPressed.getCloseBtn().getForeground()==Color.BLUE){
                Thread.currentThread().interrupt();
            }
        }
        ButtonPressed.setButtonFore("open");
    }
    public static void closeDoor(){
        textArea.append("Door Closing\n");
        ButtonPressed.setButtonFore("close");
    }
    public static void stop(){
        moving=false;
        textArea.append("Elevator stopping at "+currentFloor+" floor\n");
    }
    public static void stop(int fl){
        moving=false;
        textArea.append("Elevator stopping at "+ fl+" floor\n");
    }
    public static boolean getMoving(){
        return moving;
    }
    public void press(){
        TreeSet<Integer> array=RequestElevator.getFloorRequestUp();
        for(int floor : "down".equals(direction) ? ButtonPressed.getPressedButtons() : ButtonPressed.getPressedButtons().descendingSet()){
            if (floor>currentFloor){
                direction="up";
                array=RequestElevator.getFloorRequestUp();
            }
            else if (floor<currentFloor){
                direction="down";
                array=RequestElevator.getFloorRequestDown();  
            }
            else
                direction="nowhere";
            moving = !"nowhere".equals(direction);
            textArea.append("Lift is moving "+direction+"\n");
            if (!"nowhere".equals(direction)){
                try{
                    Thread.sleep(3000);
                }
                catch(InterruptedException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
            for(int requestFloor : array){
                if(("up".equals(direction)&&requestFloor<floor&&requestFloor>=currentFloor)||(direction.equals("down")&&requestFloor>floor&&requestFloor<=currentFloor)){
                    stop(requestFloor);
                    currentFloor=requestFloor;
                    openDoor();
                    closeDoor();
                    array.remove(requestFloor);
                    if ("up".equals(direction)){
                        if (requestFloor==1)
                            myReq.btn1.setForeground(Color.BLACK);
                        else if (requestFloor==2)
                            myReq.btn2.setForeground(Color.BLACK);
                        else if (requestFloor==3)
                            myReq.btn4.setForeground(Color.BLACK);
                    }
                    else if ("down".equals(direction)){
                        if (requestFloor==2)
                            myReq.btn3.setForeground(Color.BLACK);
                        else if (requestFloor==3)
                            myReq.btn5.setForeground(Color.BLACK);
                        else if (requestFloor==4)
                            myReq.btn6.setForeground(Color.BLACK);
                    }
                }
            }
            stop(floor);
            currentFloor=floor;
            openDoor();
            closeDoor();
            ButtonPressed.getPressedButtons().remove(floor);
            ButtonPressed.getPressedButtonsSync().remove(floor);
            if (floor==1)
                myButt.onebtn.setForeground(Color.BLACK);
            else if (floor==2)
                myButt.twobtn.setForeground(Color.BLACK);
            else if (floor==3)
                myButt.threebtn.setForeground(Color.BLACK);
            else if (floor==4)
                myButt.fourbtn.setForeground(Color.BLACK);
        }
    }
    public void run(){
        Elevator elevator=new Elevator();
        elevator.go();
    }
    
}
