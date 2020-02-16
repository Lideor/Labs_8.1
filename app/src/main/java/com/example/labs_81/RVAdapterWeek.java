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


        private ListDay days;//список дней в выбранной недели
        private DataBase data;//база данных

        private int choice = 1;//выбранный день
        private Context ctn;//контекст вызывашего адаптер обьекта
        private int choiceInt = -1;

public static class CardViewHolder extends RecyclerView.ViewHolder {

    private CardView cardView;

    CardViewHolder(CardView cv) {
        super(cv);
        cardView = cv;
    }
}

    public void removeChoice(int newChoiceInt, int newChoice) {

        //изменяем выбор просматриваемого дня недели
        this.choice = newChoice;
        int choice = choiceInt;


        notifyItemRangeChanged(newChoiceInt, 1);//изменяем вид нового выбранного дня
        if(choice!=-1) notifyItemRangeChanged(choice, 1);//изменяем вид предыдущего выбранного выбранного дня

    }

    //отключаем вид дня недели при нажатии на активный
    public void removeOne(int newChoiceInt) {
        this.choice = -1;
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
        //слушатель нажатия на весь обьект
        cardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(position!=choiceInt)removeChoice(position, days.getDay(position).getDay());
                else {
                    removeOne(position);
                }
            }
        });

        //контейнер всего обьекта
        RelativeLayout main = (RelativeLayout) cardView.findViewById(R.id.main);
        //контейнер текста названия
        RelativeLayout text = (RelativeLayout) main.findViewById(R.id.text);
        TextView day = (TextView)text.findViewById(R.id.day);

        RelativeLayout list = (RelativeLayout) main.findViewById(R.id.list);//контейнер списка
        RecyclerView rv = (RecyclerView) list.findViewById(R.id.rv);// список занятий

        // В случае если позиция в списке обьектов сопадает с днем выбранным пользователя, изменяем цвет шапки на активный
        // и выводим список занятий в данный день
        if(choice==days.getDay(position).getDay()) {

            RelativeLayout.LayoutParams relev = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);//дублируем настройки лайаут текста
            relev.setMargins(0,0,0,(int)ctn.getResources().getDimension(R.dimen.total_margin));// увеличиываем отступ
            text.setLayoutParams(relev);// устанавливаем найтсройик

            choiceInt = position;// устанавдливаем позицию текуущего дня

            LinearLayoutManager llm = new LinearLayoutManager(ctn);// менджер списка занятий
            rv.setLayoutManager(llm);//установка менджера

            RelativeLayout.LayoutParams feedCommentParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);// создаем параметры лайаута чтобы его размер зависил от содержимого
            rv.setLayoutParams(feedCommentParams);// устанавливаем созданные настройки для списка занаятий

            RVAdapterLesson adapter = new RVAdapterLesson(days.getDay(position), data, ctn); // создаем адаптер списка занятий
            rv.setAdapter(adapter);     //устанавливаем ажаптер

        }
        else {

            LinearLayoutManager llm = new LinearLayoutManager(ctn);// менджер списка занятий
            rv.setLayoutManager(llm);//установка менджера
            // обновлемя список при его наличии
            if(rv.getAdapter()!=null) rv.getAdapter().notifyItemRangeRemoved(0,rv.getAdapter().getItemCount());

            // создаем параметры лайаута чтобы его размер были нулевыми
            RelativeLayout.LayoutParams feedCommentParams = new RelativeLayout.LayoutParams(0, 0);
            rv.setLayoutParams(feedCommentParams);// устанавливаем параметры для контйенера списка занятий

            RelativeLayout.LayoutParams relev = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);//дублируем настройки лайаут текста
            relev.setMargins(0,0,0,0);// увеличиываем отступ
            text.setLayoutParams(relev);// устанавливаем найтсройик
        }

        //определение текущего дня недели и номера недеоли
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK)-1;
        int Week = c.get(Calendar.WEEK_OF_YEAR);
        int t = Week % 2;
        int a = days.getNumber();
        //устанавливаем цвет текущей позиции обьекта в активный в случае если он совпалдает с текущим днем и находится в активной недели
        if ((dayOfWeek==days.getDay(position).getDay())&&(Week % 2 == days.getNumber()-1))text.setBackgroundColor
                (ctn.getResources().getColor(R.color.colorMainText));
        else text.setBackgroundColor(ctn.getResources().getColor(R.color.colorAccent));

        //устанавливаем название дня
        day.setText(data.getWeek(days.getDay(position).getDay()-1));

    }


    @Override

    public int getItemCount() {
        return days.getSize();
    }

}
