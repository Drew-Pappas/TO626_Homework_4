package com.example.hw4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Declare objects
    EditText editTextEntryBirdZipcode, editTextEntryBirdReporter, editTextEntryBirdName;
    Button buttonSubmitBird;
    @Override

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Connect UI elements to declared objects
        editTextEntryBirdName = findViewById(R.id.editTextEntryBirdName);
        editTextEntryBirdZipcode = findViewById(R.id.editTextEntryBirdZipcode);
        editTextEntryBirdReporter = findViewById(R.id.editTextEntryBirdReporter);

        buttonSubmitBird = findViewById(R.id.buttonSubmitBird);

        //Set onclick listener to make button responsive to clicks
        buttonSubmitBird.setOnClickListener(this);
    }

    //Creates a menu inflater to handle navigation between pages
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater optionsMenuInflater = getMenuInflater();
        optionsMenuInflater.inflate(R.menu.dropdown, menu);
        return super.onCreateOptionsMenu(menu);

    }

    //Handles selections for navigation buttons after menu has been clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_activity_menu_item:

                Toast.makeText(this, "You're already on the input page", Toast.LENGTH_SHORT).show();

            case R.id.search_for_bird_menu_item:

                Intent searchIntent = new Intent(MainActivity.this, SearchForBirds.class);
                startActivity(searchIntent);

            default:
                return false;

        }

    }

    @Override
    public void onClick(View view) {

        //Create firebase database reference and object to access nested information
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("birdlog");

        //Handle onclick for the submit button and create a new bird object to be passed to the database
        if (view == buttonSubmitBird){

            String entryBird = editTextEntryBirdName.getText().toString();
            int zip = Integer.parseInt(editTextEntryBirdZipcode.getText().toString());
            String entryPerson = editTextEntryBirdReporter.getText().toString();
            long time = System.currentTimeMillis();

            Bird newBird = new Bird(entryBird, zip, entryPerson, time);

            myRef.push().setValue(newBird);

            Toast.makeText(this, "Bird submitted to database!", Toast.LENGTH_SHORT).show();

            //Reset text to empty string to prompt user that the entry was submitted
            editTextEntryBirdName.setText("");
            editTextEntryBirdZipcode.setText("");
            editTextEntryBirdReporter.setText("");

        }
    }
}
