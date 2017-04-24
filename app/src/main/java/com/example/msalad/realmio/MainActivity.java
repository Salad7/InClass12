package com.example.msalad.realmio;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    int size;
    ArrayList<Expense> expenseArrayList;
    CustomAdapter customAdapter;
    ListView list;
    Realm realm;
    Button addExpense;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner) findViewById(R.id.spinner2);
        expenseArrayList = new ArrayList<Expense>();
        customAdapter = new CustomAdapter(getApplicationContext(),R.layout.custom_item,expenseArrayList);
        addExpense = (Button) findViewById(R.id.addExpense);
        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,AddExpenseActivity.class);
                startActivity(i);
            }
        });
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(customAdapter);
        realm.init(getApplicationContext());
        realm = Realm.getDefaultInstance();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                expenseArrayList.clear();
                // Build the query looking at all users:
                RealmQuery<Expense> query = realm.where(Expense.class);
                // Add query conditions:
                query.equalTo("expenseCat", "Groceries");
                // Execute the query:
                RealmResults<Expense> result1 = query.findAll();
                Toast.makeText(getApplicationContext(),result1.get(0).getExpenseName(),Toast.LENGTH_SHORT).show();

                customAdapter.notifyDataSetChanged();
                // Toast.makeText(getApplicationContext(),"Listener",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public class CustomAdapter extends ArrayAdapter<Expense>{

        Context c;
        int r;
        List<Expense> o;
        TextView name;
        TextView amount;
        public CustomAdapter(Context context, int resource, List<Expense> objects) {
            super(context, resource, objects);
            c= context;
            r = resource;
            o = objects;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(r,parent,false);
            name = (TextView) convertView.findViewById(R.id.nameTV);
            amount = (TextView) convertView.findViewById(R.id.amountTV);

            name.setText(o.get(position).getExpenseName());
            amount.setText(Double.parseDouble(o.get(position).getExpenseAmount()+"")+"");
            return  convertView;
        }


    }
}
