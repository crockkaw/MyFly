package com.example.kawka.myfly;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import com.loopeer.android.librarys.scrolltable.ScrollTableView;
import org.joda.time.DateTime;


public class MNalotFragment extends Fragment  implements View.OnClickListener {

    View myView;

    EditText numberOfFlights;

    public int a = 5;
    SeekBar seekBar;
    int wysokosc = 264;

    LinearLayout ll;

    Spinner spinRocznySp, spinRocznySym;
    Boolean stan =true;

    ArrayList<ArrayList<String>> resultsAktualny;

    private  String[] topTitlesLoty ;
    private  String[] topTitlesRoczny ;
    private  String[] topTitlesAktualny ;

    private String[] leftTitles = new String[]{"W-3", "MI-17", "Razem"};
    private String[] leftTitlesSym = new String[]{"s. W-3", "s. MI-17", "Razem"};
    private String[] leftTitlesAktualny = new String[]{"W-3", "MI-17", "MI-2", "Razem"};


    String[] rowLoty1 = new String[] {"W-3","pilot","nr 4","1:20","09:00","10:20"};
    String[] rowLoty2 = new String[] {"W-3","pilot","nr 6","1:00","11:00","12:00"};
    String[] rowLoty3 = new String[] {"MI-17","instruktor","nr 2","2:10","10:00","12:10"};
    String[] rowLoty4 = new String[] {"W-3","pilot","nr 7","0:30","09:00","9:30"};
    String[] rowLoty5 = new String[] {"MI-17","instruktor","nr 4","1:40","13:00","14:40"};
    String[] rowLoty6 = new String[] {"MI-17","pilot","nr 5","2:10","13:00","14:10"};
    String[] rowLoty7 = new String[] {"MI-17","instruktor","nr 6","0:50","11:00","11:50"};
    String[] rowLoty8 = new String[] {"W-3","instruktor","nr 5","1:30","10:00","11:30"};
    String[] rowLoty9 = new String[] {"W-3","pilot","nr 7","2:00","14:00","16:00"};
    String[] rowLoty10 = new String[] {"W-3","pilot","nr 4","1:20","09:00","10:20"};
    String[] rowLoty11= new String[] {"W-3","pilot","nr 6","1:00","11:00","12:00"};
    String[] rowLoty12= new String[] {"MI-17","instruktor","nr 2","2:10","10:00","12:10"};
    String[] rowLoty13= new String[] {"W-3","pilot","nr 7","0:30","09:00","9:30"};
    String[] rowLoty14= new String[] {"MI-17","instruktor","nr 6","0:50","11:00","11:50"};
    String[] rowLoty15= new String[] {"W-3","instruktor","nr 5","1:30","10:00","11:30"};
    String[] rowLoty16= new String[] {"W-3","pilot","nr 7","2:00","14:00","16:00"};
    String[] rowLoty17= new String[] {"W-3","pilot","nr 7","0:30","09:00","9:30"};
    String[] rowLoty18= new String[] {"MI-17","instruktor","nr 4","1:40","13:00","14:40"};
    String[] rowLoty19= new String[] {"W-3","pilot","nr 7","2:00","14:00","16:00"};
    String[] rowLoty20= new String[] {"W-3","pilot","nr 4","1:20","09:00","10:20"};
    String[] rowLoty21= new String[] {"W-3","pilot","nr 6","1:00","11:00","12:00"};
    String[] rowLoty22= new String[] {"MI-17","instruktor","nr 2","2:10","10:00","12:10"};
    String[] rowLoty23= new String[] {"W-3","pilot","nr 7","0:30","09:00","9:30"};
    String[] rowLoty24= new String[] {"MI-17","instruktor","nr 6","0:50","11:00","11:50"};
    String[] rowLoty25= new String[] {"W-3","instruktor","nr 5","1:30","10:00","11:30"};
    String[] rowLoty26 = new String[] {"W-3","pilot","nr 4","1:20","09:00","10:20"};
    String[] rowLoty27= new String[] {"W-3","pilot","nr 6","1:00","11:00","12:00"};
    String[] rowLoty28= new String[] {"MI-17","instruktor","nr 2","2:10","10:00","12:10"};
    String[] rowLoty29= new String[] {"W-3","pilot","nr 7","0:30","09:00","9:30"};
    String[] rowLoty30= new String[] {"MI-17","instruktor","nr 4","1:40","13:00","14:40"};
    String[] rowLoty31= new String[] {"MI-17","pilot","nr 5","2:10","13:00","14:10"};
    String[] rowLoty32= new String[] {"MI-17","instruktor","nr 6","0:50","11:00","11:50"};
    String[] rowLoty33= new String[] {"W-3","instruktor","nr 5","1:30","10:00","11:30"};
    String[] rowLoty34= new String[] {"W-3","pilot","nr 7","2:00","14:00","16:00"};
    String[] rowLoty35 = new String[] {"W-3","pilot","nr 4","1:20","09:00","10:20"};
    String[] rowLoty36= new String[] {"W-3","pilot","nr 6","1:00","11:00","12:00"};
    String[] rowLoty37= new String[] {"MI-17","instruktor","nr 2","2:10","10:00","12:10"};
    String[] rowLoty38= new String[] {"W-3","pilot","nr 7","0:30","09:00","9:30"};
    String[] rowLoty39= new String[] {"MI-17","instruktor","nr 6","0:50","11:00","11:50"};
    String[] rowLoty40= new String[] {"W-3","instruktor","nr 5","1:30","10:00","11:30"};
    String[] rowLoty41= new String[] {"W-3","pilot","nr 7","2:00","14:00","16:00"};
    String[] rowLoty42= new String[] {"W-3","pilot","nr 7","0:30","09:00","9:30"};
    String[] rowLoty43= new String[] {"MI-17","instruktor","nr 4","1:40","13:00","14:40"};
    String[] rowLoty44= new String[] {"W-3","pilot","nr 7","2:00","14:00","16:00"};
    String[] rowLoty45= new String[] {"W-3","pilot","nr 4","1:20","09:00","10:20"};
    String[] rowLoty46= new String[] {"W-3","pilot","nr 6","1:00","11:00","12:00"};
    String[] rowLoty47= new String[] {"MI-17","instruktor","nr 2","2:10","10:00","12:10"};
    String[] rowLoty48= new String[] {"W-3","pilot","nr 7","0:30","09:00","9:30"};
    String[] rowLoty49= new String[] {"MI-17","instruktor","nr 6","0:50","11:00","11:50"};
    String[] rowLoty50= new String[] {"W-3","instruktor","nr 5","1:30","10:00","11:30"};


