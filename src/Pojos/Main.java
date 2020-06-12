package Pojos;

import org.w3c.dom.ls.LSOutput;
import simulation.Simulation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String [] args){
        //created a simulation
        Simulation simulation = new Simulation();

        // loading items for the  phase 1
        File file = new File("phase1.txt");
        System.out.println("loading phase1 items:............");
        List<Item> items_list = simulation.loadItems(file);
        System.out.println("loading fleet of U1 rockets.............");
        List<Rocket> rockets_list = simulation.LoadU1(items_list);
        int missionCost = simulation.runSimulation(rockets_list);
        System.out.printf("the budget required for the mission is %d%n", missionCost);
    }
}
