package com.eg.yevgeniy.egontrial.Activities.E_Car;


import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.eg.yevgeniy.egontrial.Activities.Home.HomeActivity;
import com.eg.yevgeniy.egontrial.Activities.Login.weekExtender;
import com.eg.yevgeniy.egontrial.R;
import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;



import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class CarShare extends Fragment implements WeekView.EventClickListener, MonthLoader.MonthChangeListener, WeekView.EventLongPressListener, WeekView.EmptyViewLongPressListener, WeekView.EmptyViewClickListener {

    private static final int TYPE_DAY_VIEW = 1;
    private static final int TYPE_THREE_DAY_VIEW = 2;
    private static final int TYPE_WEEK_VIEW = 3;
    private int mWeekViewType = TYPE_THREE_DAY_VIEW;
    private weekExtender mWeekView;

    private List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

    private List<WeekViewEvent> eventList = new ArrayList<WeekViewEvent>();

    private HashMap<String,List<WeekViewEvent>> allEvents = new HashMap<String,List<WeekViewEvent>>() ;

    private SimpleDateFormat mFormatter = new SimpleDateFormat("MMMM dd yyyy hh:mm aa");
    private Button mButton;
    private Calendar startTime;
    private Calendar endTime;
    public int returned = 3;
    private int called = 0;


    public CarShare() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ((HomeActivity) getActivity()).setActionBarTitle("E-Car");
        View v = inflater.inflate(R.layout.fragment_car_share, container, false);

        mWeekView = (weekExtender)v.findViewById(R.id.weekExtender);
        mWeekView.setOnEventClickListener(this);

        mWeekView.setMonthChangeListener(this);
        mWeekView.setEventLongPressListener(this);
        mWeekView.setEmptyViewLongPressListener(this);
        mWeekView.setEmptyViewClickListener(this);
        setupDateTimeInterpreter(false);
        mWeekView.setNumberOfVisibleDays(1);

        return v;
    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(getContext(), event.getName() + " " + getEventTitle(event.getStartTime()), Toast.LENGTH_LONG).show();
    }


    @Override
    public void onEmptyViewLongPress(Calendar time) {



    }

    public static Calendar toCalendar(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    @Override
    public void onEmptyViewClicked(Calendar time) {

//        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
//        View customView = inflater.inflate(R.layout.popup_window,null);

        final Calendar date = time;
        final SlideDateTimeListener listener = new SlideDateTimeListener() {

            @Override
            public void onDateTimeSet(Date date)
            {
                //startTime.setTime(date);
                startTime = toCalendar(date);


                final SlideDateTimeListener listener2 = new SlideDateTimeListener() {

                    @Override
                    public void onDateTimeSet(Date date)
                    {
                        //startTime.setTime(date);
                        endTime = toCalendar(date);
                        createNewEvent();
                        mWeekView.notifyDatasetChanged();

                    }
                };
                new SlideDateTimePicker.Builder(getFragmentManager())
                        .setListener(listener2)
                        .setInitialDate(date)
                        //.setMinDate(minDate)
                        //.setMaxDate(maxDate)
                        .setIs24HourTime(true)
                        .setTheme(SlideDateTimePicker.HOLO_LIGHT)
                        .build()
                        .show();



            }
        };
        new SlideDateTimePicker.Builder(getFragmentManager())
                .setListener(listener)
                .setInitialDate(date.getTime())
                //.setMinDate(minDate)
                //.setMaxDate(maxDate)
                .setIs24HourTime(true)
                .setTheme(SlideDateTimePicker.HOLO_LIGHT)
                .build()
                .show();

    }

    public void createNewEvent(){

        WeekViewEvent event = new WeekViewEvent(1, "Y","Here", startTime, endTime);
        event.setColor(Color.parseColor("#CC03324a"));
        events.add(event);
        mWeekView.notifyDatasetChanged();
    }


    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {

        List<WeekViewEvent> matchedEvents = new ArrayList<WeekViewEvent>();
        for (WeekViewEvent event : events) {
            if (eventMatches(event, newYear, newMonth)) {
                matchedEvents.add(event);
            }
        }
        return matchedEvents;



//        newMonth--;
//
//        for (WeekViewEvent myEvent: eventList) {
//            //TODO: this is your comparison
//            if (myEvent.getStartTime().get(Calendar.MONTH) == newMonth && myEvent.getStartTime().get(Calendar.YEAR) == newYear ) {
//                Calendar startTime = myEvent.getStartTime();
//                Calendar endTime = myEvent.getEndTime();
//                WeekViewEvent event = new WeekViewEvent(1, myEvent.getName(), startTime, endTime);
//                event.setColor(Color.RED);
//                events.add(event);
//            }
//        }return events;




//        if(allEvents.containsKey(newMonth+"-"+newYear))
//            return allEvents.get(newMonth+"-"+newYear);
//
//        this.events =  new ArrayList<WeekViewEvent>();
//
//        this.events =  new ArrayList<WeekViewEvent>();
//        RepeatedEvents week = new RepeatedEvents();
//        week.setWeekView(getWeekView());
//        this.events.addAll(week.getWeekEvents(newYear, newMonth, getApplicationContext()));
//        allEvents.put(newMonth+"-"+newYear,this.events);
//
//        return this.events;

    }

    private boolean eventMatches(WeekViewEvent event, int year, int month) {
        return (event.getStartTime().get(Calendar.YEAR) == year && event.getStartTime().get(Calendar.MONTH) == month - 1) || (event.getEndTime().get(Calendar.YEAR) == year && event.getEndTime().get(Calendar.MONTH) == month - 1);
    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {



    }



//    public List<WeekViewEvent> createEvents(){
//
//        @Override
//        public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
//
//            Calendar startTime = Calendar.getInstance();
//            startTime.set(Calendar.HOUR_OF_DAY, 3);
//            startTime.set(Calendar.MINUTE, 0);
//            startTime.set(Calendar.MONTH, newMonth - 1);
//            startTime.set(Calendar.YEAR, newYear);
//            Calendar endTime = (Calendar) startTime.clone();
//            endTime.add(Calendar.HOUR, 1);
//            endTime.set(Calendar.MONTH, newMonth - 1);
//            WeekViewEvent event = new WeekViewEvent(1, getEventTitle(startTime), startTime, endTime);
//            event.setColor(getResources().getColor(R.color.colorPrimary));
//            events.add(event);
////
////        startTime = Calendar.getInstance();
////        startTime.set(Calendar.HOUR_OF_DAY, 4);
////        startTime.set(Calendar.MINUTE, 20);
////        startTime.set(Calendar.MONTH, newMonth-1);
////        startTime.set(Calendar.YEAR, newYear);
////        endTime = (Calendar) startTime.clone();
////        endTime.set(Calendar.HOUR_OF_DAY, 5);
////        endTime.set(Calendar.MINUTE, 0);
////        event = new WeekViewEvent(10, getEventTitle(startTime), startTime, endTime);
////        event.setColor(getResources().getColor(R.color.colorPrimary));
////        events.add(event);
////
////
////
////        startTime = Calendar.getInstance();
////        startTime.set(Calendar.DAY_OF_MONTH, 15);
////        startTime.set(Calendar.HOUR_OF_DAY, 3);
////        startTime.set(Calendar.MINUTE, 0);
////        startTime.set(Calendar.MONTH, newMonth-1);
////        startTime.set(Calendar.YEAR, newYear);
////        endTime = (Calendar) startTime.clone();
////        endTime.add(Calendar.HOUR_OF_DAY, 3);
////        event = new WeekViewEvent(4, getEventTitle(startTime), startTime, endTime);
////        event.setColor(getResources().getColor(R.color.colorPrimary));
////        events.add(event);
////
////        // All day event until 00:00 next day
////        startTime = Calendar.getInstance();
////        startTime.set(Calendar.DAY_OF_MONTH, 10);
////        startTime.set(Calendar.HOUR_OF_DAY, 0);
////        startTime.set(Calendar.MINUTE, 0);
////        startTime.set(Calendar.SECOND, 0);
////        startTime.set(Calendar.MILLISECOND, 0);
////        startTime.set(Calendar.MONTH, newMonth-1);
////        startTime.set(Calendar.YEAR, newYear);
////        endTime = (Calendar) startTime.clone();
////        endTime.set(Calendar.DAY_OF_MONTH, 11);
////        //event = new WeekViewEvent(8, getEventTitle(startTime), null, startTime, endTime, true);
////        //event.setColor(getResources().getColor(R.color.event_color_01));
////        events.add(event);
//            return events;
//        }
//
//    }


    private void setupDateTimeInterpreter(final boolean shortDate) {
        mWeekView.setDateTimeInterpreter(new DateTimeInterpreter() {
            @Override
            public String interpretDate(Calendar date) {
                SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());
                String weekday = weekdayNameFormat.format(date.getTime());
                SimpleDateFormat format = new SimpleDateFormat(" M/d", Locale.getDefault());

                // All android api level do not have a standard way of getting the first letter of
                // the week day name. Hence we get the first char programmatically.
                // Details: http://stackoverflow.com/questions/16959502/get-one-letter-abbreviation-of-week-day-of-a-date-in-java#answer-16959657
                if (shortDate)
                    weekday = String.valueOf(weekday.charAt(0));
                return weekday.toUpperCase() + format.format(date.getTime());
            }

            @Override
            public String interpretTime(int hour) {
                return hour > 11 ? (hour - 12) + " PM" : (hour == 0 ? "12 AM" : hour + " AM");
            }
        });
    }


    protected String getEventTitle(Calendar time) {
        return String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH)+1, time.get(Calendar.DAY_OF_MONTH));
    }

    public WeekView getWeekView() {
        return mWeekView;
    }
}