    String[] rowSp1 = new String[] {"7:40","6:50","8:20","6:30","10:50","12:20","14:40","13:20","0:00","08:10","07:30","06:40","112:50"};
    String[] rowSp2 = new String[] {"3:20","6:30","5:20","4:30","7:10","7:50","7:10","7:30","0:00","6:30","6:00","5:20","81:50"};
    String[] rowSp3 = new String[] {"11:00","13:20","13:40","11:00","18:00","20:10","21:50","20:50","0:00","14:40","13:30","12:00","193:40"};

    String[] rowSp12 = new String[] {"3:20","6:30","5:20","4:30","7:10","7:50","7:10","7:30","0:00","6:30","6:00","5:20","81:50"};
    String[] rowSp22 = new String[] {"11:00","13:20","13:40","11:00","18:00","20:10","21:50","20:50","0:00","14:40","13:30","12:00","193:40"};
    String[] rowSp32 = new String[] {"7:40","6:50","8:20","6:30","10:50","12:20","14:40","13:20","0:00","08:10","07:30","06:40","112:50"};



    String[] rowSym1 = new String[] {"3:00","3:30","2:30","2:00","2:00","2:30","2:00","2:20","0:00","3:30","4:50","4:20","37:30"};
    String[] rowSym2 = new String[] {"3:30","2:30","2:00","3:00","2:00","2:20","2:00","2:30","0:00","3:00","3:40","2:50","31:20"};
    String[] rowSym3 = new String[] {"6:30","6:00","4:30","5:00","4:00","4:50","4:00","4:50","0:00","6:30","7:30","7:10","78:50"};

    String[] rowSym12 = new String[] {"6:30","6:00","4:30","5:00","4:00","4:50","4:00","4:50","0:00","6:30","7:30","7:10","78:50"};
    String[] rowSym22 = new String[] {"3:30","2:30","2:00","3:00","2:00","2:20","2:00","2:30","0:00","3:00","3:40","2:50","31:20"};
    String[] rowSym32 = new String[] {"3:00","3:30","2:30","2:00","2:00","2:30","2:00","2:20","0:00","3:30","4:50","4:20","37:30"};

    String[] rowAktu1 = new String[] {"621:20","236:50","189:10","93:25", "102:15"};
    String[] rowAktu2 = new String[] {"458:30","132:20","148:25","81:10","73:20"};
    String[] rowAktu3 = new String[] {"268:40","92:30","94:15","32:10","28:05"};
    String[] rowAktu4 = new String[] {"1348:30","461:40","431:50","206:45","203:40"};

//    private String[] leftTitlesAktualny = new String[10];

