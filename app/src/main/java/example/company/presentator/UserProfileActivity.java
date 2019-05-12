package example.company.presentator;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Arrays;

import example.company.presentator.adapter.GiftAdapter;

public class UserProfileActivity extends Activity {
    private static final Gift[] gifts = {
            new Gift("Пророк"),
            new Gift("Брошюра"),
            new Gift("Крекер"),
            new Gift("Крен"),
            new Gift("Рефрактор"),
            new Gift("Руль"),
            new Gift("Квартира"),
            new Gift("Рессора"),
            new Gift("Трувер"),
            new Gift("Версус"),
            new Gift("Рэп"),
            new Gift("Прораб"),
            new Gift("Ресурс"),
            new Gift("Придира")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        RecyclerView recyclerView = findViewById(R.id.profileRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        GiftAdapter giftAdapter = new GiftAdapter(Arrays.asList(gifts));
        recyclerView.setAdapter(giftAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
