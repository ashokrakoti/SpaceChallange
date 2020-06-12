package simulation;

import Pojos.Item;
import Pojos.Rocket;
import Pojos.RocketU1;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Simulation {
    /**
     * this method loads cargo to the rocket.
     * @param file takes the .txt file containing the cargo items details to be loaded to the rocket
     * @return list of cargo item objects.
     */
    public List<Item> loadItems (File file)  {
        List<Item> Cargo_list = new ArrayList<>();
        Scanner s = null;
        try {
            s = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println(" Input file is not present in the given location");
        }

        while (s.hasNextLine()) {
            Item item = new Item();
            String[] splits = s.nextLine().split("=");
            item.setName(splits[0]);
            double item_weight = Integer.parseInt(splits[1]);
            item.setWeight(item_weight/1000); //conversion for tonnes weight
            Cargo_list.add(item);
        }
        return Cargo_list;
    }

    /**
     * Creates the rockets of type U1 and start filling the items into till the limits of the U1.
     * @param Cargo_list list of items to be loaded to the rockets.
     * @return  returns a list of rockets loaded and ready to launch
     */
    public List<Rocket> LoadU1 (@NotNull List<Item> Cargo_list){
        //load U1 and till the limits and create new ones till all the items are loaded
        // return the array list of U1 loaded rockets
       List<Rocket> u1_list = new ArrayList<>();
        List<Item> item_List_loaded = new ArrayList<>();
        Iterator<Item> itr = Cargo_list.iterator();
        Rocket u1 = new RocketU1();
        while(itr.hasNext()){
             Item i = itr.next();
             if(u1.canCarry(i)){
                 u1.carry(i);
                // Item j = i;
                 itr.remove();
                 item_List_loaded.add(i);
                 u1.setItems_Loaded(item_List_loaded);
             }else{
                 u1_list.add(u1);
                 u1= new RocketU1();
                 item_List_loaded = new ArrayList<>();
                 item_List_loaded.add(i);
                 itr.remove();
             }
             if(!itr.hasNext() && item_List_loaded!=null){
                 u1_list.add(u1);
             }
        }item_List_loaded.clear();
        return u1_list;
     }

    /**
     * this methods takes the list of the loaded rockets and launches them and lands them.
     * @param rocketList list of loaded rockets
     * @return the budget required to launch the whole rockets in millions (USD).
     */
     public int runSimulation(List<Rocket> rocketList){ // run time polymorphism

        // keeps track of the budget required for the launch.
        int budget = 0;

          for (Rocket rock : rocketList) {
              boolean launch_status = rock.launch();
              budget = rock.getRocket_Cost();
             // this loop launches a rocket until its launch is sucess.
             while(!launch_status){
                 System.out.println("OOPS launch failure!!. rocket crashed!!!!!. Relaunching the crashed rocket");
                 launch_status = rock.launch();
                 budget += rock.getRocket_Cost();
             }
             boolean land_status = rock.land();
              // this loop launches a rocket until its landing is sucessful.
              while(!land_status){
                  System.out.println("OOPS land failure!!. rocket crashed!!!!!. Relaunching the crashed rocket");
                  launch_status = rock.launch();
                  budget = rock.getRocket_Cost();
                  while(!launch_status){
                      System.out.println("OOPS launch failure!!. rocket crashed!!!!!. Relaunching the crashed rocket");
                      launch_status = rock.launch();
                      budget += rock.getRocket_Cost();
                  }land_status = rock.land();
              }
         }
        return budget;
     }


}
