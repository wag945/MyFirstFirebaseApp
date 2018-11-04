package com.example.bill.myfirstfirebaseapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private Button mButton;
    private EditText mTeamName;
    private EditText mPlayer1;
    private EditText mPlayer2;
    private EditText mPlayer3;
    private EditText mPlayer4;
    private EditText mPlayer5;
    private EditText mRecord;
    private Button mAddTeam;
    private DatabaseReference mDatabase;
// ...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mTeamName = (EditText) findViewById(R.id.teamName);
        mPlayer1 = (EditText) findViewById(R.id.player1);
        mPlayer2 = (EditText) findViewById(R.id.player2);
        mPlayer3 = (EditText) findViewById(R.id.player3);
        mPlayer4 = (EditText) findViewById(R.id.player4);
        mPlayer5 = (EditText) findViewById(R.id.player5);
        mRecord = (EditText) findViewById(R.id.record);
        mAddTeam = (Button) findViewById(R.id.addTeam);
        mAddTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String teamName = mTeamName.getText().toString();
//                mDatabase.child("Teams").child("Name").setValue(teamName);
                Team team = new Team();
                //Adding values
                team.setName(teamName);
                team.setRecord(mRecord.getText().toString());
                team.addPlayer(mPlayer1.getText().toString());
                team.addPlayer(mPlayer2.getText().toString());
                team.addPlayer(mPlayer3.getText().toString());
                team.addPlayer(mPlayer4.getText().toString());
                team.addPlayer(mPlayer5.getText().toString());
                team.setRecord(mRecord.getText().toString());
                DatabaseReference newRef = mDatabase.child("Teams").push();
                Log.d("MainActivity mAddTeam on click","adding team "+teamName);
                newRef.setValue(team);
                Intent myIntent = new Intent(MainActivity.this, TeamLobbyActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

//        mDatatbaseStatus.setText("myRef database: "+mDatabase.getDatabase().toString());

        DatabaseReference teamsRef = FirebaseDatabase.getInstance().getReference("Teams");

        teamsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("There are " + dataSnapshot.getChildrenCount() + " teams");
                for (DataSnapshot teamSnapshot: dataSnapshot.getChildren()) {
//                    Log.d("MainActivity","teamSnapshot: "+teamSnapshot);
                    String name = teamSnapshot.child("name").getValue(String.class);
                    if (name != null) {
                        Log.d("MainActivity", "name from snapshot:" + name);
                        String record = teamSnapshot.child("record").getValue(String.class);
                        Log.d("MainActivity", "record from snapshot: " + record);
                        String player1 = teamSnapshot.child("player1").getValue(String.class);
                        Log.d("MainActivity", "player1 from snapshot: " + player1);
                        String player2 = teamSnapshot.child("player2").getValue(String.class);
                        Log.d("MainActivity", "player2 from snapshot: " + player2);
                        String player3 = teamSnapshot.child("player3").getValue(String.class);
                        Log.d("MainActivity", "player3 from snapshot: " + player3);
                        String player4 = teamSnapshot.child("player4").getValue(String.class);
                        Log.d("MainActivity", "player4 from snapshot: " + player4);
                        String player5 = teamSnapshot.child("player5").getValue(String.class);
                        Log.d("MainActivity", "player5 from snapshot: " + player5);
                        Team newTeam = new Team();
                        newTeam.setName(name);
                        newTeam.addPlayer(player1);
                        newTeam.addPlayer(player2);
                        newTeam.addPlayer(player3);
                        newTeam.addPlayer(player4);
                        newTeam.addPlayer(player5);
                        newTeam.setRecord(record);
                        newTeam.print();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this,
                                "onCanceled error",
                                Toast.LENGTH_LONG).show();
            }
        });
        // Read from the database
//        mDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated
//                Team team = dataSnapshot.child("Teams").getValue(Team.class);
//                Toast.makeText(MainActivity.this,"Received team: "+team.getName(),Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                Toast.makeText(MainActivity.this,"onCanceled error",Toast.LENGTH_LONG).show();
//            }
//        });
    }
}
