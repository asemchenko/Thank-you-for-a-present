package example.company.presentator;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import example.company.presentator.adapter.GiftAdapter;

public class UserProfileActivity extends Activity {
    private static final String[] gifts = {
            "Пророк",
            "Брошюра",
            "Крекер",
            "Крен",
            "Рефрактор",
            "Руль",
            "Квартира",
            "Рессора",
            "Трувер",
            "Версус",
            "Рэп",
            "Прораб",
            "Ресурс",
            "Придира"
    };
    private DatabaseReference giftRef;
    private GiftAdapter giftAdapter;

    {
        String curUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        giftRef = FirebaseDatabase.getInstance().getReference("gifts").child(curUserId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        RecyclerView recyclerView = findViewById(R.id.profileRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        giftAdapter = new GiftAdapter();
        recyclerView.setAdapter(giftAdapter);
        addGiftListener();
    }

    private void addGiftListener() {
        giftRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Gift newGift = loadGift(dataSnapshot);
                giftAdapter.add(newGift);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Gift changedGift = loadGift(dataSnapshot);
                giftAdapter.update(changedGift);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Gift removedGift = loadGift(dataSnapshot);
                giftAdapter.remove(removedGift);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

            private Gift loadGift(DataSnapshot snapshot) {
                return new Gift(snapshot.getKey(), snapshot.getValue(String.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
