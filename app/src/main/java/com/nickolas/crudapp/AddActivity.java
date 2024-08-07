package com.nickolas.crudapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AddActivity extends AppCompatActivity {
     /**
     Nama : Nickolas Kurniawan Jonathan
     NPM : 237064456033
     Kelas : Karyawan (K.01)
     **/

    EditText code_input, name_input, price_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        code_input = findViewById(R.id.code_input);
        name_input = findViewById(R.id.name_input);
        price_input = findViewById(R.id.price_input);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addProduct(
                        code_input.getText().toString().trim(),
                        name_input.getText().toString().trim(),
                        Integer.valueOf(price_input.getText().toString().trim()));
            }
        });

        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.add_toolbar);
        setSupportActionBar(toolbar);

        // Enable the Up button (Back Arrow)
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Use the OnBackPressedCallback instead
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                finish(); // Finish the current activity (equivalent to onBackPressed())
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }
}