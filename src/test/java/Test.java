/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Console;

/**
 *
 * @author root
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Console cnsl = null;
        String name = null;
        TestService testService = new TestService();
        
        
        

        try {
            // creates a console object
            cnsl = System.console();
            
     
            boolean exit = false;
            // if console is not null
            while (cnsl != null && !exit) {

                // read line from the user input
                name = cnsl.readLine("command : ");

                // prints
                System.out.println("Name entered : " + name);
                if (name.trim().equalsIgnoreCase("exit")) {
                    exit = true;
                } else if (name.trim().equalsIgnoreCase("stats")) {
                    System.out.println("DATA STAT \n " + testService.readStats.dumpRawStats());
                } else if (name.trim().equalsIgnoreCase("create")) {
                    testService.createBalanceNode(1);
                } else if (name.trim().equalsIgnoreCase("delete")) {
                    testService.createBalanceNode(2);
                } else if (name.trim().equalsIgnoreCase("get")) {
                    testService.createBalanceNode(3);
                } else if (name.trim().equalsIgnoreCase("set")) {
                    testService.createBalanceNode(4);
                } else if (name.trim().equalsIgnoreCase("add")) {
                    testService.createBalanceNode(5);
                } else if (name.trim().equalsIgnoreCase("deduct")) {
                    testService.createBalanceNode(6);
                } else if (name.trim().equalsIgnoreCase("counter")) {
                    testService.createBalanceNode(7);
                }
            }
        } catch (Exception ex) {
            // if any error occurs
            ex.printStackTrace();
        }
    }
}
