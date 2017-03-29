package com.example.kawka.myfly;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.MenuItem;

import com.afollestad.materialdialogs.MaterialDialog;
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

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

@AILayout(R.layout.activity_maktu_status)
public class MAStatusActivity extends AIActionBarActivity implements RapidFloatingActionContentLabelList.OnRapidFloatingActionContentLabelListListener {

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
                .setLabel(getString(R.string.legend_label_purple))
                .setResId(R.mipmap.ico_test_d)
                .setIconNormalColor(0xffea9054)
                .setLabelColor(Color.WHITE)
                .setLabelSizeSp(14)
                .setLabelBackgroundDrawable(ABShape.generateCornerShapeDrawable(0xaa000000, ABTextUtil.dip2px(context, 4)))
                .setWrapper(1)
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
                .setLabel(getString(R.string.legend_label_red))
                .setResId(R.mipmap.ico_test_d)
                .setIconNormalColor(0xffea1212)
                .setLabelColor(Color.WHITE)
                .setLabelSizeSp(14)
                .setLabelBackgroundDrawable(ABShape.generateCornerShapeDrawable(0xaa000000, ABTextUtil.dip2px(context, 4)))
                .setWrapper(3)
        );
        items.add(new RFACLabelItem<Integer>()
                .setLabel("Dokument nie przeczytany")
                .setResId(R.drawable.doc_warning)
                .setIconNormalColor(0xffea1212)
                .setLabelColor(Color.WHITE)
                .setLabelSizeSp(14)
                .setLabelBackgroundDrawable(ABShape.generateCornerShapeDrawable(0xaa000000, ABTextUtil.dip2px(context, 4)))
                .setWrapper(4)
        );
        items.add(new RFACLabelItem<Integer>()
                .setLabel("Nie przeczytany, termin przekroczony")
                .setResId(R.mipmap.ico_test_d)
                .setIconNormalColor(0xffb0000)
                .setIconPressedColor(0xff3e2723)
                .setLabelColor(Color.WHITE)
                .setLabelSizeSp(14)
                .setLabelBackgroundDrawable(ABShape.generateCornerShapeDrawable(0xaa000000, ABTextUtil.dip2px(context, 4)))
                .setWrapper(5)
        );
        items.add(new RFACLabelItem<Integer>()
                .setLabel("Zapoznano się z dokumentem")
                .setResId(R.mipmap.ico_test_d)
                .setIconNormalColor(0xffb0000)
                .setIconPressedColor(0xff3e2723)
                .setLabelColor(Color.WHITE)
                .setLabelSizeSp(14)
                .setLabelBackgroundDrawable(ABShape.generateCornerShapeDrawable(0xaa000000, ABTextUtil.dip2px(context, 4)))
                .setWrapper(6)
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



    public void komisja_onClick(View view) {
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
        textView1_1.setText(R.string.status_komisja_1);
        final TextView textView2_1 = (TextView)
                v.findViewById(R.id.textView2_1);
        textView2_1.setText(R.string.status_komisja_2);
        final TextView textView3_1 = (TextView)
                v.findViewById(R.id.textView3_1);
        textView3_1.setText(R.string.status_komisja_3);
        final TextView textView4_1 = (TextView)
                v.findViewById(R.id.textView4_1);
        textView4_1.setText(R.string.status_komisja_4);

        alertBuldier.setCancelable(true)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which){
                            }
                        });
        dialog = alertBuldier.create();
        dialog.show();
    }
}
