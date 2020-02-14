package com.example.labs_81;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;
import static com.example.labs_81.MainActivity.LOG_TAG;

public class RVAdapterLesson extends RecyclerView.Adapter<RVAdapterLesson.CardViewHolder> {


    Day lesson;
    DataBase data;
    String choice;
    Context ctn;
    int choiceInt;
    SharedPreferences sPref;

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        String choice;

        CardViewHolder(CardView cv) {
            super(cv);
            cardView = cv;
        }
    }

    public void removeChoice(int newChoiceInt, String newChoice) {
        choice = newChoice;
        int choice = choiceInt;
        this.notifyItemRangeChanged(newChoiceInt, 1);
        this.notifyItemRangeChanged(choice, 1);

        sPref = ctn.getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("numberGroup", newChoice);
        ed.commit();
    }

    RVAdapterLesson(Day lesson, DataBase data, Context ctn) {
        this.lesson = lesson;
        this.data = data;
        this.ctn = ctn;
    }


    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())

                .inflate(R.layout.card_lesson, parent, false);

        return new CardViewHolder(cv);

    }


    @Override

    public void onBindViewHolder(CardViewHolder cardViewHolder, final int position) {

        CardView cardView = cardViewHolder.cardView;

        RelativeLayout main = (RelativeLayout) cardView.findViewById(R.id.main);

        RelativeLayout time = (RelativeLayout) main.findViewById(R.id.time);
        TextView start = (TextView) time.findViewById(R.id.start);
        start.setText(data.getCall(lesson.getLesson(position).getTime()-1).getStart().toString());
        TextView end = (TextView) time.findViewById(R.id.end);
        end.setText(data.getCall(lesson.getLesson(position).getTime()-1).getEnd().toString());
        TextView aud = (TextView) time.findViewById(R.id.aud);

        aud.setText(lesson.getLesson(position).getAud());

        Date datestart = new Date();
        Date dateend = new Date();
        Date currentDate = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        try {
            datestart = formatter.parse(data.getCall(lesson.getLesson(position).getTime()-1).getStart().toString());
            dateend = formatter.parse(data.getCall(lesson.getLesson(position).getTime()-1).getEnd().toString());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        currentDate.setHours(data.getCall(lesson.getLesson(position).getTime()-1).getStart().H);
        currentDate.setMinutes(data.getCall(lesson.getLesson(position).getTime()-1).getStart().M);
        long star = currentDate.getTime();
        currentDate.setHours(data.getCall(lesson.getLesson(position).getTime()-1).getEnd().H);
        currentDate.setMinutes(data.getCall(lesson.getLesson(position).getTime()-1).getEnd().M);
        long en = currentDate.getTime();
        long current = System.currentTimeMillis();
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK)-1;
        if (((star < current) && (en > current))&&dayOfWeek==lesson.getDay()) {

            time.setBackgroundColor(ctn.getResources().getColor(R.color.colorMainText));
        }
        DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        RelativeLayout les = (RelativeLayout) main.findViewById(R.id.lesson);
        TextView type = (TextView) les.findViewById(R.id.type);
        type.setText(data.getType(lesson.getLesson(position).getType()-1));
        TextView title = (TextView) les.findViewById(R.id.title);
        title.setText(lesson.getLesson(position).getName());
        TextView name = (TextView) les.findViewById(R.id.name);
        name.setText(lesson.getLesson(position).getTeach());
    }


    @Override

    public int getItemCount() {
        return lesson.getSize();
    }


}
