package jacksonmeyer.com.sidekck;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MessagingActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "MessagingActivity";
    @Bind(R.id.sidekickTextView)
    TextView SidekickTextView;
    @Bind(R.id.profileImageView)
    ImageView ProfileImageView;
    @Bind(R.id.messagingImageView)
    ImageView MessagingImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);
        ButterKnife.bind(this);

        SidekickTextView.setOnClickListener(this);
        ProfileImageView.setOnClickListener(this);
        MessagingImageView.setOnClickListener(this);

        Typeface Ninja = Typeface.createFromAsset(getAssets(), "Fonts/shojumaru-regular.ttf");
        SidekickTextView.setTypeface(Ninja);
    }

    @Override
    public void onClick(View v) {
        if (v == SidekickTextView) {
            Log.d(TAG, "onClick: Sidekick text");
            Intent intent = new Intent(MessagingActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (v == ProfileImageView) {
            Log.d(TAG, "onClick: profile picture");
            Intent intent = new Intent(MessagingActivity.this, ProfileActivity.class);
            startActivity(intent);
        } else {
            Log.d(TAG, "smoething happened");
        }
    }
}
