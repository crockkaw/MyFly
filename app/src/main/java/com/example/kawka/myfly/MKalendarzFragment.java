package com.example.kawka.myfly;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import butterknife.Bind;
import butterknife.ButterKnife;


public class MKalendarzFragment extends Fragment implements OnDateSelectedListener {

    View myView;
    TextView dateTextView;

    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();

    @Bind(R.id.calendarView)
    MaterialCalendarView widget;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_mkalendarz, container, false);
        ButterKnife.bind(this, myView);
        widget = (MaterialCalendarView)myView.findViewById(R.id.calendarView);

//        widget.setOnDateChangedListener(this);
        widget.setShowOtherDates(MaterialCalendarView.SHOW_ALL);


        Calendar instance = Calendar.getInstance();
        widget.setSelectedDate(instance.getTime());

        Calendar instance1 = Calendar.getInstance();
        instance1.set(instance1.get(Calendar.YEAR), Calendar.JANUARY, 1);

        Calendar instance2 = Calendar.getInstance();
        instance2.set(instance2.get(Calendar.YEAR), Calendar.DECEMBER, 31);

        widget.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setMinimumDate(instance1.getTime())
                .setMaximumDate(instance2.getTime())
                .commit();

//        widget.addDecorators(
//                new MySelectorDecorator(this),
//                new HighlightWeekendsDecorator(),
//                oneDayDecorator
//        );


        Calendar cal1 = Calendar.getInstance();
        cal1.set(instance1.get(Calendar.YEAR), Calendar.JANUARY, 1);
        Calendar cal2 = Calendar.getInstance();
        cal2.set(instance1.get(Calendar.YEAR), Calendar.DECEMBER, 8);

        HashSet<CalendarDay> setDays = getCalendarDaysSet();
        HashSet<CalendarDay> setDays2 = getCalendarDaysSet2();

        int myColor = R.color.red;
        widget.addDecorator(new BookingDecorator(myColor, setDays));
        widget.addDecorator(new BookingDecorator2(myColor, setDays2));


        widget.setOnDateChangedListener(this);

         dateTextView = (TextView) myView.findViewById(R.id.selected_date_textView);










//        new ApiSimulator().executeOnExecutor(Executors.newSingleThreadExecutor());
        return myView;
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        //If you change a decorate, you need to invalidate decorators
        dateTextView.setText(FORMATTER.format(date.getDate()));

        Log.d("MyApp","BookingDecorator decorate last");

    }



//    @Override
//    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
//        //If you change a decorate, you need to invalidate decorators
//        oneDayDecorator.setDate(date.getDate());
//        widget.invalidateDecorators();
//    }

    /**
     * Simulate an API call to show how to add decorators
     */
//    private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {
//
//        @Override
//        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            Calendar calendar = Calendar.getInstance();
//            calendar.add(Calendar.MONTH, -2);
//            ArrayList<CalendarDay> dates = new ArrayList<>();
//            for (int i = 0; i < 30; i++) {
//                CalendarDay day = CalendarDay.from(calendar);
//                dates.add(day);
//                calendar.add(Calendar.DATE, 5 );
//            }
//
//            return dates;
//        }
//
//        @Override
//        protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
//            super.onPostExecute(calendarDays);
//
////            if (isFinishing()) {
////                return;
////            }
//
//            widget.addDecorator(new BookingDecorator(Color.RED, calendarDays));
//        }
//    }

    private HashSet<CalendarDay> getCalendarDaysSet() {

        Calendar instance = Calendar.getInstance();

        HashSet<CalendarDay> setDays = new HashSet<>();
                CalendarDay calDay = CalendarDay.from(2017,Calendar.MARCH,14);
                setDays.add(calDay);
         calDay = CalendarDay.from(instance.get(Calendar.YEAR),instance.get(Calendar.MONTH),18);
        setDays.add(calDay);
         calDay = CalendarDay.from(instance.get(Calendar.YEAR),instance.get(Calendar.MONTH) + 1,2);
        setDays.add(calDay);

//        while (cal1.getTime().before(cal2.getTime())) {
//            CalendarDay calDay = CalendarDay.from(cal1);
//            setDays.add(calDay);
//            cal1.add(Calendar.DATE, 1);
//
//        }
        Log.d("MyApp","HashSet");

        return setDays;
    }

    private HashSet<CalendarDay> getCalendarDaysSet2() {
        Calendar instance = Calendar.getInstance();

        HashSet<CalendarDay> setDays2 = new HashSet<>();
        CalendarDay calDay = CalendarDay.from(2017,Calendar.MARCH,17);
        setDays2.add(calDay);
        calDay = CalendarDay.from(instance.get(Calendar.YEAR),instance.get(Calendar.MONTH),24);
        setDays2.add(calDay);
        calDay = CalendarDay.from(instance.get(Calendar.YEAR),instance.get(Calendar.MONTH) + 1,6);
        setDays2.add(calDay);

        return setDays2;
    }


    private class BookingDecorator implements DayViewDecorator {
        private int mColor;
        private HashSet<CalendarDay> mCalendarDayCollection;

        public BookingDecorator(int color, HashSet<CalendarDay> calendarDayCollection) {
            mColor = color;
            mCalendarDayCollection = calendarDayCollection;
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return mCalendarDayCollection.contains(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new ForegroundColorSpan(mColor));
            //view.addSpan(new BackgroundColorSpan(Color.BLUE));
            view.setBackgroundDrawable(ContextCompat.getDrawable(getContext(),R.drawable.calendar_box));
            Log.d("MyApp","BookingDecorator decorate last");
    }
    }

    private class BookingDecorator2 implements DayViewDecorator {
        private int mColor;
        private HashSet<CalendarDay> mCalendarDayCollection;

        public BookingDecorator2(int color, HashSet<CalendarDay> calendarDayCollection) {
            mColor = color;
            mCalendarDayCollection = calendarDayCollection;
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return mCalendarDayCollection.contains(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new ForegroundColorSpan(mColor));
            //view.addSpan(new BackgroundColorSpan(Color.BLUE));
            view.setBackgroundDrawable(ContextCompat.getDrawable(getContext(),R.drawable.calendar_box2));
            Log.d("MyApp","BookingDecorator decorate last");
        }
    }


}
