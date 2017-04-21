package com.eg.yevgeniy.egontrial.Activities.Login;


import android.content.Context;
import android.text.format.DateFormat;
import android.util.AttributeSet;

import com.alamkanak.weekview.*;
import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.WeekView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by StephanieD on 4/20/17.
 */

public class weekExtender extends com.alamkanak.weekview.WeekView{



    private int counter = 0;
    private DateTimeInterpreter dateTimeInterpreter;

    public weekExtender(Context context) {
        this(context, null);
    }

    public weekExtender(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public weekExtender(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    public DateTimeInterpreter getDateTimeInterpreter() {
        if (dateTimeInterpreter == null)
            dateTimeInterpreter = new DateTimeInterpreter() {
                @Override
                public String interpretDate(Calendar date) {
                    SimpleDateFormat sdf = new SimpleDateFormat("EEEEE M/dd", Locale.getDefault());
                    return sdf.format(date.getTime()).toUpperCase();
                }
                private int counter = 0;
                @Override
                public String interpretTime(int hour) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, hour);
                    calendar.set(Calendar.MINUTE, 0);
                    System.out.println("Printing Hour " + hour);

                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                        System.out.println("Time..First " + sdf.format(calendar.getTime()));
                        counter++;
                        return sdf.format(calendar.getTime());

                    } catch (Exception e) {
                        e.printStackTrace();
                        return "";
                    }
                }
            };
        else if(counter == 0) {
            dateTimeInterpreter = new DateTimeInterpreter() {
                @Override
                public String interpretDate(Calendar date) {
                    SimpleDateFormat sdf = new SimpleDateFormat("EEEEE M/dd", Locale.getDefault());
                    return sdf.format(date.getTime()).toUpperCase();
                }
                @Override
                public String interpretTime(int hour) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, hour);
                    calendar.set(Calendar.MINUTE, 0);
                    System.out.println("Printing Hour " + hour);


                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                        System.out.println("Time..First " + sdf.format(calendar.getTime()));
                        return sdf.format(calendar.getTime());

                    } catch (Exception e) {
                        e.printStackTrace();
                        return "";
                    }
                }
            };

        }
        counter = 0;
        return dateTimeInterpreter;
    }

    @Override
    public void setDateTimeInterpreter(DateTimeInterpreter dateTimeInterpreter) {
        this.dateTimeInterpreter = dateTimeInterpreter;
    }
}
