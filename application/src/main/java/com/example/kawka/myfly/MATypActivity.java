package com.example.kawka.myfly;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.wangjie.androidbucket.utils.ABTextUtil;
import com.wangjie.androidbucket.utils.imageprocess.ABShape;
import com.wangjie.androidinject.annotation.annotations.base.AILayout;
import com.wangjie.androidinject.annotation.annotations.base.AIView;
import com.wangjie.androidinject.annotation.present.AIActionBarActivity;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionHelper;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RFACLabelItem;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RapidFloatingActionContentLabelList;

import org.joda.time.DateTime;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@AILayout(R.layout.activity_ma_typ)
public class MATypActivity extends AIActionBarActivity implements RapidFloatingActionContentLabelList.OnRapidFloatingActionContentLabelListListener {


    String dd, mm, yy, date;


    @AIView(R.id.activity_main_rfal)
    private RapidFloatingActionLayout rfaLayout;
    @AIView(R.id.activity_main_rfab)
    private RapidFloatingActionButton rfaBtn;
    private RapidFloatingActionHelper rfabHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_ma_typ);

        Bundle bundle = getIntent().getExtras();
        String typ = bundle.getString("a");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Status  " + typ);

        setupWindowAnimations();
        floatingButtonMenu();

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(R.color.statusbar_color));

        date();

    }


    private void date() {
        java.util.Calendar instance = java.util.Calendar.getInstance();
        dd = String.valueOf(instance.get(java.util.Calendar.DAY_OF_MONTH));
        mm = String.valueOf(instance.get(java.util.Calendar.MONTH)+1);
        yy = String.valueOf(instance.get(java.util.Calendar.YEAR));
        date = dd + "." + mm + "." + yy;
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


//    private void setupActionBar() {
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            // Show the Up button in the action bar.
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setTitle("Status - TYP");
//
//        }
//    }


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

    @Override
    public void onRFACItemLabelClick(int position, RFACLabelItem item) {
        rfabHelper.toggleContent();
    }

    @Override
    public void onRFACItemIconClick(int position, RFACLabelItem item) {
        rfabHelper.toggleContent();
    }



    public void wodowanie_onClick(View view) throws ParseException {

        DateTime r1 = new DateTime();
        DateTime e1 ;

        r1 = r1.plusDays(14);
        e1 = r1.minusYears(1);

        DecimalFormat df = new DecimalFormat("00");

        String dr1 = String.valueOf(df.format(r1.getDayOfMonth()));
        String mr1 = String.valueOf(df.format(r1.getMonthOfYear()));
        String yr1 = String.valueOf(r1.getYear());

        String de1 = String.valueOf(df.format(e1.getDayOfMonth()));
        String me1 = String.valueOf(df.format(e1.getMonthOfYear()));
        String ye1 = String.valueOf(e1.getYear());

        String rel1 = dr1 + "." + mr1 + "." + yr1 ;
        String ext1 = de1 + "." + me1 + "." + ye1 ;

        Dialog dialog;
        View v;
        v =  (LayoutInflater.from(this))
                .inflate(R.layout.dialog_status_small, null);
        final AlertDialog.Builder alertBuldier =
                new AlertDialog.Builder(this);
        alertBuldier.setView(v);
        final TextView titleText = (TextView)
                v.findViewById(R.id.titleText);
        titleText.setText(R.string.typ_wodowanie);
        final TextView textView1_1 = (TextView)
                v.findViewById(R.id.textView1_1);
        final TextView textView1_2 = (TextView)
                v.findViewById(R.id.textView1_2);
        textView1_1.setText("Data aktualnej kontroli: ");
        textView1_2.setText(ext1);
        final TextView textView2_1 = (TextView)
                v.findViewById(R.id.textView2_1);
        final TextView textView2_2 = (TextView)
                v.findViewById(R.id.textView2_2);
        textView2_1.setText("Data następnej kontroli: ");
        textView2_2.setText(rel1);

        final TextView textView3_1 = (TextView)
                v.findViewById(R.id.textView3_1);
        final TextView textView3_2 = (TextView)
                v.findViewById(R.id.textView3_2);
        textView3_1.setText("Pozostało dni: ");
        textView3_2.setText("14");

        alertBuldier.setCancelable(true)
                .setPositiveButton("OK", null);
        dialog = alertBuldier.create();
        dialog.show();
    }


    public void zaawans_onClick(View view) throws ParseException {

        DateTime r1 = new DateTime();
        DateTime e1 ;

        r1 = r1.plusDays(33);
        e1 = r1.minusYears(1);

        DecimalFormat df = new DecimalFormat("00");

        String dr1 = String.valueOf(df.format(r1.getDayOfMonth()));
        String mr1 = String.valueOf(df.format(r1.getMonthOfYear()));
        String yr1 = String.valueOf(r1.getYear());

        String de1 = String.valueOf(df.format(e1.getDayOfMonth()));
        String me1 = String.valueOf(df.format(e1.getMonthOfYear()));
        String ye1 = String.valueOf(e1.getYear());

        String rel1 = dr1 + "." + mr1 + "." + yr1 ;
        String ext1 = de1 + "." + me1 + "." + ye1 ;

        Dialog dialog;
        View v;
        v =  (LayoutInflater.from(this))
                .inflate(R.layout.dialog_status_small, null);
        final AlertDialog.Builder alertBuldier =
                new AlertDialog.Builder(this);
        alertBuldier.setView(v);
        final TextView titleText = (TextView)
                v.findViewById(R.id.titleText);
        titleText.setText("W-R Zaawansowane");
        final TextView textView1_1 = (TextView)
                v.findViewById(R.id.textView1_1);
        final TextView textView1_2 = (TextView)
                v.findViewById(R.id.textView1_2);
        textView1_1.setText("Data aktualnej kontroli: ");
        textView1_2.setText(ext1);
        final TextView textView2_1 = (TextView)
                v.findViewById(R.id.textView2_1);
        final TextView textView2_2 = (TextView)
                v.findViewById(R.id.textView2_2);
        textView2_1.setText("Data następnej kontroli: ");
        textView2_2.setText(rel1);

        final TextView textView3_1 = (TextView)
                v.findViewById(R.id.textView3_1);
        final TextView textView3_2 = (TextView)
                v.findViewById(R.id.textView3_2);
        textView3_1.setText("Pozostało dni: ");
        textView3_2.setText("33");

        alertBuldier.setCancelable(true)
                .setPositiveButton("OK", null);
        dialog = alertBuldier.create();
        dialog.show();
    }


    public void egzaminy_onClick(View view) throws ParseException {

        DateTime r1 = new DateTime();
        DateTime e1 ;

        r1 = r1.plusDays(14);
        e1 = r1.minusYears(1);

        DecimalFormat df = new DecimalFormat("00");

        String dr1 = String.valueOf(df.format(r1.getDayOfMonth()));
        String mr1 = String.valueOf(df.format(r1.getMonthOfYear()));
        String yr1 = String.valueOf(r1.getYear());

        String de1 = String.valueOf(df.format(e1.getDayOfMonth()));
        String me1 = String.valueOf(df.format(e1.getMonthOfYear()));
        String ye1 = String.valueOf(e1.getYear());

        String rel1 = dr1 + "." + mr1 + "." + yr1 ;
        String ext1 = de1 + "." + me1 + "." + ye1 ;

        Dialog dialog;
        View v;
        v = (LayoutInflater.from(this))
                .inflate(R.layout.dialog_status_small, null);
        final AlertDialog.Builder alertBuldier =
                new AlertDialog.Builder(this);
        alertBuldier.setView(v);
        final TextView titleText = (TextView)
                v.findViewById(R.id.titleText);
        titleText.setText("Egzaminy");
        final TextView textView1_1 = (TextView)
                v.findViewById(R.id.textView1_1);
        final TextView textView1_2 = (TextView)
                v.findViewById(R.id.textView1_2);
        textView1_1.setText("Data aktualnego egzaminu: ");
        textView1_2.setText(ext1);
        final TextView textView2_1 = (TextView)
                v.findViewById(R.id.textView2_1);
        final TextView textView2_2 = (TextView)
                v.findViewById(R.id.textView2_2);
        textView2_1.setText("Data następnego egzaminu: ");
        textView2_2.setText(rel1);

        final TextView textView3_1 = (TextView)
                v.findViewById(R.id.textView3_1);
        final TextView textView3_2 = (TextView)
                v.findViewById(R.id.textView3_2);
        textView3_1.setText("Pozostało dni: ");
        textView3_2.setText("-4");

        alertBuldier.setCancelable(true)
                .setPositiveButton("OK", null);
        dialog = alertBuldier.create();
        dialog.show();
    }

        public void kontrole_onClick(View view) throws ParseException {

            DateTime r1 = new DateTime();
            DateTime e1 ;

            r1 = r1.plusDays(3);
            e1 = r1.minusYears(1);

            DecimalFormat df = new DecimalFormat("00");

            String dr1 = String.valueOf(df.format(r1.getDayOfMonth()));
            String mr1 = String.valueOf(df.format(r1.getMonthOfYear()));
            String yr1 = String.valueOf(r1.getYear());

            String de1 = String.valueOf(df.format(e1.getDayOfMonth()));
            String me1 = String.valueOf(df.format(e1.getMonthOfYear()));
            String ye1 = String.valueOf(e1.getYear());

            String rel1 = dr1 + "." + mr1 + "." + yr1 ;
            String ext1 = de1 + "." + me1 + "." + ye1 ;

            Dialog dialog;
            View v;
            v =  (LayoutInflater.from(this))
                    .inflate(R.layout.dialog_status_small, null);
            final AlertDialog.Builder alertBuldier =
                    new AlertDialog.Builder(this);
            alertBuldier.setView(v);
            final TextView titleText = (TextView)
                    v.findViewById(R.id.titleText);
            titleText.setText("Kontrole");
            final TextView textView1_1 = (TextView)
                    v.findViewById(R.id.textView1_1);
            final TextView textView1_2 = (TextView)
                    v.findViewById(R.id.textView1_2);
            textView1_1.setText("Data aktualnej kontroli: ");
            textView1_2.setText(ext1);
            final TextView textView2_1 = (TextView)
                    v.findViewById(R.id.textView2_1);
            final TextView textView2_2 = (TextView)
                    v.findViewById(R.id.textView2_2);
            textView2_1.setText("Data następnej kontroli: ");
            textView2_2.setText(rel1);

            final TextView textView3_1 = (TextView)
                    v.findViewById(R.id.textView3_1);
            final TextView textView3_2 = (TextView)
                    v.findViewById(R.id.textView3_2);
            textView3_1.setText("Pozostało dni: ");
            textView3_2.setText("3");

            alertBuldier.setCancelable(true)
                    .setPositiveButton("OK", null);
            dialog = alertBuldier.create();
            dialog.show();
        }

    public void vfrD_onClick(View view) throws ParseException {

        DateTime r1 = new DateTime();
        DateTime e1 ;

        r1 = r1.plusDays(67);
        e1 = r1.minusYears(1);

        DecimalFormat df = new DecimalFormat("00");

        String dr1 = String.valueOf(df.format(r1.getDayOfMonth()));
        String mr1 = String.valueOf(df.format(r1.getMonthOfYear()));
        String yr1 = String.valueOf(r1.getYear());

        String de1 = String.valueOf(df.format(e1.getDayOfMonth()));
        String me1 = String.valueOf(df.format(e1.getMonthOfYear()));
        String ye1 = String.valueOf(e1.getYear());

        String rel1 = dr1 + "." + mr1 + "." + yr1 ;
        String ext1 = de1 + "." + me1 + "." + ye1 ;

        Dialog dialog;
        View v;
        v =  (LayoutInflater.from(this))
                .inflate(R.layout.dialog_status_small, null);
        final AlertDialog.Builder alertBuldier =
                new AlertDialog.Builder(this);
        alertBuldier.setView(v);
        final TextView titleText = (TextView)
                v.findViewById(R.id.titleText);
        titleText.setText("VFR Dzień");
        final TextView textView1_1 = (TextView)
                v.findViewById(R.id.textView1_1);
        final TextView textView1_2 = (TextView)
                v.findViewById(R.id.textView1_2);
        textView1_1.setText("Data aktualnej kontroli: ");
        textView1_2.setText(ext1);
        final TextView textView2_1 = (TextView)
                v.findViewById(R.id.textView2_1);
        final TextView textView2_2 = (TextView)
                v.findViewById(R.id.textView2_2);
        textView2_1.setText("Data następnej kontroli: ");
        textView2_2.setText(rel1);

        final TextView textView3_1 = (TextView)
                v.findViewById(R.id.textView3_1);
        final TextView textView3_2 = (TextView)
                v.findViewById(R.id.textView3_2);
        textView3_1.setText("Pozostało dni: ");
        textView3_2.setText("67");

        alertBuldier.setCancelable(true)
                .setPositiveButton("OK", null);
        dialog = alertBuldier.create();
        dialog.show();
    }

    public void vfrN_onClick(View view) throws ParseException {

        DateTime r1 = new DateTime();
        DateTime e1 ;

        r1 = r1.plusDays(41);
        e1 = r1.minusYears(1);

        DecimalFormat df = new DecimalFormat("00");

        String dr1 = String.valueOf(df.format(r1.getDayOfMonth()));
        String mr1 = String.valueOf(df.format(r1.getMonthOfYear()));
        String yr1 = String.valueOf(r1.getYear());

        String de1 = String.valueOf(df.format(e1.getDayOfMonth()));
        String me1 = String.valueOf(df.format(e1.getMonthOfYear()));
        String ye1 = String.valueOf(e1.getYear());

        String rel1 = dr1 + "." + mr1 + "." + yr1 ;
        String ext1 = de1 + "." + me1 + "." + ye1 ;

        Dialog dialog;
        View v;
        v =  (LayoutInflater.from(this))
                .inflate(R.layout.dialog_status_small, null);
        final AlertDialog.Builder alertBuldier =
                new AlertDialog.Builder(this);
        alertBuldier.setView(v);
        final TextView titleText = (TextView)
                v.findViewById(R.id.titleText);
        titleText.setText("VFR Noc");
        final TextView textView1_1 = (TextView)
                v.findViewById(R.id.textView1_1);
        final TextView textView1_2 = (TextView)
                v.findViewById(R.id.textView1_2);
        textView1_1.setText("Data aktualnej kontroli: ");
        textView1_2.setText(ext1);
        final TextView textView2_1 = (TextView)
                v.findViewById(R.id.textView2_1);
        final TextView textView2_2 = (TextView)
                v.findViewById(R.id.textView2_2);
        textView2_1.setText("Data następnej kontroli: ");
        textView2_2.setText(rel1);

        final TextView textView3_1 = (TextView)
                v.findViewById(R.id.textView3_1);
        final TextView textView3_2 = (TextView)
                v.findViewById(R.id.textView3_2);
        textView3_1.setText("Pozostało dni: ");
        textView3_2.setText("41");

        alertBuldier.setCancelable(true)
                .setPositiveButton("OK", null);
        dialog = alertBuldier.create();
        dialog.show();
    }

    public void ifrD_onClick(View view) throws ParseException {

        DateTime r1 = new DateTime();
        DateTime e1 ;

        r1 = r1.plusDays(13);
        e1 = r1.minusYears(1);

        DecimalFormat df = new DecimalFormat("00");

        String dr1 = String.valueOf(df.format(r1.getDayOfMonth()));
        String mr1 = String.valueOf(df.format(r1.getMonthOfYear()));
        String yr1 = String.valueOf(r1.getYear());

        String de1 = String.valueOf(df.format(e1.getDayOfMonth()));
        String me1 = String.valueOf(df.format(e1.getMonthOfYear()));
        String ye1 = String.valueOf(e1.getYear());

        String rel1 = dr1 + "." + mr1 + "." + yr1 ;
        String ext1 = de1 + "." + me1 + "." + ye1 ;

        Dialog dialog;
        View v;
        v =  (LayoutInflater.from(this))
                .inflate(R.layout.dialog_status_small, null);
        final AlertDialog.Builder alertBuldier =
                new AlertDialog.Builder(this);
        alertBuldier.setView(v);
        final TextView titleText = (TextView)
                v.findViewById(R.id.titleText);
        titleText.setText("IFR Dzień");
        final TextView textView1_1 = (TextView)
                v.findViewById(R.id.textView1_1);
        final TextView textView1_2 = (TextView)
                v.findViewById(R.id.textView1_2);
        textView1_1.setText("Data aktualnej kontroli: ");
        textView1_2.setText(ext1);
        final TextView textView2_1 = (TextView)
                v.findViewById(R.id.textView2_1);
        final TextView textView2_2 = (TextView)
                v.findViewById(R.id.textView2_2);
        textView2_1.setText("Data następnej kontroli: ");
        textView2_2.setText(rel1);

        final TextView textView3_1 = (TextView)
                v.findViewById(R.id.textView3_1);
        final TextView textView3_2 = (TextView)
                v.findViewById(R.id.textView3_2);
        textView3_1.setText("Pozostało dni: ");
        textView3_2.setText("13");

        alertBuldier.setCancelable(true)
                .setPositiveButton("OK", null);
        dialog = alertBuldier.create();
        dialog.show();
    }

    public void ifrN_onClick(View view) throws ParseException {

        DateTime r1 = new DateTime();
        DateTime e1 ;

        r1 = r1.plusDays(4);
        e1 = r1.minusYears(1);

        DecimalFormat df = new DecimalFormat("00");

        String dr1 = String.valueOf(df.format(r1.getDayOfMonth()));
        String mr1 = String.valueOf(df.format(r1.getMonthOfYear()));
        String yr1 = String.valueOf(r1.getYear());

        String de1 = String.valueOf(df.format(e1.getDayOfMonth()));
        String me1 = String.valueOf(df.format(e1.getMonthOfYear()));
        String ye1 = String.valueOf(e1.getYear());

        String rel1 = dr1 + "." + mr1 + "." + yr1 ;
        String ext1 = de1 + "." + me1 + "." + ye1 ;

        Dialog dialog;
        View v;
        v =  (LayoutInflater.from(this))
                .inflate(R.layout.dialog_status_small, null);
        final AlertDialog.Builder alertBuldier =
                new AlertDialog.Builder(this);
        alertBuldier.setView(v);
        final TextView titleText = (TextView)
                v.findViewById(R.id.titleText);
        titleText.setText("IFR Noc");
        final TextView textView1_1 = (TextView)
                v.findViewById(R.id.textView1_1);
        final TextView textView1_2 = (TextView)
                v.findViewById(R.id.textView1_2);
        textView1_1.setText("Data aktualnej kontroli: ");
        textView1_2.setText(ext1);
        final TextView textView2_1 = (TextView)
                v.findViewById(R.id.textView2_1);
        final TextView textView2_2 = (TextView)
                v.findViewById(R.id.textView2_2);
        textView2_1.setText("Data następnej kontroli: ");
        textView2_2.setText(rel1);

        final TextView textView3_1 = (TextView)
                v.findViewById(R.id.textView3_1);
        final TextView textView3_2 = (TextView)
                v.findViewById(R.id.textView3_2);
        textView3_1.setText("Pozostało dni: ");
        textView3_2.setText("4");

        alertBuldier.setCancelable(true)
                .setPositiveButton("OK", null);
        dialog = alertBuldier.create();
        dialog.show();
    }
    }

