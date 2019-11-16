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
    EditText editTextEntryBirdZipcode, editTextEntryBirdReporter, editTextEntryBirdName;
    Button buttonSubmitBird;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEntryBirdName = findViewById(R.id.editTextEntryBirdName);
        editTextEntryBirdZipcode = findViewById(R.id.editTextEntryBirdZipcode);
        editTextEntryBirdReporter = findViewById(R.id.editTextEntryBirdReporter);

        buttonSubmitBird = findViewById(R.id.buttonSubmitBird);

        buttonSubmitBird.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater optionsMenuInflater = getMenuInflater();
        optionsMenuInflater.inflate(R.menu.dropdown, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_activity_menu_item:

                Toast.makeText(this, "You're already on the input page", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.search_for_bird_menu_item:
                Intent searchIntent = new Intent(MainActivity.this, SearchForBirds.class);
                startActivity(searchIntent);


            default:
                return false;

        }

    }

    @Override
    public void onClick(View view) {

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("birdlog");

        if (view == buttonSubmitBird){

            String entryBird = editTextEntryBirdName.getText().toString();
            int zip = Integer.parseInt(editTextEntryBirdZipcode.getText().toString());
            String entryPerson = editTextEntryBirdReporter.getText().toString();
            long time = System.currentTimeMillis();

            Bird newBird = new Bird(entryBird, zip, entryPerson, time);

            myRef.push().setValue(newBird);

        }
    }
}
