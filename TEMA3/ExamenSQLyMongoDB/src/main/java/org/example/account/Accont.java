package org.example.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Accont {
    private int accountid;
    private String iban;
    private double balance;
    private int customerid;

    public Accont(String iban, double balance, int customerid) {
        this.iban = iban;
        this.balance = balance;
        this.customerid = customerid;
    }
}
