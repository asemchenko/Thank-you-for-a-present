package com.example.presentator.modules.friends;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.presentator.R;

public class FriendsActivity extends AppCompatActivity {

    private FriendsController friendsController;
    private RecyclerView recyclerView;
    private FriendsAdapter friendsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        initRecyclerView();
        initAdapter();
        friendsController = new FriendsController(this, friendsAdapter);
        loadPeople();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.person_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initAdapter(){
        friendsAdapter = new FriendsAdapter();
        recyclerView.setAdapter(friendsAdapter);
    }

    private void loadPeople() {
        friendsController.displayUserFriends();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_user, menu);
        MenuItem item = menu.findItem(R.id.search_user);
        SearchView searchView = (SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.equals("")){
                    friendsController.displayUserFriends();
                } else {
                    friendsController.displayAllUsersByNickname(query);
                }
                //searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                friendsController.displayAllUsersByNickname(newText);
                //searchView.clearFocus();
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}