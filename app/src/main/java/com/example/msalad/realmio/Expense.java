package com.example.msalad.realmio;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by msalad on 4/24/2017.
 */

public class Expense extends RealmObject{


    public String expenseName;
    public String expenseCat;
    public double expenseAmount;

    public Expense() {

    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public String getExpenseCat() {
        return expenseCat;
    }

    public void setExpenseCat(String expenseCat) {
        this.expenseCat = expenseCat;
    }

    public double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }
}
