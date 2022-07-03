package sg.edu.np.mad.practical6_radchanondonsukkram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    private String TAG = "List Activity";
    ArrayList<User> userList = new ArrayList<>();

    //define dbHandler
    DBHandler dbHandler = new DBHandler(this, null, null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        userList = dbHandler.getUsers();

        if (userList.size() == 0){
            CreateUsers(dbHandler);
        }

        RecyclerView recyclerView = findViewById(R.id.recycleView);
        MyAdaptor mAdaptor = new MyAdaptor(userList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdaptor);

        ImageView userProfilePic = findViewById(R.id.userIcon);

    }

    // provide random integer for userName and userDescription
    private int randomInt(){
        Random ran = new Random();
        int ran_int = ran.nextInt(999999999);
        return ran_int;
    }

    // Randomise follow status
    private boolean randomStatus(){
        Random rd = new Random();
        return rd.nextBoolean();
    }

    // create 20 new users
    private void CreateUsers(DBHandler dbHandler){
        for (int i=1; i<21; i++){
            int ranInt = randomInt();
            String userName = "Name - " + String.valueOf(ranInt);
            String userDesc = "Description - " + String.valueOf(randomInt());
            User newUser = new User(userName,userDesc,i,randomStatus());
            dbHandler.addUser(newUser);
        }
    }
}