package com.example.kawka.myfly;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ScrollView;
import android.widget.TextView;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.kawka.myfly.models.ZibConfirm;
import com.example.kawka.myfly.network.ApiService;
import com.example.kawka.myfly.network.DownloadFile;
import com.example.kawka.myfly.network.ServiceGenerator;
import com.wangjie.androidinject.annotation.annotations.base.AIView;
import com.wangjie.androidbucket.utils.ABTextUtil;
import com.wangjie.androidbucket.utils.imageprocess.ABShape;
import com.wangjie.androidinject.annotation.annotations.base.AILayout;
import com.wangjie.androidinject.annotation.present.AIActionBarActivity;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionHelper;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RFACLabelItem;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RapidFloatingActionContentLabelList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@AILayout(R.layout.activity_maktu_status)
public class MAStatusActivity extends AIActionBarActivity implements RapidFloatingActionContentLabelList.OnRapidFloatingActionContentLabelListListener {

    String dd, mm, yy, date;

boolean yetDownload;

    String docUrl;

    ProgressDialog mProgressDialog;


    ProgressDialog progressDialog;

@AIView(R.id.activity_main_rfal)
private RapidFloatingActionLayout rfaLayout;
@AIView(R.id.activity_main_rfab)
private RapidFloatingActionButton rfaBtn;
private RapidFloatingActionHelper rfabHelper;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_maktu_status);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Status & ZIB");

