package com.example.presentator.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.presentator.R;
import com.example.presentator.adapter.NewsAdapter;
import com.example.presentator.adapter.PersonAdapter;
import com.example.presentator.model.entities.News;
import com.example.presentator.model.entities.User;

import java.util.Arrays;
import java.util.Collection;

public class PeopleSearch extends AppCompatActivity {

    private RecyclerView personRecyclerView;
    private PersonAdapter personAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_search);
        initRecyclerView();
        loadPeople();
    }

    private Collection<User> getPeople() {
        User user = new User();
        user.setName("Shurik");
        user.setNick("SanekTNT");
        user.setGender(User.Gender.MALE);
        user.setImageURL("https://cdn.arstechnica.net/wp-content/uploads/2016/02/5718897981_10faa45ac3_b-640x624.jpg");
        return Arrays.asList(
                new User(user.getImageURL(),user.getNick(),user.getName(),user.getGender())
        ,new User(user.getImageURL(),user.getNick(),user.getName(),user.getGender())
                ,new User(user.getImageURL(),user.getNick(),user.getName(),user.getGender())
                ,new User(user.getImageURL(),user.getNick(),user.getName(),user.getGender())
                ,new User(user.getImageURL(),user.getNick(),user.getName(),user.getGender())

        );
    }

    private void loadPeople() {
        Collection<User> people = getPeople();
        personAdapter.setItems(people);
    }

    private void initRecyclerView() {
        personRecyclerView = findViewById(R.id.person_recycler_view);
        personRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        personAdapter = new PersonAdapter();
        personRecyclerView.setAdapter(personAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.search_person);
        SearchView searchView = (SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
