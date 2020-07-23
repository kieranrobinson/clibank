package uk.co.kieranrobinson;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bank {
    private final List<Branch> branchList;

    public Bank(){
        this.branchList = new ArrayList<>();
    }

    //Takes name of branch as input, and returns object form if it exists.
    private Branch findBranch(String branchName){
        for (Branch branch : branchList) {
            if (branch.getName().equals(branchName)) {
                return branch;
            }
        }
        return null;
    }

    //Takes branch name as input, retrieves matching branch object if exists, and calls the displayCustomerDetails() method within it
    public void showCustomerDetails() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter branch name to show details from:");
        String branchName = scan.next();
        Branch currentBranch = findBranch(branchName);
        if (currentBranch != null) {
            currentBranch.displayCustomersDetails();
        } else {
            System.out.println("Branch does not exist");
        }
    }

    //Takes branch name and customer ID as inputs, and returns a list of deposits and withdrawals for matching customer
    public void showCustomerTransactions() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter branch customer is stored within:");
        String branchName = scan.next();
        Branch currentBranch = findBranch(branchName);
        if (currentBranch != null) {
            System.out.println("Enter ID of customer");
            if (scan.hasNextInt()) {
                int customerID = scan.nextInt();
                Customer currentCustomer = currentBranch.findCustomer(customerID);
                if (currentCustomer != null) {
                    currentCustomer.getCustomerTransactions();
                } else {
                    System.out.println("Customer does not exist");
                }
            } else {
                System.out.println("Invalid input. Transaction request failed");
            }
        } else {
            System.out.println("Branch does not exist");
        }
    }

    //Takes branch name, customer name, and opening balance as inputs, and creates Customer object in matching branch
    public void addCustomer() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter name of branch to add customer to:");
        String branchName = scan.next();
        Branch currentBranch = findBranch(branchName);
        if (currentBranch != null) {
            System.out.println("Enter name of customer:");
            String cusName = scan.next();
            scan.nextLine();
            System.out.println("Enter customers opening balance:");
            if (scan.hasNextBigDecimal()) {
                BigDecimal openingBalance = scan.nextBigDecimal();
                currentBranch.addCustomer(cusName, openingBalance);
            } else {
                System.out.println("Invalid value, addition failed");
            }
        } else {
            System.out.println("Branch does not exist");
            scan.nextLine();
        }

    }

    //Takes branch name, customer ID, choice of either deposit or withdrawal, and relevant amount.
    //Adjusts balance as well as adding a record to list of transactions if successful.
    public void addTransaction() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter name of branch where customer is stored:");
        String branchName = scan.next();
        Branch currentBranch = findBranch(branchName); //Retrieve matching branch object to String branchName
        if (currentBranch != null) {
            System.out.println("Enter ID of customer to add transaction for:");
            if (scan.hasNextInt()) {
                int customerID = scan.nextInt();
                Customer currentCustomer = currentBranch.findCustomer(customerID); //Retrieve matching customer object to int customerID
                if (currentCustomer != null) {
                    System.out.println("Press 1 to deposit, or 2 for withdrawal");
                    int customerChoice = scan.nextInt();
                    if (customerChoice == 1) {
                        System.out.println("Enter amount to deposit:");
                        if (scan.hasNextBigDecimal()) {
                            BigDecimal depositAmount = scan.nextBigDecimal();
                            if (depositAmount.compareTo(BigDecimal.valueOf(0)) > 0) {
                                currentCustomer.depositCash(depositAmount);
                            } else {
                                System.out.println("Deposit amount has to be greater than 0");
                            }
                        } else {
                            System.out.println("Invalid amount. Deposit failed");
                        }
                    } else if (customerChoice == 2){
                        System.out.println("Enter amount to withdraw:");
                        if(scan.hasNextBigDecimal()){
                            BigDecimal withdrawAmount = scan.nextBigDecimal();
                            if(withdrawAmount.compareTo(BigDecimal.valueOf(0)) > 0){
                                currentCustomer.withdrawCash(withdrawAmount);
                            } else {
                                System.out.println("Withdrawal amount has to be greater than 0");
                            }
                        } else {
                            System.out.println("Invalid amount. Withdrawal failed");
                        }
                    }
                } else {
                    System.out.println("Customer does not exist. Transaction failed.");
                }
            } else {
                System.out.println("Invalid input. Transaction failed");
            }
        } else {
            System.out.println("Branch does not exist");
        }
    }

    //Remove Customer object with matching ID from the specified branch
    public void removeCustomer() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter name of branch to remove customer from:");
        String branchName = scan.next();
        Branch currentBranch = findBranch(branchName);
        if (currentBranch != null) {
            System.out.println("Enter ID of customer to be removed:");
            if (scan.hasNextInt()) {
                int cusID = scan.nextInt();
                currentBranch.removeCustomer(cusID);
            } else {
                System.out.println("Invalid Input. Removal failed.");
            }
        } else {
            System.out.println("Branch does not exist");
        }
    }

    //Print a list of existing branches
    public void showBranches(){
        if(!branchList.isEmpty()){
            System.out.println("Branches: ");
            for(Branch branch : branchList){
                System.out.println(branch.getName());
            }
        } else {
            System.out.println("No branches exist");
        }

    }

    //Add a new branch with specified name if branch does not already exist with that name. Case sensitive.
    public void addBranch() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter branch name to be added:");
        String branchName = scan.next();
        for (Branch branch : branchList) {
            if (branch.getName().equals(branchName)) {
                System.out.println("Branch already exists");
                return;
            }
        }
        Branch newBranch = new Branch(branchName);
        branchList.add(newBranch);
        System.out.println(branchName + " added");
    }

    //Remove branch with specified name if exists
    public void removeBranch() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter branch name to be removed: ");
        String branchName = scan.next();
        for(int i=0; i<branchList.size(); i++){
            if(branchList.get(i).getName().equals(branchName)){
                System.out.println(branchList.get(i).getName() + " removed");
                branchList.remove(i);
                return;
            }
        }
        System.out.println("Branch does not exist. Removal failed");
    }
}
