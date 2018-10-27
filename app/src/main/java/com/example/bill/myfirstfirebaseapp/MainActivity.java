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

    private TextView mTextView;
    private Button mButton;
    private EditText mDatatbaseStatus;
    private EditText mDataToSend;
    private DatabaseReference mDatabase;
// ...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.mTextView);
        mDatatbaseStatus = (EditText) findViewById(R.id.mDatabaseStatus);
        mDataToSend = (EditText) findViewById(R.id.mDataToSend);
        mButton = (Button) findViewById(R.id.mButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Write the data to the database
                String text = mDataToSend.getText().toString();

                mDatabase.child("username").setValue(text);
            }
        });


        // Write a message to the database
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatatbaseStatus.setText("myRef database: "+mDatabase.getDatabase().toString());
        // Read from the database


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated
                String value = dataSnapshot.child("username").getValue(String.class);
                Toast.makeText(MainActivity.this,"Received data from database: ",Toast.LENGTH_LONG).show();
                mTextView.setText(value);
                mDatatbaseStatus.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(MainActivity.this,"onCanceled error",Toast.LENGTH_LONG).show();
                mDatatbaseStatus.setText(error.toString());
            }
        });

    }
}
