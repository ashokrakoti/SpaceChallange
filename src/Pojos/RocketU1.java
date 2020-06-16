package Pojos;

import simulation.Simulation;

import java.util.ArrayList;
import java.util.logging.Logger;

public class RocketU1 extends Rocket {
    private static final Logger logger = Logger.getLogger(Simulation.class.getName());
   //constructor
    public RocketU1() {
        rocket_Weight = 10;             // in TONNES
        items_Loaded = new ArrayList<>();
        rocket_Cost = 100;              // in millions USD
        max_rocket_weight = 18;         //tonnes
        cargo_limit = max_rocket_weight - rocket_Weight;
    }

    //Class methods
    @Override
    public boolean launch() {
       // logger.info("inside the  launch function of the Rocket U1");
        double rand = Math.random();
       // logger.info("cargoWeight = "+cargo_Weight+" cargo_limit = "+cargo_limit);
        launch_crash_per = 5 * ((double) cargo_Weight / cargo_limit);
       // logger.info("launch crash percent: "+launch_crash_per);
       // logger.info("launch randomValue : "+rand);
        return launch_crash_per > rand;
    }

    @Override
    public boolean land() {
        //logger.info("inside the landing function of the rocket U1 ");
        double rand = Math.random();
       // logger.info("cargoWeight = "+cargo_Weight+" cargo_limit = "+cargo_limit);
        land_crash_per = 1 * ((double) cargo_Weight / cargo_limit);
       // logger.info("land crash percent: "+land_crash_per);
       // logger.info("land randomValue: "+rand);
        return land_crash_per > rand;
    }
}