    private ScrollTableView stv_loty, stv_loty2, stv_aktualny, stv_roczny, stv_roczny_sym;
    Button dataButton1;
    int prog = 15;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_mnalot, container, false);

        MainActivity activity = (MainActivity) getActivity();
//        leftTitlesAktualny = activity.nalotAktualnyAdapter.getMyLeftTitlesAktualny();
//        resultsAktualny = activity.nalotAktualnyAdapter.getMyResultsAktualny();

        topTitlesLoty = new String[]{getString(R.string.nal_loty1), getString(R.string.nal_loty2), getString(R.string.nal_loty3), getString(R.string.nal_loty4), getString(R.string.nal_loty5), getString(R.string.nal_loty6)};
        topTitlesRoczny = new String[]{getString(R.string.nal_rocz1), getString(R.string.nal_rocz2), getString(R.string.nal_rocz3), getString(R.string.nal_rocz4), getString(R.string.nal_rocz5), getString(R.string.nal_rocz6), getString(R.string.nal_rocz7), getString(R.string.nal_rocz8), getString(R.string.nal_rocz9), getString(R.string.nal_rocz10), getString(R.string.nal_rocz11), getString(R.string.nal_rocz12), "Razem"};
        topTitlesAktualny = new String[]{getString(R.string.nal_cal1), getString(R.string.nal_cal2), getString(R.string.nal_cal3), getString(R.string.nal_cal4), getString(R.string.nal_cal5)};

        try {
            initialization();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        toggleButton1();

        spinRocznySp = (Spinner) myView.findViewById(R.id.spinnerRokSp);
        spinRocznySym = (Spinner) myView.findViewById(R.id.spinnerRokSym);
        addItemsOnSpinner();
        spinerEdit();



        return myView;
    }



    public void initialization() throws ParseException {

        numberOfFlights = (EditText) myView.findViewById(R.id.numberOfFlights);


        stv_loty = (ScrollTableView) myView.findViewById(R.id.scroll_t_loty);
        stv_aktualny = (ScrollTableView) myView.findViewById(R.id.scroll_t_aktualny);
        stv_roczny = (ScrollTableView) myView.findViewById(R.id.scroll_t_roczny);
        stv_roczny_sym = (ScrollTableView) myView.findViewById(R.id.scroll_t_roczny_sym);

        ArrayList<String> topTitlesLoty = createTopTitlesLoty();
        ArrayList<String> leftTitle = createLeftTitle();
        ArrayList<String> topTitlesRoczny = createTopTitlesRoczny();
        ArrayList<String> leftTitleLoty = createLeftTitleLoty();


        stv_loty.setDatas(createTopTitlesLoty(), createLeftTitleLoty(), createContentLoty());
        stv_roczny.setDatas(createTopTitlesRoczny(), createLeftTitle(), createContent1(rowSp1,rowSp2,rowSp3,null));
        stv_roczny_sym.setDatas(createTopTitlesRoczny(), createLeftTitleSym(), createContent1(rowSym1,rowSym2,rowSym3, null));
        stv_aktualny.setDatas(createTopTitlesAktualny(), createLeftTitleAktualny(), createContent1(rowAktu1,rowAktu2,rowAktu3,rowAktu4));

//        stv_loty.setDatas(createTopTitlesLoty(), createLeftTitleLoty(), createContent(leftTitleLoty.size(), topTitlesLoty.size()));
//        stv_roczny.setDatas(createTopTitlesRoczny(), createLeftTitle(), createContent(leftTitle.size(), topTitlesRoczny.size()));
//        stv_roczny_sym.setDatas(createTopTitlesRoczny(), createLeftTitle(), createContent(leftTitle.size(), topTitlesRoczny.size()));
//        stv_aktualny.setDatas(createTopTitlesAktualny(), createLeftTitleAktualny(), resultsAktualny);


        seekBar = (SeekBar) myView.findViewById(R.id.seekBar1);
        seekBar.setProgress(5);
        seekBar.incrementProgressBy(2);
        seekBar.setMax(50);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                numberOfFlights.setText(String.valueOf(progress));


            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Button confirmFlightsButton = (Button) myView.findViewById(R.id.confirmFlightsButton);


        confirmFlightsButton.setOnClickListener(new View.OnClickListener() {



            public void onClick(View v)
            {
                int num = Integer.parseInt(numberOfFlights.getText().toString());

                a=num;

                if (num>50 || num<5){
                    Toast.makeText(getContext(), "Wartość minimalna 5, maksymalna 50" , Toast.LENGTH_SHORT).show();

                }else {


                final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Pobieranie...");
                progressDialog.show();

                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {

                                ArrayList<String> topTitlesLoty = createTopTitlesLoty();
                                ArrayList<String> leftTitleLoty = null;




                                try {
                                    leftTitleLoty = createLeftTitleLoty();

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    stv_loty2 = (ScrollTableView) myView.findViewById(R.id.scroll_t_loty2);

                                    stv_loty2.setDatas(createTopTitlesLoty(), createLeftTitleLoty(), createContentLoty());
                                    stv_loty.setDatas(createTopTitlesLoty(), createLeftTitleLoty(), createContentLoty());



                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                progressDialog.dismiss();




                                if (stan) {
                                    int num = Integer.parseInt(numberOfFlights.getText().toString());

                                    if(num!=5){
                                    stv_loty.setVisibility(View.GONE);
                                    stv_loty2.setVisibility(View.VISIBLE);
                                    stan = false;}
                                } else {
                                    stv_loty2.setVisibility(View.GONE);
                                    stv_loty.setVisibility(View.VISIBLE);
                                    stan=true;
                                }


//                                CardView cardView = (CardView) myView.findViewById(R.id.cardLoty);
//                                cardView.setVisibility(View.GONE);
//                                cardView.setVisibility(View.VISIBLE);
//

//                                LinearLayout l1 = (LinearLayout) myView.findViewById(R.id.layout_loty);
//                                l1.requestLayout();
//                                LinearLayout l2 = (LinearLayout) myView.findViewById(R.id.wykonaneLoty);
//                                LinearLayout l3 = (LinearLayout) myView.findViewById(R.id.loty);
//                                l2.requestLayout();
//                                l3.requestLayout();



//
//
//                                wysokosc = wysokosc + (a * 96);
//                                LinearLayout l1 = (LinearLayout) myView.findViewById(R.id.layout_loty);
//
//                                l1.getLayoutParams().height = wysokosc;
//                                l1.requestLayout();

//

                            }
                        }, 1000);}

            }
        });}






