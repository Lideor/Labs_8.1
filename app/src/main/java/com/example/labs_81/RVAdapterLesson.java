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


    private Day lesson;// список занятий
    private DataBase data;//база данных
    private Context ctn;//контекст обьекта который вызвал адаптер

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;

        CardViewHolder(CardView cv) {
            super(cv);
            cardView = cv;
        }
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

        RelativeLayout main = (RelativeLayout) cardView.findViewById(R.id.main);// контейнер всех элементов

        RelativeLayout time = (RelativeLayout) main.findViewById(R.id.time);// контейнер времени
        TextView start = (TextView) time.findViewById(R.id.start);
        start.setText(data.getCall(lesson.getLesson(position).getTime()-1).getStart().toString());//устанавливаем время начала занятия
        TextView end = (TextView) time.findViewById(R.id.end);
        end.setText(data.getCall(lesson.getLesson(position).getTime()-1).getEnd().toString());//устанавливаем время конца занятия
        TextView aud = (TextView) time.findViewById(R.id.aud);
        aud.setText(lesson.getLesson(position).getAud());//устанавливаем время номер аудитории

        Date currentDate = new Date();// создаем обьект времени

        //устанвливаем время начала занаятие в созданный обьект
        currentDate.setHours(data.getCall(lesson.getLesson(position).getTime()-1).getStart().H);
        currentDate.setMinutes(data.getCall(lesson.getLesson(position).getTime()-1).getStart().M);
        long star = currentDate.getTime();// переводи полученный обьект в милллисекунды

        //устанвливаем время конца занаятие в созданный обьект
        currentDate.setHours(data.getCall(lesson.getLesson(position).getTime()-1).getEnd().H);
        currentDate.setMinutes(data.getCall(lesson.getLesson(position).getTime()-1).getEnd().M);
        long en = currentDate.getTime();// переводи полученный обьект в милллисекунды

        long current = System.currentTimeMillis();//текущие время в миллисекундах

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK)-1;//тполучаем номер дня недели

        //проверяем текущее занятие явяляется ли текущми
        if (((star < current) && (en > current))&&dayOfWeek==lesson.getDay()) {

            time.setBackgroundColor(ctn.getResources().getColor(R.color.colorMainText));// изменяем цвет на текущий

        }

        RelativeLayout les = (RelativeLayout) main.findViewById(R.id.lesson);//контейнер о информации о занятии
        TextView type = (TextView) les.findViewById(R.id.type);
        type.setText(data.getType(lesson.getLesson(position).getType()-1));//устанавливаем тип занятия
        TextView title = (TextView) les.findViewById(R.id.title);
        title.setText(lesson.getLesson(position).getName());//устанавливаем название пары
        TextView name = (TextView) les.findViewById(R.id.name);
        name.setText(lesson.getLesson(position).getTeach());//устанавливаем имя прпеподавателя
    }


    @Override

    public int getItemCount() {
        return lesson.getSize();
    }


}
