package jacksonmeyer.com.sidekck;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import jacksonmeyer.com.sidekck.constructors.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.sidekickTextView)
    TextView SidekickTextView;
    @Bind(R.id.profileImageView)
    ImageView ProfileImageView;
    @Bind(R.id.messagingImageView)
    ImageView MessagingImageView;
    @Bind(R.id.addPostButton)
    ImageView AddPostButton;

    @Bind(R.id.getFacebookInfoButton)
    Button GetFacebookInfoButton;

    private DatabaseReference mDatabaseRef;
    private String facebookData;
    private String Uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mDatabaseRef = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_USER);

        ProfileImageView.setOnClickListener(this);
        MessagingImageView.setOnClickListener(this);

        AddPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                PostDialogBoxFragment postDialogBoxFragment = new PostDialogBoxFragment();
//                postDialogBoxFragment.show(fm, "dialog is shown");
            }

        });
    }



    public void getResponses(View view) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me?fields=id,about,birthday,first_name,last_name,email,picture",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
            /* handling the result */
                        facebookData = String.valueOf(response.getJSONObject());
//                        System.out.println("Response:" + facebookData);
                        getFacebookProfileData();

                    }
                }
        ).executeAsync();
    }



    public void getFacebookProfileData() {
        try {
            String jsonData = facebookData;
            if (jsonData == null) {
                Toast.makeText(MainActivity.this, "it didnt work", Toast.LENGTH_LONG).show();
            }
            System.out.println("Response:" + facebookData);
            JSONObject facebookJSON = new JSONObject(jsonData);
            String firstName = facebookJSON.getString("first_name");
            String lastName = facebookJSON.getString("last_name");
            String displayName = (firstName + " " + lastName.charAt(0));
            String birthday = facebookJSON.getString("birthday");
            String email = facebookJSON.getString("email");
            String bio = "Hi I'm one of the coolest people you will meet, besides Elon Musk " +
                    "and Mark Zuckerburg, but I'm new to the area and I love to be adventureous, " +
                    "can't wait to hang with you all!";
            String facebookId = facebookJSON.getString("id");
            JSONObject picturePicture = facebookJSON.getJSONObject("picture");
            JSONObject pictureData = picturePicture.getJSONObject("data");
            String picture = pictureData.getString("url");


            User singleUser = new User(firstName, lastName, displayName, bio, birthday, email, facebookId, picture);
            mDatabaseRef.setValue(singleUser);

        } catch (JSONException e) {
            Log.d("hello", "caught exception");
        }
    }

    @Override
    public void onClick(View view) {
        if (view == ProfileImageView) {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
        } else if (view == MessagingImageView)  {
            //stay on the current activity
            Intent intent = new Intent(MainActivity.this, MessagingActivity.class);
            startActivity(intent);
        }
        else if (view == AddPostButton) {
            //stay on the current activity
            Intent intent = new Intent(MainActivity.this, PostDialogBoxFragment.class);
            startActivity(intent);
        } else {
            Log.d("yo", "something happened");
        }
    }
}

