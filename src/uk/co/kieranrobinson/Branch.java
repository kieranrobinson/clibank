package uk.co.kieranrobinson;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Branch {
    private final String name;
    private final List<Customer> customerList;

    public Branch(String name){
        this.name = name;
        this.customerList = new ArrayList<>();
    }

    //Add a customer to the branch. openingBalance has to be greater than 0.
    public void addCustomer(String cusName, BigDecimal openingBalance){
        openingBalance = openingBalance.setScale(2, RoundingMode.FLOOR);
        if(openingBalance.compareTo(BigDecimal.valueOf(0)) >= 0){
            int cusID = customerList.size() + 1;
            this.customerList.add(new Customer(cusName, openingBalance, cusID));
            System.out.println(cusName + " added with balance of " + openingBalance);
        } else {
            System.out.println("Opening balance cannot be less than 0. Customer addition failed.");
        }
    }

    //Remove a customer from a branch via their ID
    public void removeCustomer(int cusID){
        if(customerList.size() <= 0){
            System.out.println("No customers stored");
            return;
        }

        for(int i=0; i<customerList.size(); i++){
            if(customerList.get(i).getCusID() == cusID){
                System.out.println(customerList.get(i).getCusName() + " removed.");
                customerList.remove(i);
                return;
            }
        }

        System.out.println("Customer not found");
    }

    public String getName() {
        return name;
    }

    //Prints each Customer name, ID and balance for the branch
    public void displayCustomersDetails(){
        if(customerList.size() > 0){
            //Call the getCustomerDetails method for each customer stored within the customerList
            for (Customer currentCustomer : customerList) {
                currentCustomer.getCustomerDetails();
            }
        } else {
            System.out.println("No customers are stored");
        }
    }

    //Take Customer ID as parameter and return matching Customer object if exists
    public Customer findCustomer(int cusID){
        for(Customer customer : customerList){
            if(customer.getCusID() == cusID){
                return customer;
            }
        }
        return null;
    }
}
