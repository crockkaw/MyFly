package com.example.kawka.myfly;

        import android.content.Intent;
        import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import com.github.ajalt.reprint.core.AuthenticationFailureReason;
        import com.github.ajalt.reprint.core.AuthenticationListener;
        import com.github.ajalt.reprint.core.Reprint;
        import android.widget.TextView;



public class FingerprintActivity extends AppCompatActivity {

    TextView errorText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint);

        Reprint.initialize(this);

        errorText = (TextView) findViewById(R.id.error_TextView);

//        startTraditional();
    }



//    public void start(View view) {
//        startTraditional();
//    }



//    private void startTraditional() {
//
//        Reprint.authenticate(new AuthenticationListener() {
//            @Override
//            public void onSuccess(int moduleTag) {
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
//            }
//
//            @Override
//            public void onFailure(@NonNull AuthenticationFailureReason failureReason, boolean fatal,
//                                  @Nullable CharSequence errorMessage, int moduleTag, int errorCode) {
//                errorText.setText(errorMessage.toString());
//            }
//        });
//
//    }

}