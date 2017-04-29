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

        java.util.Calendar instance = java.util.Calendar.getInstance();
        dd = String.valueOf(instance.get(java.util.Calendar.DAY_OF_MONTH));
        mm = String.valueOf(instance.get(java.util.Calendar.MONTH)+1);
        yy = String.valueOf(instance.get(java.util.Calendar.YEAR));
        date = dd + "." + mm + "." + yy;


        String dt = String.valueOf(date);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy");

        Calendar d1 = Calendar.getInstance();
        d1.setTime(sdf.parse(dt));
        d1.add(Calendar.DATE, 14);
        String dat1 = sdf.format(d1.getTime());

        SimpleDateFormat sdf2 = new SimpleDateFormat("dd");
        SimpleDateFormat sdf3 = new SimpleDateFormat("mm");


        String dd1 = sdf2.format(d1.getTime());
        String mm1 = sdf3.format(d1.getTime());




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
        textView1_1.setText("Data kontroli: ");
        textView1_2.setText(dd1 + "." + mm1 + "." + String.valueOf(instance.get(java.util.Calendar.YEAR)-1));
        final TextView textView2_1 = (TextView)
                v.findViewById(R.id.textView2_1);
        final TextView textView2_2 = (TextView)
                v.findViewById(R.id.textView2_2);
        textView2_1.setText("Data następnej kontroli: ");
        textView2_2.setText(dat1);

        final TextView textView3_1 = (TextView)
                v.findViewById(R.id.textView3_1);
        final TextView textView3_2 = (TextView)
                v.findViewById(R.id.textView3_2);
        textView3_1.setText("Pozostało dni: ");
        textView3_2.setText("14");


        alertBuldier.setCancelable(true)

                .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface pDialog, int pWhich) {
                    }

                });

        dialog = alertBuldier.create();
        dialog.show();
    }
}
