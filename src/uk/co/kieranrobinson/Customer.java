package uk.co.kieranrobinson;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final String cusName;
    private BigDecimal balance;
    private final List<BigDecimal> cusTransactions; //List of transactions that have been added to a customer
    private final int cusID;

    public Customer(String cusName, BigDecimal openingBalance, int cusID) {
        this.cusName = cusName;
        this.balance = openingBalance;
        this.cusTransactions = new ArrayList<>();
        this.cusID = cusID;
    }

    //Print all of the customers transactions
    public void getCustomerTransactions(){
        if(!cusTransactions.isEmpty()){
            for(int i=0; i<cusTransactions.size(); i++){
                System.out.println("Transaction " + (i+1) + ": " + cusTransactions.get(i));
            }
        } else {
            System.out.println("No transaction history");
        }
    }

    public int getCusID() {
        return cusID;
    }

    public String getCusName() {
        return cusName;
    }

    //Retrieves Customer ID , balance and name
    public void getCustomerDetails(){
        System.out.println("Customer ID: " + cusID);
        System.out.println("Name: " + cusName);
        System.out.println("Balance: " + balance);
    }

    //Add a transaction, adjust balance accordingly, and add to transaction list
    public void depositCash(BigDecimal depositAmount){
        depositAmount = depositAmount.setScale(2, RoundingMode.FLOOR);
        balance = balance.add(depositAmount);
        cusTransactions.add(depositAmount);
        System.out.println(depositAmount + " deposited");
    }

    public void withdrawCash(BigDecimal withdrawAmount){
        withdrawAmount = withdrawAmount.setScale(2,RoundingMode.FLOOR);
        balance = balance.subtract(withdrawAmount);
        cusTransactions.add(withdrawAmount.negate());
        System.out.println(withdrawAmount + " withdrawn");
    }
}
