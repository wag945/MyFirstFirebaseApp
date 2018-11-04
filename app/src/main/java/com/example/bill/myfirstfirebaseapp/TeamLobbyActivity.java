package com.example.bill.myfirstfirebaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TeamLobbyActivity extends AppCompatActivity {

    private ListView listViewTeams;
    private TeamAdapter teamAdapter;
    private ArrayList<Team> teams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_lobby);
        listViewTeams = (ListView) findViewById(R.id.list_view_teams);

//        UserProfilePersistence userProfilePersistence = new UserProfilePersistence(this);
//        userProfiles = userProfilePersistence.getDataFromDB();
//        Toast.makeText(ViewAllUsersActivity.this,"Number of users read from DB: "+userProfiles.size(),Toast.LENGTH_SHORT).show();
        teams = new ArrayList<>();

        //Need to populate the teams from the DB
        Team mteam = new Team("Test","Player1","Player2","Player3","Player4","Player5","2-0");
        teams.add(mteam);

        teamAdapter = new TeamAdapter(this,
                R.layout.content_list_item,
                teams);

        listViewTeams.setAdapter(teamAdapter);

        listViewTeams.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                Team team = (Team) listViewTeams.getItemAtPosition(position);
//                Intent intent = new Intent(ViewAllUsersActivity.this, UserDetailsActivity.class);
//                intent.putExtra("FIRSTNAME", userProfile.getFirstname());
//                intent.putExtra("SURNAME", userProfile.getSurname());
//                intent.putExtra("USERNAME", userProfile.getUsername());
//
//                startActivity(intent);
            }
        });
    }
}
