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



    List<String> cards;
    String choice;
    Context ctn;
    int choiceInt;
    SharedPreferences sPref;

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        String choice;
        CardViewHolder(CardView cv,Context ctn, RVAdapterChoice rv) {
            super(cv);
            cardView = cv;
            final Context context = ctn;
            int v = cardView.getVerticalScrollbarPosition();
        }
    }

    public void removeChoice(int newChoiceInt,String newChoice){
        choice=newChoice;
        int choice = choiceInt;
        this.notifyItemRangeChanged(newChoiceInt,1);
        this.notifyItemRangeChanged(choice,1);

        sPref = ctn.getSharedPreferences("prefs",MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("numberGroup",newChoice);
        ed.commit();
    }

    RVAdapterChoice(List cards,String choice,Context ctn){
        this.cards = cards;
        this.choice=choice;
        this.ctn = ctn;

    }



    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())

                .inflate(R.layout.choice_card, parent, false);

        return new CardViewHolder(cv,ctn,this);

    }



    @Override

    public void onBindViewHolder(CardViewHolder cardViewHolder, final int position) {

        final CardView cardView = cardViewHolder.cardView;

        TextView title = (TextView)cardView.findViewById(R.id.info_text);
        cardView.setOnClickListener(new View.OnClickListener() {

       @Override
       public void onClick(View view) {

            TextView title = (TextView)cardView.findViewById(R.id.info_text);
            removeChoice(position, title.getText().toString());

        }
        });
        String buf = cards.get(position);
        title.setText(buf);
        if(buf.equals(choice)) {

            choiceInt = position;
            title.setTextColor(ctn.getResources().getColor(R.color.colorMainCard));
            title.setPadding(0,-7,0,0);
            ImageButton btn =(ImageButton)cardView.findViewById(R.id.imageButton);
            cardView.setCardBackgroundColor(ctn.getResources().getColor(R.color.colorMainText));
            btn.setVisibility(View.VISIBLE);
            View view = (View)cardView.findViewById(R.id.view);
            view.setVisibility(View.VISIBLE);
        }
        else{
            View view = (View)cardView.findViewById(R.id.view);
            view.setVisibility(View.INVISIBLE);
            title.setTextColor(ctn.getResources().getColor(R.color.colorMainCard));
            title.setPadding(0,0,0,0);
            cardView.setCardBackgroundColor(ctn.getResources().getColor(R.color.colorAccent));
            ImageButton btn =(ImageButton)cardView.findViewById(R.id.imageButton);
            btn.setVisibility(View.INVISIBLE);
        }

    }



    @Override

    public int getItemCount() {

        return cards.size();

    }

}
