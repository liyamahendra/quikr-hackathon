package com.mahendraliya.quikrcars.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.mahendraliya.quikrcars.R;

/**
 * Created by mahendraliya on 22/08/15.
 */
public class QCBaseActivity extends AppCompatActivity {
    protected void showToastShort(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void showToastLong(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    protected void showErrorAlert(Context context, String message) {
        showAlertWithTitleAndMessageAndCallbacks(context, context.getString(R.string.dialog_title_error), message, null, true);
    }

    protected void showAlertWithTitleAndMessage(Context context, String title, String message) {
        showAlertWithTitleAndMessageAndCallbacks(context, title, message, null, false);
    }

    protected void showAlertWithTitleAndMessageAndCallbacks(Context context, String title, String message, DialogInterface.OnClickListener successClickListener, boolean isError) {

        AlertDialog.Builder alert = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, successClickListener);

        if (isError) {
            alert.setIcon(android.R.drawable.ic_dialog_alert);
        } else {
            alert.setIcon(android.R.drawable.ic_dialog_info);
        }

        alert.show();
    }
}
