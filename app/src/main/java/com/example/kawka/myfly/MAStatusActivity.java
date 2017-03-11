package com.example.kawka.myfly;


import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ScrollView;
import android.widget.Toast;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.MenuItem;
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

import java.util.ArrayList;
import java.util.List;

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
            Toast.makeText(getContext(), "clicked label: " + position, Toast.LENGTH_SHORT).show();
            rfabHelper.toggleContent();
            }

    @Override
    public void onRFACItemIconClick(int position, RFACLabelItem item) {
            Toast.makeText(getContext(), "clicked icon: " + position, Toast.LENGTH_SHORT).show();
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


//    private void setupActionBar() {
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            // Show the Up button in the action bar.
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setTitle("Status & ZIB");
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
                .setIconNormalColor(0xff2f7650)
                .setLabelSizeSp(14)
                .setLabelBackgroundDrawable(ABShape.generateCornerShapeDrawable(0xaa000000, ABTextUtil.dip2px(context, 4)))
                .setWrapper(0)
        );
        items.add(new RFACLabelItem<Integer>()
                .setLabel(getString(R.string.legend_label_yellow))
                .setResId(R.mipmap.ico_test_d)
                .setIconNormalColor(0xffffd900)
                .setLabelColor(Color.WHITE)
                .setLabelSizeSp(14)
                .setLabelBackgroundDrawable(ABShape.generateCornerShapeDrawable(0xaa000000, ABTextUtil.dip2px(context, 4)))
                .setWrapper(1)
        );
        items.add(new RFACLabelItem<Integer>()
                .setLabel(getString(R.string.legend_label_purple))
                .setResId(R.mipmap.ico_test_d)
                .setIconNormalColor(0xffb904c6)
                .setLabelColor(Color.WHITE)
                .setLabelSizeSp(14)
                .setLabelBackgroundDrawable(ABShape.generateCornerShapeDrawable(0xaa000000, ABTextUtil.dip2px(context, 4)))
                .setWrapper(2)
        );
        items.add(new RFACLabelItem<Integer>()
                .setLabel(getString(R.string.legend_label_red))
                .setResId(R.mipmap.ico_test_d)
                .setIconNormalColor(0xffc60a04)
                .setLabelColor(Color.WHITE)
                .setLabelSizeSp(14)
                .setLabelBackgroundDrawable(ABShape.generateCornerShapeDrawable(0xaa000000, ABTextUtil.dip2px(context, 4)))
                .setWrapper(3)
        );
        items.add(new RFACLabelItem<Integer>()
                .setLabel("Dokument nie przeczytany")
                .setResId(R.mipmap.ico_test_d)
                .setIconNormalColor(0xffb0000)
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

}
