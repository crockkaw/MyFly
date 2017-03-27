package com.example.kawka.myfly;


import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
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
    HashSet<CalendarDay> set_ifr_d, set_vfr_n, set_kontrola;

    public static final int ifr_d = 3;
    public static final int vfr_n = 20;
    public static final int ifr_n = 13;
    public static final int kontrola = 28;
    public static final int w_r_wod = 27;

    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();

    @Bind(R.id.calendarView)
    MaterialCalendarView widget;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_mkalendarz, container, false);
        ButterKnife.bind(this, myView);
        widget = (MaterialCalendarView)myView.findViewById(R.id.calendarView);

        widget.setShowOtherDates(MaterialCalendarView.SHOW_ALL);

        Calendar instance = Calendar.getInstance();
        widget.setSelectedDate(instance.getTime());

        Calendar instance1 = Calendar.getInstance();
        instance1.set(instance1.get(Calendar.YEAR), instance1.get(Calendar.MONTH), 1);

        Calendar instance2 = Calendar.getInstance();
        instance2.set(instance2.get(Calendar.YEAR) + 1 , instance2.get(Calendar.MONTH), 31);

        widget.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setMinimumDate(instance1.getTime())
                .setMaximumDate(instance2.getTime())
                .commit();


        Calendar cal1 = Calendar.getInstance();
        cal1.set(instance1.get(Calendar.YEAR), Calendar.JANUARY, 1);
        Calendar cal2 = Calendar.getInstance();
        cal2.set(instance1.get(Calendar.YEAR), Calendar.DECEMBER, 8);

        set_ifr_d = getCalendar_ifr_d();
        set_vfr_n = getCalendar_vfr_n();
        set_kontrola = getCalendar_kontrola();

        EventDecorator eventDecorator = new EventDecorator(getActivity());
        EventDecorator.Decorator_ifr_d obj_ifr_d = eventDecorator.new Decorator_ifr_d(set_ifr_d);
        EventDecorator.Decorator_vfr_n obj_vfr_n = eventDecorator.new Decorator_vfr_n(set_vfr_n);
        EventDecorator.Decorator_kontrola obj_kontrola = eventDecorator.new Decorator_kontrola(set_kontrola);
        EventDecorator.DaySelectorDecorator obj_day_decor = eventDecorator.new DaySelectorDecorator(getActivity());
        EventDecorator.CurrentDecorator obj_cur_decor = eventDecorator.new CurrentDecorator();

        widget.addDecorator(obj_ifr_d);
        widget.addDecorator(obj_vfr_n);
        widget.addDecorator(obj_kontrola);
        widget.addDecorators(obj_day_decor);
        widget.addDecorator(obj_cur_decor);


        widget.setOnDateChangedListener(this);

        return myView;
    }



    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        TextView noEventTextView = (TextView) myView.findViewById(R.id.noEventTextView);

        int l = date.getDay();
        switch(l){
            case ifr_d:
                noEventTextView.setText("Mi-8 Lot IFR w dzien");
                break;
            case vfr_n:
                noEventTextView.setText("Mi-2 Lot VFR w nocy");
               break;
            case ifr_n:
                noEventTextView.setText(R.string.no_plans_for_that_day);

                break;
            case kontrola:
                noEventTextView.setText("Mi-2 Kontrola techniki pilotowania");

                break;
            case w_r_wod:
                noEventTextView.setText(R.string.no_plans_for_that_day);

                break;
            default:
                noEventTextView.setText(R.string.no_plans_for_that_day);

        }

    }


    private HashSet<CalendarDay> getCalendar_ifr_d() {

        Calendar instance = Calendar.getInstance();

         set_ifr_d = new HashSet<>();
                CalendarDay calDay = CalendarDay.from(instance.get(Calendar.YEAR),instance.get(Calendar.MONTH),ifr_d);
                set_ifr_d.add(calDay);

        return set_ifr_d;
    }

    private HashSet<CalendarDay> getCalendar_vfr_n() {
        Calendar instance = Calendar.getInstance();

        set_vfr_n = new HashSet<>();
        CalendarDay calDay = CalendarDay.from(instance.get(Calendar.YEAR),instance.get(Calendar.MONTH),vfr_n);
        set_vfr_n.add(calDay);

        return set_vfr_n;
    }

    private HashSet<CalendarDay> getCalendar_kontrola() {
        Calendar instance = Calendar.getInstance();

        set_kontrola = new HashSet<>();
        CalendarDay calDay = CalendarDay.from(instance.get(Calendar.YEAR),instance.get(Calendar.MONTH),kontrola);
        set_kontrola.add(calDay);

        return set_kontrola;
    }