//                a = progress + 15;
//
//                prog = progress;
//
//                ArrayList<String> topTitlesLoty = createTopTitlesLoty();
//                ArrayList<String> leftTitleLoty = createLeftTitleLoty();
//
//
//                stv_loty.setDatas(createTopTitlesLoty(), createLeftTitleLoty(), createContent(leftTitleLoty.size(), topTitlesLoty.size()));
//                 ll = (LinearLayout) myView.findViewById(R.id.layout_loty);
////                ll.setBackgroundColor(getResources().getColor(R.color.transparent));
////                ll.invalidate();
//
//                wysokosc = wysokosc + (progress * 96);
//
//                ll.getLayoutParams().height = wysokosc;
//                ll.requestLayout();



//







    @Override
    public void onClick(View v) {


    }



    private ArrayList<ArrayList<String>> createContent(int row, int column) {
        ArrayList<ArrayList<String>> results = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            ArrayList<String> strings = new ArrayList<>();
            for (int j = 0; j < column; j++) {
                strings.add( i + "" + j);
            }
            results.add(strings);
        }
        return results;
    }


    private ArrayList<String> createTopTitlesLoty() {
        ArrayList<String> results = new ArrayList<>();
        for (String string : topTitlesLoty) {
            results.add(string);
        }
        return results;
    }

    private ArrayList<String> createTopTitlesAktualny() {
        ArrayList<String> results = new ArrayList<>();
        for (String string : topTitlesAktualny) {
            results.add(string);
        }
        return results;
    }


    private ArrayList<String> createLeftTitle() {
        ArrayList<String> results = new ArrayList<>();
        for (String string : leftTitles) {
            if (string != null && !string.isEmpty())
            {
                results.add(string);}
        }
        return results;
    }

    private ArrayList<String> createLeftTitleSym() {
        ArrayList<String> results = new ArrayList<>();
        for (String string : leftTitlesSym) {
            if (string != null && !string.isEmpty())
            {
                results.add(string);}
        }
        return results;
    }

    private ArrayList<String> createLeftTitleAktualny() {
        ArrayList<String> results = new ArrayList<>();
        for (String string : leftTitlesAktualny) {
            if (string != null && !string.isEmpty())
            {
                results.add(string);}
        }
        return results;
    }

        private ArrayList<String> createTopTitlesRoczny() {
            ArrayList<String> results = new ArrayList<>();
            for (String string : topTitlesRoczny) {
                results.add(string);
            }
            return results;
        }


    private ArrayList<String> createLeftTitleLoty() throws ParseException {

        String mm2 = null;



//        java.util.Calendar instance = java.util.Calendar.getInstance();
//        String dd = String.valueOf(instance.get(java.util.Calendar.DAY_OF_MONTH));
//        String mm = String.valueOf(instance.get(java.util.Calendar.MONTH)+1);
//        String yy = String.valueOf(instance.get(java.util.Calendar.YEAR));
////        int i_dd =instance.get(java.util.Calendar.DAY_OF_MONTH);
//        String date = dd + "." + mm + "." + yy;
//
////        String dd2 = String.valueOf(instance.get(java.util.Calendar.DAY_OF_MONTH)+1);
////        String date2 = dd2 + "." + mm + "." + yy;
//
//
//        String dt = String.valueOf(date);
//        SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy");

        Log.d("myapp", " left title a="+String.valueOf(a));


        ArrayList<String> results = new ArrayList<>();
        for (int i = 1; i <= a; i++) {
////            Calendar l1 = Calendar.getInstance();
////            l1.setTime(sdf.parse(dt));
//
//            Calendar e2 = Calendar.getInstance();
//            e2.setTime(sdf.parse(dt));
//            e2.add(Calendar.DAY_OF_YEAR, -i );


            DateTime dateTime = new DateTime();
            dateTime = dateTime.minusDays(i*2);



//            l1.add(Calendar.DAY_OF_MONTH, -i * 2);
//            String rel1 = sdf.format(l1.getTime());

//            SimpleDateFormat sdf1 = new SimpleDateFormat("dd/mm/yyyy");
//
//            String dat = String.valueOf(sdf1.format(e2.getTime()));
//
//            Log.d("myapp", dat);
//
//            SimpleDateFormat sdf2 = new SimpleDateFormat("dd");
//            SimpleDateFormat sdf3 = new SimpleDateFormat("mm");
//            SimpleDateFormat sdf4 = new SimpleDateFormat("yy");
//
//            String dd1 = String.valueOf(sdf2.format(e2.getTime()));
//            String mm1 = String.valueOf(sdf3.format(e2.getTime()));
//            String yy1 = String.valueOf(sdf4.format(e2.getTime()));

            String dd1 = String.valueOf(dateTime.getDayOfMonth());
            String mm1 = String.valueOf(dateTime.getMonthOfYear());
            String yy1 = String.valueOf(dateTime.getYear());

//            if (mm1.equals("01")|| mm1.equals("1")){
//                mm1 = "I";
//            }
//            if (mm1.equals("02")|| mm1.equals("2")){
//                mm1 = "II";
//            }
//            if (mm1.equals("03")|| mm1.equals("3")) {
//                mm1 = "III";
//            }
//            if (mm1.equals("04")|| mm1.equals("4")){
//                mm1 = "IV";
//            }
//            if (mm1.equals("05")|| mm1.equals("5")){
//                mm1 = "V";
//            }
//            if (mm1.equals("06") || mm1.equals("6")){
//                mm1 = "VI";
//            }
//            if (mm1.equals("07")|| mm1.equals("7")){
//                mm1 = "VII";
//            }
//            if (mm1.equals("08")|| mm1.equals("8")){
//                mm1 = "VIII";
//            }
//            if (mm1.equals("09")|| mm1.equals("9")){
//                mm1 = "IX";
//            }
//            if (mm1.equals("10")|| mm1.equals("10")){
//                mm1 = "X";
//            }
//            if (mm1.equals("11")|| mm1.equals("11")){
//                mm1 = "XI";
//            }
//            if (mm1.equals("12")|| mm1.equals("12")){
//                mm1 = "XII";
//            }



            results.add(dd1 +"."+ mm1 +"."+ "17");

            Log.d("myapp", results.get(i-1));

        }
        return results;
    }




    private ArrayList<ArrayList<String>> createContent1(String[] row1, String[] row2, String[] row3, String[] row4 ) {


        ArrayList<ArrayList<String>> results = new ArrayList<>();
        ArrayList<String> strings1 = new ArrayList<>();
        for (String string : row1) {
            strings1.add(string);
        }
        results.add(strings1);

        ArrayList<String> strings2 = new ArrayList<>();
        for (String string : row2) {
            strings2.add(string);
        }
        results.add(strings2);

        ArrayList<String> strings3 = new ArrayList<>();
        for (String string : row3) {
            strings3.add(string);
        }
        results.add(strings3);

        if (row4 != null ) {
            ArrayList<String> strings4 = new ArrayList<>();
            for (String string : row4) {
                strings4.add(string);
            }
            results.add(strings4);
        }

        return results;
    }


        private ArrayList<ArrayList<String>> createContentLoty() {


        ArrayList<ArrayList<String>> results = new ArrayList<>();

        ArrayList<String> strings1 = new ArrayList<>();
        for (String string : rowLoty1) {
            strings1.add(string);
        }
        results.add(strings1);

        ArrayList<String> strings2 = new ArrayList<>();
        for (String string : rowLoty2) {
            strings2.add(string);
        }
        results.add(strings2);

        ArrayList<String> strings3 = new ArrayList<>();
        for (String string : rowLoty3) {
            strings3.add(string);
        }
        results.add(strings3);

        ArrayList<String> strings4 = new ArrayList<>();
        for (String string : rowLoty4) {
            strings4.add(string);
        }
        results.add(strings4);

        ArrayList<String> strings5 = new ArrayList<>();
        for (String string : rowLoty5) {
            strings5.add(string);
        }
        results.add(strings5);

        ArrayList<String> strings6 = new ArrayList<>();
        for (String string : rowLoty6) {
            strings6.add(string);
        }
        results.add(strings6);

        ArrayList<String> strings7 = new ArrayList<>();
        for (String string : rowLoty7) {
            strings7.add(string);
        }
        results.add(strings7);

        ArrayList<String> strings8 = new ArrayList<>();
        for (String string : rowLoty8) {
            strings8.add(string);
        }
        results.add(strings8);

        ArrayList<String> strings9 = new ArrayList<>();
        for (String string : rowLoty9) {
            strings9.add(string);
        }
        results.add(strings9);

        ArrayList<String> strings10 = new ArrayList<>();
        for (String string : rowLoty10) {
            strings10.add(string);
        }
        results.add(strings10);

        ArrayList<String> strings11 = new ArrayList<>();
        for (String string : rowLoty11) {
            strings11.add(string);
        }
        results.add(strings11);

        ArrayList<String> strings12 = new ArrayList<>();
        for (String string : rowLoty12) {
            strings12.add(string);
        }
        results.add(strings12);

        ArrayList<String> strings13 = new ArrayList<>();
        for (String string : rowLoty13) {
            strings13.add(string);
        }
        results.add(strings8);

        ArrayList<String> strings14 = new ArrayList<>();
        for (String string : rowLoty14) {
            strings14.add(string);
        }
        results.add(strings14);

        ArrayList<String> strings15 = new ArrayList<>();
        for (String string : rowLoty15) {
            strings15.add(string);
        }
        results.add(strings15);

        ArrayList<String> strings16 = new ArrayList<>();
        for (String string : rowLoty16) {
            strings16.add(string);
        }
        results.add(strings16);

        ArrayList<String> strings17 = new ArrayList<>();
        for (String string : rowLoty17) {
            strings17.add(string);
        }
        results.add(strings17);

        ArrayList<String> strings18 = new ArrayList<>();
        for (String string : rowLoty18) {
            strings18.add(string);
        }
        results.add(strings18);

        ArrayList<String> strings19 = new ArrayList<>();
        for (String string : rowLoty19) {
            strings19.add(string);
        }
        results.add(strings19);

        ArrayList<String> strings20 = new ArrayList<>();
        for (String string : rowLoty20) {
            strings20.add(string);
        }
        results.add(strings20);

        ArrayList<String> strings21 = new ArrayList<>();
        for (String string : rowLoty21) {
            strings21.add(string);
        }
        results.add(strings21);

        ArrayList<String> strings22 = new ArrayList<>();
        for (String string : rowLoty22) {
            strings22.add(string);
        }
        results.add(strings2);

        ArrayList<String> strings23 = new ArrayList<>();
        for (String string : rowLoty23) {
            strings23.add(string);
        }
        results.add(strings23);

        ArrayList<String> strings24 = new ArrayList<>();
        for (String string : rowLoty24) {
            strings24.add(string);
        }
        results.add(strings24);

        ArrayList<String> strings25 = new ArrayList<>();
        for (String string : rowLoty25) {
            strings25.add(string);
        }
        results.add(strings25);

        ArrayList<String> strings26 = new ArrayList<>();
        for (String string : rowLoty26) {
            strings26.add(string);
        }
        results.add(strings26);

        ArrayList<String> strings27 = new ArrayList<>();
        for (String string : rowLoty27) {
            strings27.add(string);
        }
        results.add(strings27);

        ArrayList<String> strings28 = new ArrayList<>();
        for (String string : rowLoty28) {
            strings28.add(string);
        }
        results.add(strings28);

        ArrayList<String> strings29 = new ArrayList<>();
        for (String string : rowLoty29) {
            strings29.add(string);
        }
        results.add(strings29);

        ArrayList<String> strings30 = new ArrayList<>();
        for (String string : rowLoty30) {
            strings30.add(string);
        }
        results.add(strings30);

        ArrayList<String> strings31 = new ArrayList<>();
        for (String string : rowLoty31) {
            strings31.add(string);
        }
        results.add(strings31);

        ArrayList<String> strings32 = new ArrayList<>();
        for (String string : rowLoty32) {
            strings32.add(string);
        }
        results.add(strings32);

        ArrayList<String> strings33 = new ArrayList<>();
        for (String string : rowLoty33) {
            strings33.add(string);
        }
        results.add(strings33);

        ArrayList<String> strings34 = new ArrayList<>();
        for (String string : rowLoty34) {
            strings34.add(string);
        }
        results.add(strings34);

        ArrayList<String> strings35 = new ArrayList<>();
        for (String string : rowLoty35) {
            strings35.add(string);
        }
        results.add(strings35);

        ArrayList<String> strings36 = new ArrayList<>();
        for (String string : rowLoty36) {
            strings36.add(string);
        }
        results.add(strings36);

        ArrayList<String> strings37 = new ArrayList<>();
        for (String string : rowLoty37) {
            strings37.add(string);
        }
        results.add(strings37);

        ArrayList<String> strings38 = new ArrayList<>();
        for (String string : rowLoty38) {
            strings38.add(string);
        }
        results.add(strings38);

        ArrayList<String> strings39 = new ArrayList<>();
        for (String string : rowLoty39) {
            strings39.add(string);
        }
        results.add(strings39);

        ArrayList<String> strings40 = new ArrayList<>();
        for (String string : rowLoty40) {
            strings40.add(string);
        }
        results.add(strings40);

        ArrayList<String> strings41 = new ArrayList<>();
        for (String string : rowLoty41) {
            strings41.add(string);
        }
        results.add(strings41);

        ArrayList<String> strings42 = new ArrayList<>();
        for (String string : rowLoty42) {
            strings42.add(string);
        }
        results.add(strings42);

        ArrayList<String> strings43 = new ArrayList<>();
        for (String string : rowLoty43) {
            strings43.add(string);
        }
        results.add(strings43);

        ArrayList<String> strings44 = new ArrayList<>();
        for (String string : rowLoty44) {
            strings44.add(string);
        }
        results.add(strings44);

        ArrayList<String> strings45 = new ArrayList<>();
        for (String string : rowLoty45) {
            strings45.add(string);
        }
        results.add(strings45);

        ArrayList<String> strings46 = new ArrayList<>();
        for (String string : rowLoty46) {
            strings46.add(string);
        }
        results.add(strings46);

        ArrayList<String> strings47 = new ArrayList<>();
        for (String string : rowLoty47) {
            strings47.add(string);
        }
        results.add(strings47);

        ArrayList<String> strings48 = new ArrayList<>();
        for (String string : rowLoty48) {
            strings48.add(string);
        }
        results.add(strings48);
        ArrayList<String> strings49 = new ArrayList<>();
        for (String string : rowLoty49) {
            strings49.add(string);
        }
        results.add(strings49);

        ArrayList<String> strings50 = new ArrayList<>();
        for (String string : rowLoty50) {
            strings50.add(string);
        }
        results.add(strings50);

        Log.d("myapp", strings20.get(0));

        return results;
    }

