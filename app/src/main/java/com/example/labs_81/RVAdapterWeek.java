package com.example.labs_81;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class RVAdapterWeek extends RecyclerView.Adapter<RVAdapterWeek.CardViewHolder> {


        ListDay days;
        DataBase data;

        int choice = 1;
        Context ctn;
        int choiceInt = -1;
        SharedPreferences sPref;

public static class CardViewHolder extends RecyclerView.ViewHolder {

    CardView cardView;
    String choice;

    CardViewHolder(CardView cv) {
        super(cv);
        cardView = cv;
    }
}

    public void removeChoice(int newChoiceInt, int newChoice) {
        this.choice = newChoice;
        int choice = choiceInt;
        this.notifyItemRangeChanged(newChoiceInt, 1);
        if(choice!=-1)  this.notifyItemRangeChanged(choice, 1);

    }

    public void removeOne(int newChoiceInt) {
        this.choice = -1;
        int choice = choiceInt;

        choiceInt = -1;
        this.notifyItemRangeChanged(newChoiceInt, 1);

    }
    RVAdapterWeek(ListDay days, DataBase data, int choice,Context ctn) {
        this.days = days;
        this.data = data;
        this.ctn = ctn;
        this.choice = choice-1;
    }


    @Override
    public RVAdapterWeek.CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())

                .inflate(R.layout.card_day, parent, false);

        return new RVAdapterWeek.CardViewHolder(cv);

    }


    @Override

    public void onBindViewHolder(RVAdapterWeek.CardViewHolder cardViewHolder, final int position) {

        CardView cardView = cardViewHolder.cardView;
        cardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(position!=choiceInt)removeChoice(position, days.getDay(position).getDay());
                else {
                    removeOne(position);
                }
            }
        });
        RelativeLayout main = (RelativeLayout) cardView.findViewById(R.id.main);
        if(choice==days.getDay(position).getDay()) {
            choiceInt =position;
            RelativeLayout list = (RelativeLayout) main.findViewById(R.id.list);
            RecyclerView rv = (RecyclerView) list.findViewById(R.id.rv);
            LinearLayoutManager llm = new LinearLayoutManager(ctn);
            rv.setLayoutManager(llm);
            RelativeLayout.LayoutParams feedCommentParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            rv.setLayoutParams(feedCommentParams);
            RVAdapterLesson adapter = new RVAdapterLesson(days.getDay(position), data, ctn);
            rv.setAdapter(adapter);
        }
        else {
            RelativeLayout list = (RelativeLayout) main.findViewById(R.id.list);
            RecyclerView rv = (RecyclerView) list.findViewById(R.id.rv);
            LinearLayoutManager llm = new LinearLayoutManager(ctn);
            rv.setLayoutManager(llm);
            if(rv.getAdapter()!=null) rv.getAdapter().notifyItemRangeRemoved(0,rv.getAdapter().getItemCount());
            RelativeLayout.LayoutParams feedCommentParams = new RelativeLayout.LayoutParams(0, 0);
            rv.setLayoutParams(feedCommentParams);
        }
        RelativeLayout text = (RelativeLayout) main.findViewById(R.id.text);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK)-1;
        if (dayOfWeek==days.getDay(position).getDay())text.setBackgroundColor(ctn.getResources().getColor(R.color.colorMainText));
        else text.setBackgroundColor(ctn.getResources().getColor(R.color.colorPrimary));
        TextView day = (TextView)text.findViewById(R.id.day);
        day.setText(data.getWeek(days.getDay(position).getDay()-1));

    }


    @Override

    public int getItemCount() {
        return days.getSize();
    }

}
