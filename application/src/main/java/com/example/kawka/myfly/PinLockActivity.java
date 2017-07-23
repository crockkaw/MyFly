package com.example.kawka.myfly;

import android.app.Application;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.github.ajalt.reprint.core.AuthenticationFailureReason;
import com.github.ajalt.reprint.core.AuthenticationListener;
import com.github.ajalt.reprint.core.Reprint;
import com.github.orangegangsters.lollipin.lib.managers.AppLockActivity;
import com.github.orangegangsters.lollipin.lib.managers.LockManager;

import uk.me.lewisdeane.ldialogs.BaseDialog;
import uk.me.lewisdeane.ldialogs.CustomDialog;


public class PinLockActivity extends AppLockActivity {


    @Override
    public void onCreateActivity(){
        Reprint.initialize(this);

        Reprint.authenticate(new AuthenticationListener() {
            @Override
            public void onSuccess(int moduleTag) {

                fingersucces ();

                Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(@NonNull AuthenticationFailureReason failureReason, boolean fatal,
                                  @Nullable CharSequence errorMessage, int moduleTag, int errorCode) {

                errorMess(failureReason, fatal, errorMessage,  moduleTag,  errorCode);
            }
        });

    }

    @Override
    public void showForgotDialog() {
        Resources res = getResources();
        // Create the builder with required paramaters - Context, Title, Positive Text
        CustomDialog.Builder builder = new CustomDialog.Builder(this,
                res.getString(R.string.activity_dialog_title),
                res.getString(R.string.activity_dialog_accept));
        builder.content(res.getString(R.string.activity_dialog_content));
        builder.negativeText(res.getString(R.string.activity_dialog_decline));

        //Set theme
        builder.darkTheme(false);
        builder.typeface(Typeface.SANS_SERIF);
        builder.positiveColor(res.getColor(R.color.light_blue_500)); // int res, or int colorRes parameter versions available as well.
        builder.negativeColor(res.getColor(R.color.light_blue_500));
        builder.rightToLeft(false); // Enables right to left positioning for languages that may require so.
        builder.titleAlignment(BaseDialog.Alignment.CENTER);
        builder.buttonAlignment(BaseDialog.Alignment.CENTER);
        builder.setButtonStacking(false);

        //Set text sizes
        builder.titleTextSize((int) res.getDimension(R.dimen.activity_dialog_title_size));
        builder.contentTextSize((int) res.getDimension(R.dimen.activity_dialog_content_size));
        builder.positiveButtonTextSize((int) res.getDimension(R.dimen.activity_dialog_positive_button_size));
        builder.negativeButtonTextSize((int) res.getDimension(R.dimen.activity_dialog_negative_button_size));

        //Build the dialog.
        CustomDialog customDialog = builder.build();
        customDialog.setCanceledOnTouchOutside(false);
        customDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        customDialog.setClickListener(new CustomDialog.ClickListener() {
            @Override
            public void onConfirmClick() {
                Toast.makeText(getApplicationContext(), "Yes", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelClick() {
                Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
            }
        });

        // Show the dialog.
        customDialog.show();
    }

    @Override
    public void onPinFailure(int attempts) {

    }


    @Override
    public void onPinSuccess(int attempts) {
                Intent intent = new Intent(this,StartActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                finish();
                Process.killProcess(Process.myPid());
        startActivity(intent);

    }

    @Override
    public int getPinLength() {
        return super.getPinLength();//you can override this method to change the pin length from the default 4
    }
}