//    private ArrayList<ArrayList<String>> createContent1(String[] row1, String[] row2, String[] row3 ) {
//
//
//        ArrayList<ArrayList<String>> results = new ArrayList<>();
//        ArrayList<String> strings1 = new ArrayList<>();
//        for (String string : row1) {
//            strings1.add(string);
//        }
//        results.add(strings1);
//
//        ArrayList<String> strings2 = new ArrayList<>();
//        for (String string : row2) {
//            strings2.add(string);
//        }
//        results.add(strings2);
//
//        ArrayList<String> strings3 = new ArrayList<>();
//        for (String string : row3) {
//            strings3.add(string);
//        }
//        results.add(strings3);
//
//
//        return results;
//    }


    public void addItemsOnSpinner() {
        ArrayList<String> years= new ArrayList<>(Arrays.asList("2017","2016","2015","2014","2013","2012"));



        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(), R.layout.spinner_item, years);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinRocznySp.setAdapter(spinnerArrayAdapter);

        ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(getActivity().getBaseContext(), R.layout.spinner_item, years);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinRocznySym.setAdapter(spinnerArrayAdapter);

    }

//    private static View.OnKeyListener Spinner_OnKey = new View.OnKeyListener() {
//
//        public boolean onKey(View v, int keyCode, KeyEvent event) {
//            if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
//                stv_roczny.setDatas(createTopTitlesRoczny(), createLeftTitle(), createContent1(rowSp12,rowSp22,rowSp32,null));
//
//                return true;
//            } else {
//                return false;
//            }
//        }
//    };