//    private class Decorator_ifr_d implements DayViewDecorator {
//        private HashSet<CalendarDay> mCalendarDayCollection;
//
//        public Decorator_ifr_d( HashSet<CalendarDay> calendarDayCollection) {
//            mCalendarDayCollection = calendarDayCollection;
//        }
//
//        @Override
//        public boolean shouldDecorate(CalendarDay day) {
//            return mCalendarDayCollection.contains(day);}
//
//        @Override
//        public void decorate(DayViewFacade view) {
//            view.addSpan(new ForegroundColorSpan(Color.BLUE));
//            view.addSpan(new StyleSpan(Typeface.BOLD));
//            view.setBackgroundDrawable(ContextCompat.getDrawable(getContext(),R.drawable.k_ifr_d));}
//    }
//
//    private class Decorator_vfr_n implements DayViewDecorator {
//        private HashSet<CalendarDay> mCalendarDayCollection;
//
//        public Decorator_vfr_n( HashSet<CalendarDay> calendarDayCollection) {
//            mCalendarDayCollection = calendarDayCollection;
//        }
//
//        @Override
//        public boolean shouldDecorate(CalendarDay day) {
//            return mCalendarDayCollection.contains(day);
//        }
//
//        @Override
//        public void decorate(DayViewFacade view) {
//            view.addSpan(new ForegroundColorSpan(Color.BLUE));
//            view.addSpan(new StyleSpan(Typeface.BOLD));
//            view.setBackgroundDrawable(ContextCompat.getDrawable(getContext(),R.drawable.k_vfr_n));
//
//        }
//    }
//
//    private class Decorator_kontrola implements DayViewDecorator {
//        private HashSet<CalendarDay> mCalendarDayCollection;
//
//        public Decorator_kontrola( HashSet<CalendarDay> calendarDayCollection) {
//            mCalendarDayCollection = calendarDayCollection;
//        }
//
//        @Override
//        public boolean shouldDecorate(CalendarDay day) {
//            return mCalendarDayCollection.contains(day);}
//
//        @Override
//        public void decorate(DayViewFacade view) {
//            view.addSpan(new ForegroundColorSpan(Color.BLUE));
//            view.addSpan(new StyleSpan(Typeface.BOLD));
//            view.setBackgroundDrawable(ContextCompat.getDrawable(getContext(),R.drawable.k_kontrola));}
//    }
//
//    public class DaySelectorDecorator implements DayViewDecorator {
//        private final Drawable drawable;
//
//        public DaySelectorDecorator(Activity context) {
//            drawable = context.getResources().getDrawable(R.drawable.day_selector);
//        }
//
//        @Override
//        public boolean shouldDecorate(CalendarDay day) {
//            return true;
//        }
//
//        @Override
//        public void decorate(DayViewFacade view) {
//            view.setSelectionDrawable(drawable);
//        }
//    }
//
//    public class CurrentDecorator implements DayViewDecorator {
//        private final Calendar calendar = Calendar.getInstance();
//        private CalendarDay date;
//
//        public CurrentDecorator() {
//            date = CalendarDay.today();
//        }
//
//        @Override
//        public boolean shouldDecorate(CalendarDay day) {
//            day.copyTo(calendar);
//            int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
//            return date != null && day.equals(date);
//        }
//
//        @Override
//        public void decorate(DayViewFacade view) {
//            view.setBackgroundDrawable(ContextCompat.getDrawable(getContext(),R.drawable.current_day));}
//        }

}
