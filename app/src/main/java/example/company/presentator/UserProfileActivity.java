package example.company.presentator;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserProfileActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }

    @Override
    protected void onStart() {
        super.onStart();
        TextView greetingTV = findViewById(R.id.greetingTextView);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        greetingTV.setText("Hello " + currentUser.getEmail());
    }
}
