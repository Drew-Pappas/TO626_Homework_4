package com.example.hw4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SearchForBirds extends AppCompatActivity implements View.OnClickListener{
    EditText editTextZipcodeSearchTerm;
    TextView textViewFoundBirdName, textViewFoundBirdZip, textViewFoundBirdReporter;
    Button buttonSearchForBird;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_birds);

        editTextZipcodeSearchTerm = findViewById(R.id.editTextZipcodeSearchTerm);
        textViewFoundBirdName = findViewById(R.id.textViewFoundBirdName);
        textViewFoundBirdZip = findViewById(R.id.textViewFoundBirdZip);
        textViewFoundBirdReporter = findViewById(R.id.textViewFoundBirdReporter);

        buttonSearchForBird = findViewById(R.id.buttonSearchForBird);

        buttonSearchForBird.setOnClickListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater optionsMenuInflater = getMenuInflater();
        optionsMenuInflater.inflate(R.menu.dropdown, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_activity_menu_item:

                Intent mainIntent = new Intent(SearchForBirds.this, MainActivity.class);
                startActivity(mainIntent);

                return true;

            case R.id.search_for_bird_menu_item:

                Toast.makeText(this, "You're already on the search page", Toast.LENGTH_SHORT).show();

            default:
                return false;

        }
    }

    @Override
    public void onClick(View view) {

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("birdlog");

        int findZip = Integer.parseInt(editTextZipcodeSearchTerm.getText().toString());

        myRef.orderByChild("zipcode").equalTo(findZip).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Bird foundBird = dataSnapshot.getValue(Bird.class);
                String foundBirdName = foundBird.birdName;
                String foundBirdZip = Integer.toString(foundBird.zipcode);
                String foundBirdReporter = foundBird.personReporting;

                textViewFoundBirdName.setText(foundBirdName);
                textViewFoundBirdZip.setText(foundBirdZip);
                textViewFoundBirdReporter.setText(foundBirdReporter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
