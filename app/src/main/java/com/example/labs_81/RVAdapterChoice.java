package com.example.labs_81;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.example.labs_81.MainActivity.LOG_TAG;

public class RVAdapterChoice extends RecyclerView.Adapter<RVAdapterChoice.CardViewHolder> {


    private List<String> cards;// список назщваний групп
    private String choice; // название выбранной группы
    private Context ctn; // контекст того, кто вызвал данный класс
    private int choiceInt; // позиция выбранного обьекта в списке
    private SharedPreferences sPref;// пользовательсие настройик

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private String choice;

        CardViewHolder(CardView cv, Context ctn) {
            super(cv);
            cardView = cv;
            final Context context = ctn;
        }
    }

    public void removeChoice(int newChoiceInt, String newChoice) {

        //перенастройка выбора активной позиции
        choice = newChoice;
        int choice = choiceInt;

        this.notifyItemRangeChanged(newChoiceInt, 1);//обновляем обьект на который нажал пользователь
        this.notifyItemRangeChanged(choice, 1);//обновляем обьект который был предыдущим активнм

        sPref = ctn.getSharedPreferences("prefs", MODE_PRIVATE);//доступ к настройкам с имененм prefs

        SharedPreferences.Editor ed = sPref.edit();// класс для измсенения настроек
        ed.putString("numberGroup", newChoice);// записываем в настройки новое значение выбранной группы
        ed.commit();//записываем изменения

    }

    RVAdapterChoice(List cards, String choice, Context ctn) {

        this.cards = cards;
        this.choice = choice;
        this.ctn = ctn;

    }


    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())

                .inflate(R.layout.choice_card, parent, false);

        return new CardViewHolder(cv, ctn);

    }


    @Override

    public void onBindViewHolder(CardViewHolder cardViewHolder, final int position) {

        final CardView cardView = cardViewHolder.cardView;

        //устанавливаем слушателя на нажатие на элемент
        cardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                TextView title = (TextView) cardView.findViewById(R.id.info_text);
                removeChoice(position, title.getText().toString()); //вызываем метод переключение активного выбора тек. группы

            }
        });

        TextView title = (TextView) cardView.findViewById(R.id.info_text);

        //установка названия группы
        String buf = cards.get(position);
        title.setText(buf);


        //если название группы элемента текущей позиции в списке совпадает с названием группы установленной пользолвателем устанавливаем
        // внешний вид обьекта на активный
        if (buf.equals(choice)) {

            choiceInt = position;// запоминаем текущий выбор пользователя пользя ввиде позиции обьекта в списке

            title.setTextColor(ctn.getResources().getColor(R.color.colorMainCard));// устанавливаем цвет текста
            title.setPadding(0, -7, 0, 0);// поднимаем текст на 7pd

            cardView.setCardBackgroundColor(ctn.getResources().getColor(R.color.colorMainText));//устанавливаем цвет фона всего обьекта

            ImageButton btn = (ImageButton) cardView.findViewById(R.id.imageButton);
            btn.setVisibility(View.VISIBLE);// устанавливаем видимость картинки на видимую

            View view = (View) cardView.findViewById(R.id.view);
            view.setVisibility(View.VISIBLE);// устанавливаем видимость линии на видимую
        } else {
            View view = (View) cardView.findViewById(R.id.view);
            view.setVisibility(View.INVISIBLE);// устанавливаем видимость линии на невидимую

            title.setTextColor(ctn.getResources().getColor(R.color.colorMainCard));// задаем стандартый цвет
            title.setPadding(0, 0, 0, 0);// задаем стандартый отступ

            cardView.setCardBackgroundColor(ctn.getResources().getColor(R.color.colorAccent));// задаем стандартый цвет

            ImageButton btn = (ImageButton) cardView.findViewById(R.id.imageButton);
            btn.setVisibility(View.INVISIBLE);// устанавливаем видимость картинки на невидимую
        }

    }


    @Override

    public int getItemCount() {

        return cards.size();

    }

}
