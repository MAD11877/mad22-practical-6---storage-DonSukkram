package sg.edu.np.mad.practical6_radchanondonsukkram;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private final String TAG = "DB Handler";

    public static String DATABASE_NAME = "usersDB.db";

    public static String USERS = "Users";
    public static String COLUMN_USERNAME = "Username";
    public static String COLUMN_USERDESCRIPTION = "UserDescription";
    public static String COLUMN_USERID = "UserId";
    public static String COLUMN_USERFOLLOW = "UserFollow";

    public static int DATABASE_VERSION = 1;

    //define DBHandler
    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    //create tables
    public void onCreate(SQLiteDatabase db){

        String CREATE_DATABASE = "CREATE TABLE " + USERS + "("
                + COLUMN_USERNAME + " TEXT, "
                + COLUMN_USERDESCRIPTION + " TEXT, "
                + COLUMN_USERID
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USERFOLLOW + " INTEGER"
                + ")";

        //execute sql commands
        db.execSQL(CREATE_DATABASE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + USERS);
        onCreate(db);
    }

    public ArrayList<User> getUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<User> userArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + USERS;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()){

            String name = cursor.getString(0);
            String desc = cursor.getString(1);
            int id = cursor.getInt(2);
            int followedStatus = cursor.getInt(3);

            boolean followStatusAdapted = false;

            if (followedStatus == 1){
                followStatusAdapted = true;
            }

            User user = new User(name, desc, id, followStatusAdapted);
            userArrayList.add(user);
        }
        db.close();
        return userArrayList;
    }

    public void addUser(User userData){
        ContentValues values = new ContentValues();

        values.put(COLUMN_USERNAME, userData.getName());
        values.put(COLUMN_USERDESCRIPTION, userData.getDescription());
        values.put(COLUMN_USERID, userData.getId());

        int userFollowedStatus;

        if (userData.isFollowed() == false){
            userFollowedStatus = 0;
        }
        else {
            userFollowedStatus = 1;
        }

        values.put(COLUMN_USERFOLLOW, userFollowedStatus);

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(USERS, null, values);
        Log.v(TAG, "Added to db");
        db.close();
    }

    public void updateFollowStatus(int userId, boolean currentFollowStatus){
        SQLiteDatabase db = this.getWritableDatabase();
        int newFollowStatus;

        if (currentFollowStatus == false){
            newFollowStatus = 0;
        }
        else{
            newFollowStatus = 1;
        }
        // edit Mood entry
        String changeTaskStatus = "UPDATE " + USERS + " SET " + COLUMN_USERFOLLOW + " = " + "\""+ newFollowStatus+ "\""  + " WHERE " + COLUMN_USERID + " = " + "\""+ userId+ "\"";
        db.execSQL(changeTaskStatus);
        db.close();
    }

}
