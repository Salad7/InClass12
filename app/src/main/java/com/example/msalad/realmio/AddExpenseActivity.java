package com.example.msalad.realmio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by msalad on 4/24/2017.
 */

public class AddExpenseActivity extends AppCompatActivity{
    EditText name;
    EditText amount;
    String spinner_item;
    Spinner spinner;
    Button addItem;
    Button cancel;
    Realm realm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        // Obtain a Realm instance
        realm.init(getApplicationContext());
        realm = Realm.getDefaultInstance();
        final RealmResults<Expense> expenses = realm.where(Expense.class).findAll();
        Log.d("expenses", expenses.size()+"");





        name = (EditText) findViewById(R.id.nameET);
        amount = (EditText) findViewById(R.id.amountET);
        spinner = (Spinner) findViewById(R.id.spinner);
        addItem = (Button) findViewById(R.id.addBtn);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Expense e= realm.createObject(Expense.class);
                        e.setExpenseAmount(Double.parseDouble(amount.getText().toString()));
                        e.setExpenseCat(spinner.getSelectedItem().toString());
                        e.setExpenseName(name.getText().toString());


                    }
                }, new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {
                        // Transaction was a success.
                        Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                    }
                }, new Realm.Transaction.OnError() {
                    @Override
                    public void onError(Throwable error) {
                        // Transaction failed and was automatically canceled.
                        Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_LONG).show();
                        error.printStackTrace();

                    }
                });
            }
        });
    }
}
