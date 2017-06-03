package com.example.kawka.myfly.network;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;
import com.example.kawka.myfly.MyApplication;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by kawka on 30.05.2017.
 */

public class DownloadFile extends AsyncTask<String, Integer, String> {

    ProgressDialog mProgressDialog;

    Context context;


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        context = MyApplication.getAppContext();
        // Create progress dialog
        mProgressDialog = new ProgressDialog(context);
        // Set your progress dialog Title
        mProgressDialog.setTitle("Trwa pobieranie dokumentu");
        // Set your progress dialog Message
        mProgressDialog.setMessage("Proszę czekać...");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setMax(100);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        // Show progress dialog
        mProgressDialog.show();
    }

    @Override
    protected String doInBackground(String... Url) {
        try {
            URL url = new URL(Url[0]);
            URLConnection connection = url.openConnection();
            connection.connect();

            // Detect the file lenghth
            int fileLength = connection.getContentLength();

            // Locate storage location
            String filepath = Environment.getExternalStorageDirectory()
                    .getPath();

            // Download the file
            InputStream input = new BufferedInputStream(url.openStream());

            // Save the downloaded file
            OutputStream output = new FileOutputStream(filepath + "/"
                    + "doc.pdf");

            byte data[] = new byte[1024];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
                total += count;
                // Publish the progress
                publishProgress((int) (total * 100 / fileLength));
                output.write(data, 0, count);
            }

            // Close connection
            output.flush();
            output.close();
            input.close();
        } catch (Exception e) {
            // Error Log
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        super.onProgressUpdate(progress);
        mProgressDialog.setProgress(progress[0]);

        if (mProgressDialog.getProgress()==100) {
            mProgressDialog.dismiss();

            context = MyApplication.getAppContext();

            File pdfFile = new File(Environment.getExternalStorageDirectory() + "/doc.pdf");  // -> filename = maven.pdf
            Uri path = Uri.fromFile(pdfFile);
            Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
            pdfIntent.setDataAndType(path, "application/pdf");
            pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try {
                context.startActivity(pdfIntent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(context, "Brak aplikacji do wyświetlania dokumentów pdf ", Toast.LENGTH_SHORT).show();
            }
        }
    }
}