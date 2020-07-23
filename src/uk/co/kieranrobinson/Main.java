package uk.co.kieranrobinson;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Bank theBank = new Bank();
        boolean running = true;

        while (running) {
            //Call user interface, accept command line input, and perform relevant switch case.
            loadUI();
            if (scan.hasNextInt()) {
                int userInput = scan.nextInt();
                switch (userInput) {
                    case 1:
                        //Print a list of all customers in a branch, as well as their ID and balance
                        theBank.showCustomerDetails();
                        break;
                    case 2:
                        //List all of a customers deposits and withdrawals
                        theBank.showCustomerTransactions();
                        break;
                    case 3:
                        //Add a customer to an existing branch
                        theBank.addCustomer();
                        break;
                    case 4:
                        //Allows a customer to deposit funds, or withdraw they have sufficient balance
                        theBank.addTransaction();
                        break;
                    case 5:
                        //Remove a customer from an existing branch
                        theBank.removeCustomer();
                        break;
                    case 6:
                        //List all branches
                        theBank.showBranches();
                        break;
                    case 7:
                        //Add a new branch
                        theBank.addBranch();
                        break;
                    case 8:
                        //Remove an existing branch
                        theBank.removeBranch();
                        break;
                    case 9:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid input. Enter number corresponding with commands");
                        scan.nextLine(); //Clear scanner buffer ready for next input
                }
            } else {
                //Value entered was not an integer
                System.out.println("Invalid input. Enter number corresponding with commands");
                scan.nextLine(); //Clear scanner buffer ready for next input
            }
        }
    }

    //UI to show users available commands
    private static void loadUI() {
        System.out.println("-------------------- \n" +
                "1. Show Customer Details \n" +
                "2. Show Customer Transactions \n" +
                "3. Add Customer \n" +
                "4. Add Transaction \n" +
                "5. Remove Customer \n" +
                "6. Show Branches \n" +
                "7. Add Branch \n" +
                "8. Remove Branch \n" +
                "9. Exit Application \n" +
                "--------------------"
        );
    }
}
