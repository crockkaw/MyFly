package com.example.kawka.myfly;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by kawka on 2/20/2017.
 */

public class EventDecorator  {

    public Context context1;

    public EventDecorator( Context context) {
        context1 = context;
    }


    public class Decorator_ifr_d implements DayViewDecorator {
        private HashSet<CalendarDay> mCalendarDayCollection;

        public Decorator_ifr_d( HashSet<CalendarDay> calendarDayCollection) {
            mCalendarDayCollection = calendarDayCollection;
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return mCalendarDayCollection.contains(day);}

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new ForegroundColorSpan(Color.BLUE));
            view.addSpan(new StyleSpan(Typeface.BOLD));
            view.setBackgroundDrawable(ContextCompat.getDrawable(context1.getApplicationContext(),R.drawable.k_ifr_d));}
    }

    public class Decorator_vfr_n implements DayViewDecorator {
        private HashSet<CalendarDay> mCalendarDayCollection;

        public Decorator_vfr_n( HashSet<CalendarDay> calendarDayCollection) {
            mCalendarDayCollection = calendarDayCollection;
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return mCalendarDayCollection.contains(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new ForegroundColorSpan(Color.BLUE));
            view.addSpan(new StyleSpan(Typeface.BOLD));
            view.setBackgroundDrawable(ContextCompat.getDrawable(context1.getApplicationContext(),R.drawable.k_vfr_n));

        }
    }

    public class Decorator_kontrola implements DayViewDecorator {
        private HashSet<CalendarDay> mCalendarDayCollection;

        public Decorator_kontrola( HashSet<CalendarDay> calendarDayCollection) {
            mCalendarDayCollection = calendarDayCollection;
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return mCalendarDayCollection.contains(day);}

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new ForegroundColorSpan(Color.BLUE));
            view.addSpan(new StyleSpan(Typeface.BOLD));
            view.setBackgroundDrawable(ContextCompat.getDrawable(context1.getApplicationContext(),R.drawable.k_kontrola));}
    }

    public class DaySelectorDecorator implements DayViewDecorator {
        private final Drawable drawable;

        public DaySelectorDecorator(Activity context) {
            drawable = context.getResources().getDrawable(R.drawable.day_selector);
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return true;
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.setSelectionDrawable(drawable);
        }
    }

    public class CurrentDecorator implements DayViewDecorator {
        private final Calendar calendar = Calendar.getInstance();
        private CalendarDay date;

        public CurrentDecorator() {
            date = CalendarDay.today();
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            day.copyTo(calendar);
            int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
            return date != null && day.equals(date);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.setBackgroundDrawable(ContextCompat.getDrawable(context1.getApplicationContext(),R.drawable.current_day));}
    }


}