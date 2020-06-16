package simulation;

import Pojos.Item;
import Pojos.Rocket;
import Pojos.RocketU1;
import Pojos.RocketU2;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Simulation {
    private static final Logger logger = Logger.getLogger(Simulation.class.getName());
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
            logger.info("input file is not present.");

        }

        while (s != null ? s.hasNextLine() : false) {
            Item item = new Item();
            String[] splits = s.nextLine().split("=");
            //logger.info((splits.toString()));
            //logger.info(" These are the list of items to be transported using rockets.");
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
    public List<Rocket> loadU1(@NotNull List<Item> Cargo_list){
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
                 u1.carry(i);
                 itr.remove();
             }
             if(!itr.hasNext() && item_List_loaded!=null){
                 u1_list.add(u1);
             }
        }item_List_loaded.clear();
        return u1_list;
     }

    /**
     * similar as the load U1 function ....please refer the above function docs.
     * @param Cargo_list is the cargo to be loaded in the U2 rockets.
     * @return list of u2 rockets loaded with the cargo
     */
    public List<Rocket> loadU2(List<Item> Cargo_list) {
        List<Rocket> u2_list = new ArrayList<>();
        List<Item> item_List_loaded = new ArrayList<>();
        Iterator<Item> itr = Cargo_list.iterator();
        Rocket u2 = new RocketU2();
        while(itr.hasNext()){
            Item i = itr.next();
            if(u2.canCarry(i)){
                u2.carry(i);
                // Item j = i;
                itr.remove();
                item_List_loaded.add(i);
                u2.setItems_Loaded(item_List_loaded);
            }else{
                u2_list.add(u2);
                u2= new RocketU1();
                item_List_loaded = new ArrayList<>();
                item_List_loaded.add(i);
                u2.carry(i);
                itr.remove();
            }
            if(!itr.hasNext() && item_List_loaded!=null){
                u2_list.add(u2);
            }
        }item_List_loaded.clear();
        return u2_list;
    }


    /**
     * this methods takes the list of the loaded rockets and launches them and lands them.
     * @param rocketList list of loaded rockets
     * @return the budget required to launch the whole rockets in millions (USD).
     */
     public int runSimulation(List<Rocket> rocketList){ // run time polymorphism

        // keeps track of the budget required for the launch.
        int total_budget = 0;
        int rocketCount = 0;

          for (Rocket rock : rocketList) {
              rocketCount ++;
              int launchCount =0;
              launchCount++;
              System.out.println("the rocket "+rocketCount+" is being launched");
              // rocket's 1st launch
             // logger.info( " launch attempt "+launchCount+" of rocket "+rocketCount);
              boolean launch_status = rock.launch();

              if(launch_status) {
                  System.out.println("Rocket "+rocketCount+" launch attempt " +launchCount+ " successful");
              }
              int budget = rock.getRocketCost();
             // logger.info("the budget till now is "+budget);
             // this loop launches a rocket until its launch is success.
             while(!launch_status){
                // logger.info("inside the relaunch part for rocket in because of launch crash");
                 System.out.println("OOPS launch "+ launchCount +" failure!!. rocket "+rocketCount+" crashed while launching!!!!!. Relaunching the crashed rocket............");
                 launch_status = rock.launch();
                 launchCount++;
                 budget += rock.getRocketCost();  // incrementing the required budget to launch the rocket.
             }
             boolean land_status = rock.land();
              int landCount = 1;
              //logger.info("rocket "+rocketCount+" landing attempt "+landCount);

             if(land_status){
                // logger.info("inside the landing part of the rocket.");

                 System.out.println("Rocket "+rocketCount+" landing "+landCount+" is successful");
             }
              // this loop re-launches a rocket until its landing is also successful.
              while(!land_status){
                 // logger.info("inside the relaunching part of rocket because of land failure ");
                  System.out.println("OOPS landing attempt "+landCount+" failure!!. rocket "+rocketCount+" crashed while landing!!!!. Relaunching the crashed landed rocket...............");
                  launch_status = rock.launch();
                  launchCount++;
                  //logger.info("this is the launch "+launchCount+" of the rocket"+rocketCount);
                  budget += rock.getRocketCost();
                  while(!launch_status){
                      //logger.info("inside the re-launch part of the rocket after the launch crash after relaunching because of land crash ");
                      System.out.println("OOPS launch failure!!. rocket "+rocketCount+" crashed while launching!!!!!!. Relaunching the crashed rocket............");
                      launch_status = rock.launch();
                      launchCount++;
                     // logger.info("launch "+launchCount+" of the rocket "+rocketCount);
                      budget += rock.getRocketCost();
                  }land_status = rock.land();
                  landCount++;
              }

              System.out.println("The Rocket "+rocketCount+" is a success." );
              total_budget = total_budget+budget;
             // logger.info("the number of launches needed is "+launchCount);
             // logger.info("the number of landings is "+landCount);
              System.out.println("\n");
         }
        return total_budget;
     }

}