//    public void nalRoczSpButton(View v ){
//
//
//
//
//        spinRocznySp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//            @Override
//            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//
//                String items = spinRocznySp.getSelectedItem().toString();
//                Toast.makeText(getContext(), "wykonuje sie", Toast.LENGTH_LONG).show();
//
//                stv_roczny.setDatas(createTopTitlesRoczny(), createLeftTitle(), createContent1(rowSp12,rowSp22,rowSp32,null));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> arg0) {
//
//            }
//        });
//
//    }

    public void spinerEdit() {
        final ToggleButton button = (ToggleButton) myView.findViewById(R.id.nalRokSp);
        final ToggleButton button2  = (ToggleButton) myView.findViewById(R.id.nalRokSym);

        button.setOnClickListener(new View.OnClickListener()
        {

            LinearLayout l = (LinearLayout) myView.findViewById(R.id.nalRoczSp);

            @Override
            public void onClick(View v)
            {
                if(button.isChecked()){
                    l.setVisibility(View.VISIBLE);
                }
                else{
                    l.setVisibility(View.GONE);
                }
            }
        });
                spinRocznySp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                        String items = spinRocznySp.getSelectedItem().toString();
                        if (!items.equals("2017")) {
                            final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                                    R.style.AppTheme_Dark_Dialog);
                            progressDialog.setIndeterminate(true);
                            progressDialog.setMessage("Pobieranie...");
                            progressDialog.show();

                            new android.os.Handler().postDelayed(
                                    new Runnable() {
                                        public void run() {
                                            progressDialog.dismiss();
                                            stv_roczny.setDatas(createTopTitlesRoczny(), createLeftTitle(), createContent1(rowSp12, rowSp22, rowSp32, null));
                                        }
                                    }, 800);
                        }else {
                            stv_roczny.setDatas(createTopTitlesRoczny(), createLeftTitle(), createContent1(rowSp1, rowSp2, rowSp3, null));
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                    }
                });


        button2.setOnClickListener(new View.OnClickListener()
        {

            LinearLayout l = (LinearLayout) myView.findViewById(R.id.nalRoczSym);

            @Override
            public void onClick(View v)
            {
                if(button2.isChecked()){
                    l.setVisibility(View.VISIBLE);
                }
                else{
                    l.setVisibility(View.GONE);
                }
            }
        });

                spinRocznySym.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                        String items = spinRocznySym.getSelectedItem().toString();
                        if (!items.equals("2017")) {
                            final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                                    R.style.AppTheme_Dark_Dialog);
                            progressDialog.setIndeterminate(true);
                            progressDialog.setMessage("Pobieranie...");
                            progressDialog.show();

                            new android.os.Handler().postDelayed(
                                    new Runnable() {
                                        public void run() {
                                            progressDialog.dismiss();
                                            stv_roczny_sym.setDatas(createTopTitlesRoczny(), createLeftTitle(), createContent1(rowSym12, rowSym22, rowSym32, null));
                                        }
                                    }, 800);
//
                        }else {
                            stv_roczny_sym.setDatas(createTopTitlesRoczny(), createLeftTitle(), createContent1(rowSym1, rowSym2, rowSym3, null));
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                    }
                });}




    public void toggleButton1(){
        final ToggleButton b = (ToggleButton) myView.findViewById(R.id.dataButton1);
        final LinearLayout linearLayout = (LinearLayout) myView.findViewById(R.id.zakresDat_layout);

        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(b.isChecked()){
                    linearLayout.setVisibility(View.VISIBLE);
                }
                else{
                    linearLayout.setVisibility(View.GONE);
            }
            }
        });



    }


}





