package jacksonmeyer.com.sidekck;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.Bind;
import jacksonmeyer.com.sidekck.constructors.Post;

public class PostDialogBoxFragment extends DialogFragment {
    @Bind(R.id.postTextView)
    EditText PostTextView;

    private DatabaseReference mDatabaseRef;
    private String postOwnersUid;
    Integer numberOfTimesFlagged = 0;
    Integer numberOfSidekicks = 0;
    String postMessagePost = "looking for friend";
    ArrayList<String> potentialSidekicks = new ArrayList<String>() {{
        add("0");

    }};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.post_dialog_box_fragment, container, false);

        Button cancelButton = (Button) rootView.findViewById(R.id.cancelButton);
        Button submitButton = (Button) rootView.findViewById(R.id.postButton);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        postOwnersUid = (user.getUid());

        mDatabaseRef = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_USER_POST);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText Post = (EditText) rootView.findViewById(R.id.postTextView);
                postMessagePost = Post.getText().toString();

                SendPostToDataBase();
                dismiss();
            }
        });

        return rootView;
    }
    public void SendPostToDataBase() {
        Post newPost = new Post(postOwnersUid, postMessagePost, numberOfTimesFlagged, numberOfSidekicks, potentialSidekicks);
        mDatabaseRef.push().setValue(newPost);
    }
}
