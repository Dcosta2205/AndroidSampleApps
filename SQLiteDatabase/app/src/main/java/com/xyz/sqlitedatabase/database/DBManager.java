package com.xyz.sqlitedatabase.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xyz.sqlitedatabase.model.Student;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private static DBManager dbManager;
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    private DBManager(Context context) {
        this.context = context;
    }

    public static DBManager getInstance(Context context) {
        if (dbManager == null) {
            dbManager = new DBManager(context);
        }
        return dbManager;
    }

    /**
     * Make sure to call this function before you try to access the sqliteDatabase object
     */
    public void open() {
        dbHelper = new DatabaseHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    /**
     * Don't forget to call this when you go out of the app
     */
    public void close() {
        dbHelper.close();
    }

    /**
     * This method is used to insert the student name and address into the database
     *
     * @param name    name of the student to be inserted
     * @param address address of the student to be inserted
     */
    public void insert(String name, String address) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.STUDENT_NAME, name);
        contentValue.put(DatabaseHelper.STUDENT_ADDRESS, address);
        sqLiteDatabase.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    /**
     * Fetch the students stored in database
     *
     * @return returns List of students from the database
     */
    public List<Student> fetch() {
        List<Student> studentList = new ArrayList<>();
        String[] columns = new String[]{DatabaseHelper.ID, DatabaseHelper.STUDENT_NAME, DatabaseHelper.STUDENT_ADDRESS};
        Cursor cursor = sqLiteDatabase.query(DatabaseHelper.TABLE_NAME, columns, null,
                null, null, null, null);
        while (cursor.moveToNext()) {
            Student student = new Student(cursor.getString(cursor.getColumnIndex(DatabaseHelper.STUDENT_NAME)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.STUDENT_ADDRESS)),
                    cursor.getInt(cursor.getColumnIndex(DatabaseHelper.ID)));
            studentList.add(student);
        }
        return studentList;
    }

    /**
     * Search a particular student in the database
     *
     * @param name search a student by name
     * @return return a List of student by that name
     */
    public List<Student> searchStudent(String name) {
        List<Student> studentList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Students WHERE name = ? ", new String[]{name});
        while (cursor.moveToNext()) {
            Student student = new Student(cursor.getString(cursor.getColumnIndex(DatabaseHelper.STUDENT_NAME)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.STUDENT_ADDRESS)),
                    cursor.getInt(cursor.getColumnIndex(DatabaseHelper.ID)));
            studentList.add(student);
        }
        return studentList;
    }

    /**
     * Update a particular student details by using student id
     *
     * @param student new details of the student to be updated
     */
    public void updateStudentDetails(Student student) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.STUDENT_NAME, student.getName());
        contentValues.put(DatabaseHelper.STUDENT_ADDRESS, student.getAddress());
        sqLiteDatabase.update(DatabaseHelper.TABLE_NAME, contentValues, "id = " + student.getId(), null);
    }

    /**
     * Delete a specific student from the database
     *
     * @param student particular student to be deleted from database
     */
    public void deleteStudentData(Student student) {
        sqLiteDatabase.delete(DatabaseHelper.TABLE_NAME, "id = " + student.getId(), null);
    }
}
