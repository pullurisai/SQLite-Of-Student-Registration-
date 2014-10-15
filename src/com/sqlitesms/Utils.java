package com.sqlitesms;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

public class Utils {

	EditText etRoolNo, etMarks, etName;
	public static void toastItBaby(String msg, Context context) {
		Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
		toast.show();
	}
}

