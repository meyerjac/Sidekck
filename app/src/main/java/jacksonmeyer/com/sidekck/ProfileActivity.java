package jacksonmeyer.com.sidekck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.uidTextView)
    TextView UidTextView;
    @Bind(R.id.displayNameTextView)
    ImageView DisplayNameTextView;
    @Bind(R.id.profilePictureImageView)
    ImageView ProfilePictureImageView;
    @Bind(R.id.sidekickTextView)
    TextView SidekickTextView;
    @Bind(R.id.profileImageView)
    ImageView ProfileImageView;
    @Bind(R.id.messagingImageView)
    ImageView MessagingImageView;


    private DatabaseReference mDatabaseRef;
    private String TAG = "ProfileActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        mDatabaseRef = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_USER);






        SidekickTextView.setOnClickListener(this);
        MessagingImageView.setOnClickListener(this);
    }

    public void logout(View view) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }
    private void goLoginScreen() {
        Intent intent = new Intent(this, FacebookLoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (v == SidekickTextView) {
            Log.d(TAG, "onClick: Sidekick text");
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (v == MessagingImageView)  {
            Log.d(TAG, "onClick: messaging");
            //stay on the current activity
            Intent intent = new Intent(ProfileActivity.this, MessagingActivity.class);
            startActivity(intent);
        } else {
            Log.d(TAG, "smoething happened");
        }
    }
}
