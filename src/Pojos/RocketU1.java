package Pojos;

import java.util.ArrayList;
import java.util.List;

public class RocketU1 extends Rocket {

   //constructor
    public RocketU1() {
        rocket_Weight = 10;             // in TONNES
        items_Loaded = new ArrayList<>();
        rocket_Cost = 100;              // in millions USD
        max_rocket_weight = 18;         //tonnes
        launch_crash_per = 5;           //Chance of launch explosion = 5% * (cargo carried / cargo limit)
        land_crash_per = 1 ;            // Chance of landing crash = 1% * (cargo carried / cargo limit)
        cargo_limit = max_rocket_weight - rocket_Weight;
    }

    //Class methods
    @Override
    public boolean launch() {
        double rand = Math.random();
        launch_crash_per = launch_crash_per * ((double) cargo_Weight / cargo_limit);
         return (launch_crash_per > rand);
    }

    @Override
    public boolean land() {
        double rand = Math.random();
        land_crash_per = land_crash_per * ((double) cargo_Weight / cargo_limit);
        return (land_crash_per > rand);
    }
}