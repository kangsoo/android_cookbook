package com.kangsoo.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class SQLiteSample extends ActionBarActivity {


    MyDBHandler db;
    Products product;

    Button addButton;
    Button deleteButton;
    TextView tvTextView;
    EditText etEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_sample);

        tvTextView = (TextView) findViewById(R.id.tvTextViewSQLite);
        etEditText = (EditText) findViewById(R.id.etEditTextSQLite);
        addButton = (Button) findViewById(R.id.btnAddSQLite);
        deleteButton = (Button) findViewById(R.id.btnDeleteSQLite);
        db = new MyDBHandler(this, "", null, 1);

        addButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        product = new Products();
                        product.set_productName(etEditText.getText().toString());

                        db.addProduct(product);
                        displaySQLite();
                    }
                }
        );

        deleteButton.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        db.deleteProduct(etEditText.getText().toString());
                        displaySQLite();
                    }
                }
        );

    }


    public String displaySQLite(){
        String dbString = db.databaseToString();
        tvTextView.setText(dbString);
        etEditText.setText("");
        return dbString;
    }

}
