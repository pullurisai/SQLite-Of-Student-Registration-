package com.sqlitesms;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StudentActivity extends Activity {

	EditText etRoolNo, etMarks, etName;
	Button btnAdd, btnDelete, btnModify, btnView, btnViewAll, btnShow;
	SQLiteDatabase myDB;
	Cursor c;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student_layout);
		etRoolNo = (EditText) findViewById(R.id.etRoolNo);
		etMarks = (EditText) findViewById(R.id.etMarks);
		etName = (EditText) findViewById(R.id.etName);
		btnAdd = (Button) findViewById(R.id.btnAdd);
		btnDelete = (Button) findViewById(R.id.btnDelete);
		btnModify = (Button) findViewById(R.id.btnModify);
		btnView = (Button) findViewById(R.id.btnView);
		btnViewAll = (Button) findViewById(R.id.btnViewAll);
		btnShow = (Button) findViewById(R.id.btnShow);

		myDB = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
		myDB.execSQL("CREATE TABLE IF NOT EXISTS Student(rollno VARCHAR,name VARCHAR,marks VARCHAR);");

	}

	public void Methods(View view) {
		switch (view.getId()) {
		case R.id.btnAdd:
			if (view == btnAdd) {
				// Checking empty fields
				if (etRoolNo.getText().toString().trim().length() == 0
						|| etName.getText().toString().trim().length() == 0
						|| etMarks.getText().toString().trim().length() == 0) {
					Utils.toastItBaby("Please Enter All values",
							getBaseContext());
				}
				myDB.execSQL("INSERT INTO Student VALUES('"
						+ etRoolNo.getText() + "','" + etName.getText() + "','"
						+ etMarks.getText() + "');");
				Utils.toastItBaby("Success Record Added", getBaseContext());
				clearText();
			}
			break;
		case R.id.btnDelete:
			if (etRoolNo.getText().toString().trim().length() == 0) {
				Utils.toastItBaby("Please enter Rollno", getBaseContext());
			}
			c = myDB.rawQuery(
					"SELECT * FROM Student WHERE rollno='" + etRoolNo.getText()
							+ "'", null);
			if (c.moveToFirst()) {
				// Deleting record if found
				myDB.execSQL("DELETE FROM Student WHERE rollno='"
						+ etRoolNo.getText() + "'");
				Utils.toastItBaby("Success Record Deleted", getBaseContext());
			} else {
				Utils.toastItBaby("Error Invalid Rollno", getBaseContext());
			}
			clearText();
			break;
		case R.id.btnModify:
			if (etRoolNo.getText().toString().trim().length() == 0) {
				Utils.toastItBaby("Please enter Rollno", getBaseContext());
			}
			c = myDB.rawQuery(
					"SELECT * FROM Student WHERE rollno='" + etRoolNo.getText()
							+ "'", null);
			if (c.moveToFirst()) {
				// Modifying record if found
				myDB.execSQL("UPDATE Student SET name='" + etName.getText()
						+ "',marks='" + etMarks.getText() + "' WHERE rollno='"
						+ etRoolNo.getText() + "'");
				Utils.toastItBaby("Success Record Modified", getBaseContext());
			} else {
				Utils.toastItBaby("Error Invalid Rollno", getBaseContext());
			}
			clearText();
			break;
		case R.id.btnView:
			if (etRoolNo.getText().toString().trim().length() == 0) {
				Utils.toastItBaby("Please enter Rollno", getBaseContext());
			}
			c = myDB.rawQuery(
					"SELECT * FROM Student WHERE rollno='" + etRoolNo.getText()
							+ "'", null);
			if (c.moveToFirst()) {
				// Displaying record if found
				etName.setText(c.getString(1));
				etMarks.setText(c.getString(2));
			} else {
				Utils.toastItBaby("Error Invalid Rollno", getBaseContext());
				clearText();
			}
			break;
		case R.id.btnViewAll:
			c = myDB.rawQuery("SELECT * FROM Student", null);
			// Checking if no records found
			if (c.getCount() == 0) {
				Utils.toastItBaby("Error No Records Found", getBaseContext());
			}
			StringBuffer buffer = new StringBuffer();
			while (c.moveToNext()) {
				buffer.append("Rollno: " + c.getString(0) + "\n");
				buffer.append("Name: " + c.getString(1) + "\n");
				buffer.append("Marks: " + c.getString(2) + "\n\n");
			}
			// Displaying all records
			showMessage("Student Details", buffer.toString());
			break;
		case R.id.btnShow:
			showMessage("Student Management Application",
					"Developed By Venkat Sai");
			break;
		}
	}

	public void clearText() {
		etRoolNo.setText("");
		etName.setText("");
		etMarks.setText("");

	}

	public void showMessage(String title, String message) {
		Builder builder = new Builder(this);
		builder.setCancelable(true);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.show();
	}
}
