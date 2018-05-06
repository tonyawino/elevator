/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

Main class starting the application
 */
package Elevator;

/**
 *
 * @author Tony Awino
 */

public class Test {
    public static void main(String args[]){
        Elevator elevator=new Elevator();
        Thread myElevator=new Thread(elevator);
        myElevator.start();
    }
    
}
