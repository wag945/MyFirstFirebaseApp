package com.example.bill.myfirstfirebaseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;

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
                newRef.setValue(team);
            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference();
//        mDatatbaseStatus.setText("myRef database: "+mDatabase.getDatabase().toString());

        // Read from the database
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated
                String value = dataSnapshot.child("username").getValue(String.class);
                Toast.makeText(MainActivity.this,"Received data from database: ",Toast.LENGTH_LONG).show();
//                mTextView.setText(value);
//                mDatatbaseStatus.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(MainActivity.this,"onCanceled error",Toast.LENGTH_LONG).show();
//                mDatatbaseStatus.setText(error.toString());
            }
        });
    }
}
