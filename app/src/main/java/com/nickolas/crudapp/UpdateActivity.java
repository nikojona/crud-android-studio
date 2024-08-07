package com.nickolas.crudapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class UpdateActivity extends AppCompatActivity {
     /**
     Nama : Nickolas Kurniawan Jonathan
     NPM : 237064456033
     Kelas : Karyawan (K.01)
     **/

    EditText code_input, name_input, price_input;
    Button update_button, delete_button;

    String id, code, name, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        code_input = findViewById(R.id.code_input2);
        name_input = findViewById(R.id.name_input2);
        price_input = findViewById(R.id.price_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        getAndSetIntentData();

        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.update_toolbar);
        setSupportActionBar(toolbar);

        // Enable the Up button (Back Arrow)
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(code_input.getText().toString());
        }

        // Use the OnBackPressedCallback instead
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                finish(); // Finish the current activity (equivalent to onBackPressed())
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String updatedCode = code_input.getText().toString().trim();
                String updatedName = name_input.getText().toString().trim();
                String updatedPrice = price_input.getText().toString().trim();

                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.updateData(id, updatedCode, updatedName, updatedPrice);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }

    void getAndSetIntentData() {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("code") &&
                getIntent().hasExtra("name") && getIntent().hasExtra("price")) {

            // Getting Data from Intent
            id = getIntent().getStringExtra("id");
            code = getIntent().getStringExtra("code");
            name = getIntent().getStringExtra("name");
            price = getIntent().getStringExtra("price");

            // Setting Intent Data
            code_input.setText(code);
            name_input.setText(name);
            price_input.setText(price);
        } else {
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + code + "?");
        builder.setMessage("Are you sure you want to delete " + code + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Menutup Activity Update
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}