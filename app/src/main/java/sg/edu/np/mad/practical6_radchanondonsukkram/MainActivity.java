package sg.edu.np.mad.practical6_radchanondonsukkram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String TAG = "Main Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHandler dbHandler = new DBHandler(this, null, null,1);

        String description = "This is a random text being used as the user's description...";
        User newUser = new User("NewUser", description, 1, false);

        Intent receivingEnd = getIntent();
        String userName = receivingEnd.getStringExtra("New Name");
        String userDesc = receivingEnd.getStringExtra("New Desc");
        int userId = receivingEnd.getIntExtra("New Id", 0);
        boolean userFollowStatus = receivingEnd.getBooleanExtra("New Followed Status", false);
        TextView nameText = findViewById(R.id.nameText);
        nameText.setText(userName);

        TextView descText = findViewById(R.id.descText);
        descText.setText("Description: " + userDesc);
        Button followButton = findViewById(R.id.followButton);

        if (userFollowStatus == false){
            followButton.setText("Follow");
        }
        else{
            followButton.setText("Unfollow");
        }

        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v(TAG, "Button pressed");

                if (newUser.isFollowed() == false){
                    newUser.setFollowed(true);
                    followButton.setText("Unfollow");

                    dbHandler.updateFollowStatus(newUser.getId(), newUser.isFollowed());

                    Context context = getApplicationContext();
                    CharSequence text = "Followed";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else{
                    newUser.setFollowed(false);
                    followButton.setText("Follow");

                    dbHandler.updateFollowStatus(newUser.getId(), newUser.isFollowed());

                    Context context = getApplicationContext();
                    CharSequence text = "Unfollowed";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
    }
}