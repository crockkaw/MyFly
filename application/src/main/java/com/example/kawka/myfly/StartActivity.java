package com.example.kawka.myfly;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.kawka.myfly.models.NalotAktualnyAdapter;

public class StartActivity extends AppCompatActivity {

    Thread thread;
    NalotAktualnyAdapter nalotAktualnyAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

//        nalotAktualnyAdapter = new NalotAktualnyAdapter();

        Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(this.getResources().getColor(R.color.statusbar_color));



        thread = new Thread(){
            public void run(){
                try {
                    sleep(4500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    chooseOption();
                }}

        };
        thread.start();


    }

    public void startClick(View view) {
        thread.interrupt();
        chooseOption();
    }

    @Override
    public void onBackPressed()
    {
        // code here to show dialog
        super.onBackPressed();  // optional depending on your needs
    }

    public void chooseOption(){
        boolean option = isNetworkAvailable();

        if (option){
            jump();
        } else {
            Snackbar.make((findViewById(R.id.activity_start)), "Brak połączenia z internetem, zamknij aplikację.", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Zamknij", new MyUndoListener()).show();


        }

    }

    public void jump(){
        Intent intent = new Intent(getApplication(),MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
