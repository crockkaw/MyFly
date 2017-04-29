package com.example.kawka.myfly;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.Pair;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.kawka.myfly.models.NalotAktualny;
import com.example.kawka.myfly.network.ApiService;
import com.example.kawka.myfly.network.NalotAktualnyAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    View myView;
    int poleData;
    EditText dataOdET, dataDoET;

    NalotAktualnyAdapter nalotAktualnyAdapter;

    Context context;




    ArrayList<ArrayList<String>> resultsNalAkt;
    private static final String[] leftTitlesNalAkt = new String[10];

    //    private SimpleDateFormat mFormatter = new SimpleDateFormat("MMMM dd yyyy hh:mm aa");
    private SimpleDateFormat mFormatter = new SimpleDateFormat("dd MMMM yyyy");



//    public void setPrimaryColor(int position) {
//        MaterialPrefUtil.setPrimaryColorPosition(position);
//    }
//
//
//    /**
//     * set secondary color. Secondary color will be used for all preference widgets
//     * and preference header view.
//     *
//     * @param position :: integer value of color defined in {@link MaterialPrefUtil}
//     */
//    public void setDefaultSecondaryColor(Context context, int position) {
//        MaterialPrefUtil.setSecondaryColorPosition(context,position);
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.aktu));
        context=this;

        nalotAktualnyAdapter = new NalotAktualnyAdapter();


//        dane();


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame
                        , new MAktualnoscFragment())
                .commit();
        navigationView.setCheckedItem(R.id.nav_aktualnosc);


        Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(this.getResources().getColor(R.color.statusbar_color));
//
//
//
//
//
//        setTheme(com.codevscolor.materialpreference.R.style.AppThemeLight);
//
//
//
//        toolbar.setBackgroundColor(Color.parseColor(MaterialPrefUtil.primaryColor[MaterialPrefUtil.getPrimaryColorPosition()]));
//
//
//            Window window = getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.parseColor(MaterialPrefUtil.primaryColorDark[MaterialPrefUtil.getPrimaryColorPosition()]));
//
//


//        v = getLayoutInflater().inflate(R.layout.fragment_maktywnosc,  null);
//        onStart();
    }

//    public void dane() {
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl("http://81.18.213.35:7001/Rest/api/v0/")
//                .build();
//
//
//        ApiService apiService = retrofit.create(ApiService.class);
//        Observable<NalotAktualny> nal = apiService.getItems();
//
//        nal.subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(nalot -> {
//                    int b = nalot.getItems().size();
//                    for (int k = 0; k < b; k++) {
//                        leftTitlesNalAkt[k] = nalot.getItems().get(k).getNazwa();
//                    }
//
//                    resultsNalAkt = new ArrayList<>();
//                    for (int l = 0; l < b; l++) {
//                        ArrayList<String> strings = new ArrayList<>();
//                        strings.add(nalot.getItems().get(l).getOgolny());
//                        strings.add(nalot.getItems().get(l).getDv());
//                        strings.add(nalot.getItems().get(l).getDi());
//                        strings.add(nalot.getItems().get(l).getNv());
//                        strings.add(nalot.getItems().get(l).getNi());
//
//                        resultsNalAkt.add(strings);
//                    }
//                    System.out.println("Pobrano dane w Main ");
//                    System.out.println(resultsNalAkt.get(0).get(0));
//
//
//                },error -> System.out.println(error.toString()));
//
//    }

//    public String[] getMyLeftTitlesAktualny() {
//        return nalotAktualnyAdapter.getMyLeftTitlesAktualny();
//    }
//
//    public ArrayList<ArrayList<String>> getMyResultsAktualny() {
//        return nalotAktualnyAdapter.getMyResultsAktualny();
//    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();
        ActionBar actionBar = getSupportActionBar();
        if (id == R.id.nav_aktualnosc) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new MAktualnoscFragment())
                    .commit();
            actionBar.setTitle(getString(R.string.aktu));
        } else if (id == R.id.nav_planyLoty) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new MPlanLotyFragment())
                    .commit();
            actionBar.setTitle("Planowane Loty");
        } else if (id == R.id.nav_kalendarz) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new MKalendarzFragment())
                    .commit();
            actionBar.setTitle("Kalendarz");
        } else if (id == R.id.nav_nalot) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new MNalotFragment())
                    .commit();
            actionBar.setTitle("NalotAktualny");
        } else if (id == R.id.nav_zdarzenia) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new MZdarzeniaFragment())
                    .commit();
            actionBar.setTitle("Zdarzenia");


        } else if (id == R.id.nav_ustawienia) {

            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_logout) {
            finish();
            System.exit(0);


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void dialogCreate(View view) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Szczegółowe zestawienie...");
        alertDialogBuilder.setPositiveButton("ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }

                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

//        v =  (LayoutInflater.from(MainActivity.this))
//                .inflate(R.layout.add_dialog, null);
//
//        AlertDialog.Builder alertBuldier =
//                new AlertDialog.Builder(MainActivity.this);
//        alertBuldier.setView(v);
//
//        alertDialog.setCancelable(cancelable);
//        alertDialog.setOnCancelListener(cancelListener);
//        dialog = alertBuldier.create();
//        dialog.show();
//
//
//        AlertDialog ad = new AlertDialog.Builder(getActivity())
//                .setTitle("Title")
//                .setMessage("Sure you wanna do this!")
//                .setNegativeButton(android.R.string.no, new OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // do nothing (will close dialog)
//                    }
//                })
//                .setPositiveButton(android.R.string.yes,  new OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // do something
//                    }
//                })
//                .create();
    }

//    public void ustawDate(View view) {
//        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.zakresDat_layout);
//        linearLayout.setVisibility(View.VISIBLE);
//
//        LinearLayout wykLot = (LinearLayout) myView.findViewById(R.id.wykonaneLoty);
////                ll.setBackgroundColor(getResources().getColor(R.color.transparent));
////                ll.invalidate();
//
//        int wysokosc = 264  ;
//
//        wykLot.getLayoutParams().height = wysokosc ;
//        wykLot.requestLayout();
//    }




    public void pressStatus(View view) {
        Intent i = new Intent(this, MAStatusActivity.class);
        i.putExtra("a", "a");
        transitionTo(i);

    }

    public void pressTyp1(View view) {
        Intent i = new Intent(this, MATypActivity.class);
        i.putExtra("a", "W-3");
        transitionTo(i);
    }

    public void pressTyp2(View view) {
        Intent i = new Intent(this, MATypActivity.class);
        i.putExtra("a", "MI-17");
        transitionTo(i);
    }

    public void pressNewDoc(View view) {
        Intent i = new Intent(this, MAStatusActivity.class);
        i.putExtra("a", "zib");
        transitionTo(i);
    }

    public void pressChat(View view) {
        Intent i = new Intent(this, KomunikatorActivity.class);
        transitionTo(i);
    }

    void transitionTo(Intent i) {
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(this, true);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs);
        startActivity(i, transitionActivityOptions.toBundle());
    }


    public void nalRoczSpButton(View view) {
        LinearLayout l = (LinearLayout) findViewById(R.id.nalRoczSp);
        l.setVisibility(View.VISIBLE);
    }

    public void nalRoczSymButton(View view) {
        LinearLayout l = (LinearLayout) findViewById(R.id.nalRoczSym);
        l.setVisibility(View.VISIBLE);
    }

    public Context getContext(){
        return context;
    }


}
