package Pojos;

import simulation.Simulation;

import java.io.File;
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
        List<Rocket> rockets_list1 = simulation.loadU1(items_list);
        System.out.println("there are "+rockets_list1.size()+" rockets in the fleet for phase 1 launch");
        int missionCost = simulation.runSimulation(rockets_list1);
        System.out.printf("the budget required for the mission of fleet 1 with U1 type is %d%n", missionCost);

        System.out.println("\n\n--------------------------------------------------------------end of first fleet -----------------------------------------------------------------------------------------------------------------------------\n\n" );

        //for the fleet of U2 rockets
        items_list = simulation.loadItems(file);
        System.out.println("Loading fleet of U2 rockets..........");
        List<Rocket> rockets_list2 = simulation.loadU2(items_list);
        System.out.println("there are "+rockets_list2.size()+" rockets in the fleet for phase 1 launch");
        missionCost = simulation.runSimulation(rockets_list2);
        System.out.printf("the budget required for the mission of fleet 1 with U2 type is %d%n", missionCost);

        System.out.println("-------------------------------------------------------------------fleet 2 processing started---------------------------------------------------------------------------------------------------------------------------------");
        // loading items for the  phase 2
        file = new File("phase2.txt");
        System.out.println(".................loading phase2 items:............");
        items_list = simulation.loadItems(file);
        System.out.println("loading fleet of U1 rockets.............");
        rockets_list1 = simulation.loadU1(items_list);
        System.out.println("there are "+rockets_list1.size()+" rockets in the fleet for phase 2 launch");
        missionCost = simulation.runSimulation(rockets_list1);
        System.out.printf("the budget required for the mission of fleet 2  with U1 type is %d%n", missionCost);

        System.out.println("\n\n--------------------------------------------------------------end of first fleet -----------------------------------------------------------------------------------------------------------------------------\n\n" );

        //for the fleet of U2 rockets
        items_list = simulation.loadItems(file);
        System.out.println("Loading fleet of U2 rockets..........");
        rockets_list2 = simulation.loadU2(items_list);
        System.out.println("there are "+rockets_list2.size()+" rockets in the fleet for phase 2 launch");
        missionCost = simulation.runSimulation(rockets_list2);
        System.out.printf("the budget required for the mission of fleet 2 with U2 type is %d%n", missionCost);
    }
}
