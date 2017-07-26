package com.example.kawka.myfly;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.github.orangegangsters.lollipin.lib.managers.AppLock;
import com.github.orangegangsters.lollipin.lib.managers.LockManager;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    EditText _emailText;
    EditText _passwordText;
//    @Bind(R.id.btn_login) Button _loginButton;

    Button _loginButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        ButterKnife.bind(this);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(R.color.statusbar_color));


        _loginButton = (Button) findViewById(R.id.btn_login);
        _emailText = (EditText) findViewById(R.id.input_email);
        _passwordText = (EditText) findViewById(R.id.input_password);

        _loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
            login();
            }
        });





        boolean option = isNetworkAvailable();

        if (!option){
            Snackbar.make((findViewById(R.id.loginLayout)), "Brak połączenia z internetem, zamknij aplikację.", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Zamknij", new MyUndoListener()).show();

            _loginButton.setEnabled(false);
        }


    }

    public void login() {
        Log.d(TAG, "Login");





        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Uwierzytelnianie...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        progressDialog.dismiss();

                        if (!validate()) {
                            onLoginFailed();
                            return;
                        }
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();

                    }
                }, 1200);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {

        AppLock appLock = LockManager.getInstance().getAppLock(); if (appLock != null) { appLock.disableAndRemoveConfiguration(); }

        Intent intent = new Intent(getApplication(), PinLockActivity.class);
        intent.putExtra(AppLock.EXTRA_TYPE, AppLock.ENABLE_PINLOCK);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);

//        Intent i = new Intent(getApplication(), StartActivity.class);
//        i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//        startActivity(i);
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Nieudana weryfikacja, wprowadź poprawne dane", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (!email.equals("admin")) {
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (!password.equals("admin")) {
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void passportDialog(View view) {

        new AlertDialog.Builder(this)
                .setMessage("Dane uwierzytelniające możesz zmienić tylko za pomocą aplikacji webowej. ")
                .setCancelable(true)
                .setPositiveButton("OK", null)
                .show();
    }



}