//        setupActionBar();
        Bundle bundle = getIntent().getExtras();
        String zib = bundle.getString("a");
        if (zib.equals("zib")){
            zib();}

        setupWindowAnimations();
        floatingButtonMenu();

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(R.color.statusbar_color));

        date();
        try {
            zibDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void date() {
        java.util.Calendar instance = java.util.Calendar.getInstance();
        dd = String.valueOf(instance.get(java.util.Calendar.DAY_OF_MONTH));
        mm = String.valueOf(instance.get(java.util.Calendar.MONTH)+1);
        yy = String.valueOf(instance.get(java.util.Calendar.YEAR));
        date = dd + "." + mm + "." + yy;
    }

    private void zibDate() throws ParseException {

        String dt = String.valueOf(date);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy");

        Calendar r1 = Calendar.getInstance();
        r1.setTime(sdf.parse(dt));
        r1.add(Calendar.DATE, -1);
        String rel1 = sdf.format(r1.getTime());

        Calendar e1 = Calendar.getInstance();
        e1.setTime(sdf.parse(dt));
        e1.add(Calendar.DATE, 6);
        String ext1 = sdf.format(e1.getTime());

        TextView releaseDate1 = (TextView) findViewById(R.id.releaseDate1);
        TextView expireDate1 = (TextView) findViewById(R.id.expireDate1);
        releaseDate1.setText(rel1);
        expireDate1.setText(ext1);


        Calendar r2 = Calendar.getInstance();
        r2.setTime(sdf.parse(dt));
        r2.add(Calendar.DATE, -5);
        String rel2 = sdf.format(r2.getTime());

        Calendar e2 = Calendar.getInstance();
        e2.setTime(sdf.parse(dt));
        e2.add(Calendar.DATE, 1);
        String ext2 = sdf.format(e2.getTime());

        TextView releaseDate2 = (TextView) findViewById(R.id.releaseDate2);
        TextView expireDate2 = (TextView) findViewById(R.id.expireDate2);
        releaseDate2.setText(rel2);
        expireDate2.setText(ext2);


        Calendar r3 = Calendar.getInstance();
        r3.setTime(sdf.parse(dt));
        r3.add(Calendar.DATE, -10);
        String rel3 = sdf.format(r3.getTime());

        Calendar e3 = Calendar.getInstance();
        e3.setTime(sdf.parse(dt));
        e3.add(Calendar.DATE, -3);
        String ext3 = sdf.format(e3.getTime());

        TextView releaseDate3 = (TextView) findViewById(R.id.releaseDate3);
        TextView expireDate3 = (TextView) findViewById(R.id.expireDate3);
        releaseDate3.setText(rel3);
        expireDate3.setText(ext3);
        expireDate3.setTextColor(0xFFD60000);
    }

    public void zib(){
        final ScrollView scrollview = ((ScrollView) findViewById(R.id.activity_mastatus));
        scrollview.post(new Runnable() {
            @Override
            public void run() {
                scrollview.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }


    @Override
    public void onRFACItemLabelClick(int position, RFACLabelItem item) {
            rfabHelper.toggleContent();
            }

    @Override
    public void onRFACItemIconClick(int position, RFACLabelItem item) {
            rfabHelper.toggleContent();
            }



    private void setupWindowAnimations() {
        Transition transition;
        transition = buildEnterTransition();
        getWindow().setEnterTransition(transition);


    }

    private Transition buildEnterTransition() {
        Slide enterTransition = new Slide();
        enterTransition.setDuration(500);
        enterTransition.setSlideEdge(Gravity.RIGHT);
        return enterTransition;
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Transition returnTransition = buildEnterTransition();
            getWindow().setReturnTransition(returnTransition);

            finishAfterTransition();
        }
        return super.onOptionsItemSelected(item);
    }


    private void floatingButtonMenu(){

        RapidFloatingActionContentLabelList rfaContent = new RapidFloatingActionContentLabelList(context);
        rfaContent.setOnRapidFloatingActionContentLabelListListener(this);
        List<RFACLabelItem> items = new ArrayList<>();
        items.add(new RFACLabelItem<Integer>()
                .setLabel(getString(R.string.legend_label_green))
                .setLabelColor(Color.WHITE)
                .setResId(R.mipmap.ico_test_d)
                .setIconNormalColor(0xff06a64f)
                .setLabelSizeSp(14)
                .setLabelBackgroundDrawable(ABShape.generateCornerShapeDrawable(0xaa000000, ABTextUtil.dip2px(context, 4)))
                .setWrapper(0)
        );

        items.add(new RFACLabelItem<Integer>()
                .setResId(R.mipmap.ico_test_d)
                .setLabel(getString(R.string.legend_label_yellow))
                .setIconNormalColor(0xffecec14)
                .setLabelColor(Color.WHITE)
                .setLabelSizeSp(14)
                .setLabelBackgroundDrawable(ABShape.generateCornerShapeDrawable(0xaa000000, ABTextUtil.dip2px(context, 4)))
                .setWrapper(2)
        );
        items.add(new RFACLabelItem<Integer>()
                .setLabel(getString(R.string.legend_label_purple))
                .setResId(R.mipmap.ico_test_d)
                .setIconNormalColor(0xffea9054)
                .setLabelColor(Color.WHITE)
                .setLabelSizeSp(14)
                .setLabelBackgroundDrawable(ABShape.generateCornerShapeDrawable(0xaa000000, ABTextUtil.dip2px(context, 4)))
                .setWrapper(1)
        );
        items.add(new RFACLabelItem<Integer>()
                .setLabel(getString(R.string.legend_label_red))
                .setResId(R.mipmap.ico_test_d)
                .setIconNormalColor(0xffea1212)
                .setLabelColor(Color.WHITE)
                .setLabelSizeSp(14)
                .setLabelBackgroundDrawable(ABShape.generateCornerShapeDrawable(0xaa000000, ABTextUtil.dip2px(context, 4)))
                .setWrapper(3)
        );

                rfaContent
                .setItems(items)
                .setIconShadowRadius(ABTextUtil.dip2px(context, 5))
                .setIconShadowColor(0xff888888)
                .setIconShadowDy(ABTextUtil.dip2px(context, 5))
        ;

        rfabHelper = new RapidFloatingActionHelper(
                context,
                rfaLayout,
                rfaBtn,
                rfaContent
        ).build();


    }

//    public void komisja_onClick(View view) {
//
//
////        boolean wrapInScrollView = true;
////        new MaterialDialog.Builder(this)
////                .title("Komisja Lekarska")
////                .customView(R.layout.custom_view, wrapInScrollView)
////                .positiveText(R.string.positive)
////                .show();
//
//
//        new MaterialDialog.Builder(this)
//                .title("Komisja Lekarska")
//                .items(komisjaList)
//                .positiveText("OK")
//                .show();
//
//    }



    public void komisja_onClick(View view) throws ParseException {

        java.util.Calendar instance = java.util.Calendar.getInstance();
        dd = String.valueOf(instance.get(java.util.Calendar.DAY_OF_MONTH));
        mm = String.valueOf(instance.get(java.util.Calendar.MONTH)+1);
        yy = String.valueOf(instance.get(java.util.Calendar.YEAR));
        date = dd + "." + mm + "." + yy;


        String dt = date;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy");

        Calendar d1 = Calendar.getInstance();
        d1.setTime(sdf.parse(dt));
        d1.add(Calendar.DATE, 49);
        Date resultdate = new Date(d1.getTimeInMillis());

        String dat1 = sdf.format(resultdate);

        SimpleDateFormat sdf2 = new SimpleDateFormat("dd");
        SimpleDateFormat sdf3 = new SimpleDateFormat("mm");


        String dd1 = sdf2.format(d1.getTime());
        String mm1 = sdf3.format(d1.getTime());

        Dialog dialog;
        View v;
        v =  (LayoutInflater.from(this))
                .inflate(R.layout.dialog_status, null);
        final AlertDialog.Builder alertBuldier =
                new AlertDialog.Builder(this);
        alertBuldier.setView(v);
        final TextView titleText = (TextView)
                v.findViewById(R.id.titleText);
        titleText.setText(R.string.status_komisja);

        final TextView textView1_1 = (TextView)
                v.findViewById(R.id.textView1_1);
        final TextView textView1_2 = (TextView)
                v.findViewById(R.id.textView1_2);
        textView1_1.setText(R.string.status_komisja_1);
        textView1_2.setText(dd1 + "." + String.valueOf(instance.get(Calendar.MONTH)+2) + "." + String.valueOf(instance.get(java.util.Calendar.YEAR)-1));

        final TextView textView2_1 = (TextView)
                v.findViewById(R.id.textView2_1);
        final TextView textView2_2 = (TextView)
                v.findViewById(R.id.textView2_2);
        textView2_1.setText(R.string.status_komisja_2);
        textView2_2.setText(dd1 + "." + String.valueOf(instance.get(Calendar.MONTH)+2) + "." + yy);;

        final TextView textView3_1 = (TextView)
                v.findViewById(R.id.textView3_1);
        final TextView textView3_2 = (TextView)
                v.findViewById(R.id.textView3_2);
        textView3_1.setText(R.string.status_komisja_3);
        textView3_2.setText("Z1C");

        final TextView textView4_1 = (TextView)
                v.findViewById(R.id.textView4_1);
        final TextView textView4_2 = (TextView)
                v.findViewById(R.id.textView4_2);
        textView4_1.setText(R.string.status_komisja_4);
        textView4_2.setText("49");


        alertBuldier.setCancelable(true)
                .setPositiveButton("OK", null);
        dialog = alertBuldier.create();
        dialog.show();
    }

    public void openDoc1(View view) {
        new DownloadFile().execute("http://turdoc.wex.pl/file/wys-temp.pdf");
    }



    public void openDoc2(View view) {
        new DownloadFile().execute("http://turdoc.wex.pl/file/okres_wiosenny_s.pdf");

    }

    public void openDoc3(View view) {
        new DownloadFile().execute("http://turdoc.wex.pl/file/maszyna-czlowiek_s.pdf");

    }

    public void confirmDoc(View view) {
        ApiService client = ServiceGenerator.createService(ApiService.class, "client", "Turawa2016");

        ZibConfirm zibCon = new ZibConfirm("21", "264","56","2017-05-12");
        Call<ZibConfirm> call = client.createZibCon("application/vnd.oracle.adf.resourceitem+json",zibCon);
        call.enqueue(new Callback<ZibConfirm>() {
            @Override
            public void onResponse(Call<ZibConfirm> call, Response<ZibConfirm> response) {
                Toast.makeText(MAStatusActivity.this, "Potwierdzono zapoznanie się z dokumentem", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<ZibConfirm> call, Throwable t) {
                Toast.makeText(MAStatusActivity.this, "Błąd", Toast.LENGTH_SHORT).show();}
        });
    }


//    private class DownloadFile extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            // Create progress dialog
//            mProgressDialog = new ProgressDialog(MAStatusActivity.this);
//            // Set your progress dialog Title
//            mProgressDialog.setTitle("Trwa pobieranie dokumentu");
//            // Set your progress dialog Message
//            mProgressDialog.setMessage("Proszę czekać...");
//            mProgressDialog.setIndeterminate(false);
//            mProgressDialog.setMax(100);
//            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//            // Show progress dialog
//            mProgressDialog.show();
//        }
//
//        @Override
//        protected String doInBackground(String... Url) {
//            try {
//                URL url = new URL(Url[0]);
//                URLConnection connection = url.openConnection();
//                connection.connect();
//
//                // Detect the file lenghth
//                int fileLength = connection.getContentLength();
//
//                // Locate storage location
//                String filepath = Environment.getExternalStorageDirectory()
//                        .getPath();
//
//                // Download the file
//                InputStream input = new BufferedInputStream(url.openStream());
//
//                // Save the downloaded file
//                OutputStream output = new FileOutputStream(filepath + "/"
//                        + "doc.pdf");
//
//                byte data[] = new byte[1024];
//                long total = 0;
//                int count;
//                while ((count = input.read(data)) != -1) {
//                    total += count;
//                    // Publish the progress
//                    publishProgress((int) (total * 100 / fileLength));
//                    output.write(data, 0, count);
//                }
//
//                // Close connection
//                output.flush();
//                output.close();
//                input.close();
//            } catch (Exception e) {
//                // Error Log
//                Log.e("Error", e.getMessage());
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onProgressUpdate(Integer... progress) {
//            super.onProgressUpdate(progress);
//            mProgressDialog.setProgress(progress[0]);
//
//            if (mProgressDialog.getProgress()==100) {
//                mProgressDialog.dismiss();
//
//                File pdfFile = new File(Environment.getExternalStorageDirectory() + "/doc.pdf");  // -> filename = maven.pdf
//                Uri path = Uri.fromFile(pdfFile);
//                Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
//                pdfIntent.setDataAndType(path, "application/pdf");
//                pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//                try {
//                    startActivity(pdfIntent);
//                } catch (ActivityNotFoundException e) {
//                    Toast.makeText(MAStatusActivity.this, "Brak aplikacji do wyświetlania dokumentów pdf ", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//
//
//    }

}

