package com.xyz.sqlitedatabase.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xyz.sqlitedatabase.R;
import com.xyz.sqlitedatabase.adapter.StudentListAdapter;
import com.xyz.sqlitedatabase.database.DBManager;
import com.xyz.sqlitedatabase.interfaces.RecyclerItemClickListener;
import com.xyz.sqlitedatabase.model.Student;

import java.util.List;

/**
 * @author Lloyd Dcosta
 * This Activity displays the List of student stored in the database, where you can search for a
 * particular student, edit the details of the student, delete the student from database
 */
public class StudentListActivity extends AppCompatActivity implements RecyclerItemClickListener {

    private List<Student> studentList;
    private RecyclerView recyclerView;
    private SearchView mSearchView;
    private DBManager dbManager;
    private StudentListAdapter adapter;
    private EditText mEtName;
    private EditText mEtAddress;
    private LinearLayout mLlEdit;
    private Button mBtnEditAndSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        initViews();
        getDataFromIntent();
        setRecyclerAdapter();
        dbManager = DBManager.getInstance(this);
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        mSearchView = findViewById(R.id.searchView);
        mEtName = findViewById(R.id.etName);
        mEtAddress = findViewById(R.id.etAddress);
        mLlEdit = findViewById(R.id.llEditLayout);
        mBtnEditAndSave = findViewById(R.id.btnEdit);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String studentName) {
                /*
                Search for a particular student by starting a background thread
                 */
                Thread thread = new Thread(searchStudentFromDB(studentName));
                thread.start();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String studentName) {

                return false;
            }
        });
    }

    private void setRecyclerAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new StudentListAdapter(studentList, this);
        recyclerView.setAdapter(adapter);
    }

    private void getDataFromIntent() {
        if (getIntent() != null && getIntent().getSerializableExtra("studentList") != null) {
            studentList = (List<Student>) getIntent().getSerializableExtra("studentList");
        }
    }

    @Override
    public void onEditDataClicked(int position, final Student student) {
        mLlEdit.setVisibility(View.VISIBLE);
        mEtName.setText(student.getName());
        mEtAddress.setText(student.getAddress());
        mBtnEditAndSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student updateStudent = new Student(mEtName.getText().toString(),
                        mEtAddress.getText().toString(), student.getId());
                Thread thread = new Thread(updateStudentDetailsRunnable(updateStudent));
                thread.start();

            }
        });
    }

    @Override
    public void onDeleteClicked(int position, Student student) {
        Thread thread = new Thread(deleteStudentFromDBRunnable(student));
        thread.start();
    }

    /**
     * Search a particular student in the database by starting a background thread
     *
     * @param studentName student to be searched in database
     * @return returns runnable to start a thread
     */
    private Runnable searchStudentFromDB(final String studentName) {
        return new Runnable() {
            @Override
            public void run() {
                final List<Student> studentList = dbManager.searchStudent(studentName);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (studentList.size() >= 1) {
                            adapter.updateData(studentList);
                        } else {
                            Toast.makeText(StudentListActivity.this, "No Entries Found", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        };
    }

    /**
     * Update the existing student details in a background thread
     *
     * @param student details of the student to be updated
     * @return returns a runnable to start a thread
     */
    private Runnable updateStudentDetailsRunnable(final Student student) {
        return new Runnable() {
            @Override
            public void run() {
                dbManager.updateStudentDetails(student);
                /*
                Get the new List of students from Database after deleting the student
                 */
                final List<Student> studentList = dbManager.fetch();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mLlEdit.setVisibility(View.GONE);
                        adapter.updateData(studentList);
                    }
                });


            }
        };
    }

    /**
     * Delete a particular student details from the Database in a background
     *
     * @param student student details to be deleted
     * @return returns a runnable to start a thread
     */
    private Runnable deleteStudentFromDBRunnable(final Student student) {
        return new Runnable() {
            @Override
            public void run() {
                /*
                Delete the student details
                 */
                dbManager.deleteStudentData(student);
                /*
                Fetch the new List from Database after deleting the student from database
                 */
                final List<Student> studentList = dbManager.fetch();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.updateData(studentList);
                    }
                });
            }
        };
    }
}