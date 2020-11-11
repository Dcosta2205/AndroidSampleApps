package com.xyz.sqlitedatabase.interfaces;

import com.xyz.sqlitedatabase.model.Student;

public interface RecyclerItemClickListener {

    void onEditDataClicked(int position, Student student);

    void onDeleteClicked(int position, Student student);
}
