package com.example.neverlookfit.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.neverlookfit.model.Goal;
import com.example.neverlookfit.model.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "UserManager.db";

    // User table name
    private static final String TABLE_USER = "user";
    private static final String TABLE_GOAL = "goal";

    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    private static final String COLUMN_USER_DATE = "user_date";
    private static final String COLUMN_USER_WEIGHT = "user_weight";
    private static final String COLUMN_USER_BODYFAT = "user_bodyfat";
    private static final String COLUMN_USER_THIGH = "user_thigh";
    private static final String COLUMN_USER_CHEST = "user_chest";
    private static final String COLUMN_USER_BICEPS = "user_bicep";
    private static final String COLUMN_USER_HIP = "user_hip";
    private static final String COLUMN_USER_GOALID = "user_goal_id";

    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE "
            + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT,"
            + COLUMN_USER_PASSWORD + " TEXT"
            + ")";

    private String CREATE_GOAL_TABLE = "CREATE TABLE "
            + TABLE_GOAL + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_DATE + " TEXT,"
            + COLUMN_USER_WEIGHT + " DOUBLE,"
            + COLUMN_USER_BODYFAT + " DOUBLE,"
            + COLUMN_USER_THIGH + " DOUBLE,"
            + COLUMN_USER_CHEST + " DOUBLE,"
            + COLUMN_USER_BICEPS + " DOUBLE,"
            + COLUMN_USER_HIP + " DOUBLE,"
            + COLUMN_USER_GOALID + " INT,"
            + "FOREIGN KEY (" + COLUMN_USER_GOALID + ") REFERENCES " + TABLE_USER + "(user_id)"
            + ")";

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    private String DROP_GOAL_TABLE = "DROP TABLE IF EXISTS " + TABLE_GOAL;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_GOAL_TABLE);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys = ON;");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_GOAL_TABLE);

        // Create tables again
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public void addGoal(Goal goal) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_DATE, goal.getDate());
        values.put(COLUMN_USER_WEIGHT, goal.getWeight());
        values.put(COLUMN_USER_BODYFAT, goal.getBodyFat());
        values.put(COLUMN_USER_THIGH, goal.getThigh());
        values.put(COLUMN_USER_CHEST, goal.getChest());
        values.put(COLUMN_USER_BICEPS, goal.getBicep());
        values.put(COLUMN_USER_HIP, goal.getHip());

        // Inserting Row
        db.insert(TABLE_GOAL, null, values);
        db.close();
    }

    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_EMAIL,
                COLUMN_USER_NAME,
                COLUMN_USER_PASSWORD
        };
        // sorting orders
        String sortOrder =
                COLUMN_USER_NAME + " ASC";
        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order

        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    public List<Goal> getAllGoal() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_DATE,
                COLUMN_USER_WEIGHT,
                COLUMN_USER_BODYFAT,
                COLUMN_USER_THIGH,
                COLUMN_USER_CHEST,
                COLUMN_USER_BICEPS,
                COLUMN_USER_HIP
        };
        // sorting orders
        String sortOrder =
                COLUMN_USER_DATE + " ASC";

        List<Goal> goalList = new ArrayList<Goal>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null        //The sort order
        );

        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Goal goal = new Goal();
                goal.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                goal.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_USER_DATE)));
                goal.setWeight(Double.parseDouble(cursor.getString(cursor.getColumnIndex(COLUMN_USER_WEIGHT))));
                goal.setBodyFat(Double.parseDouble(cursor.getString(cursor.getColumnIndex(COLUMN_USER_BODYFAT))));
                goal.setThigh(Double.parseDouble(cursor.getString(cursor.getColumnIndex(COLUMN_USER_THIGH))));
                goal.setChest(Double.parseDouble(cursor.getString(cursor.getColumnIndex(COLUMN_USER_CHEST))));
                goal.setBicep(Double.parseDouble(cursor.getString(cursor.getColumnIndex(COLUMN_USER_BICEPS))));
                goal.setHip(Double.parseDouble(cursor.getString(cursor.getColumnIndex(COLUMN_USER_HIP))));

                goalList.add(goal);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return goalList;
    }

    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // updating row
        db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    public void updateGoal(Goal goal) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_DATE, goal.getDate());
        values.put(COLUMN_USER_WEIGHT, goal.getWeight());
        values.put(COLUMN_USER_BODYFAT, goal.getBodyFat());
        values.put(COLUMN_USER_THIGH, goal.getThigh());
        values.put(COLUMN_USER_CHEST, goal.getChest());
        values.put(COLUMN_USER_BICEPS, goal.getBicep());
        values.put(COLUMN_USER_HIP, goal.getHip());

        // updating row
        db.update(TABLE_GOAL, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(goal.getId())});
        db.close();
    }

    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    public void deleteGoal(Goal goal) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_GOAL, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(goal.getId())});
        db.close();
    }

    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }
}