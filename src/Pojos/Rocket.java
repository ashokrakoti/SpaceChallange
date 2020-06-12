package Pojos;

import java.util.List;

public class Rocket implements SpaceShip {

    double rocket_Weight =0; //in tonnes
    int max_rocket_weight =0; //in tonnes
    int cargo_Weight = 0 ;  // in tonnes
    int rocket_Cost =0; // in USD
    double launch_crash_per = 0;
    double land_crash_per = 0;
    double cargo_limit = 0; // in tonnes

    List<Item> items_Loaded = null;

    public int getRocket_Cost() {
        return rocket_Cost;
    }
    public void setItems_Loaded(List<Item> items_Loaded) {
        this.items_Loaded = items_Loaded;
    }

    @Override
    //launch and land methods will be redefined in rockets model classes U1 and U2.
    public boolean launch() {
        return true;
    }

    @Override
    public boolean land() {
        return true;
    }


    @Override
    public final boolean canCarry(Item item) { // this is the final implementation

        return (this.rocket_Weight + item.getWeight() <= this.max_rocket_weight);
    }

    @Override
    public final void carry(Item item) { // this is the final implementation
        this.cargo_Weight += item.getWeight();
        this.rocket_Weight += item.getWeight();
    }
}
