package com.example.labs_81;

import android.content.Context;
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

import static com.example.labs_81.MainActivity.LOG_TAG;

public class RVAdapterChoice extends RecyclerView.Adapter<RVAdapterChoice.CardViewHolder> {



    List<String> cards;
    String choice;
    Context ctn;


    public static class CardViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        String choice;
        CardViewHolder(CardView cv,Context ctn) {
            super(cv);
            cardView = cv;
            final Context context = ctn;
            int v = cardView.getVerticalScrollbarPosition();

            cardView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "position = " + getLayoutPosition(), Toast.LENGTH_SHORT).show();

                    //go through each item if you have few items within recycler view
                    if(getLayoutPosition()==0){
                        //Do whatever you want here

                    }else if(getLayoutPosition()==1){
                        //Do whatever you want here

                    }else if(getLayoutPosition()==2){

                    }else if(getLayoutPosition()==3){

                    }else if(getLayoutPosition()==4){

                    }else if(getLayoutPosition()==5){

                    }

                    //or you can use For loop if you have long list of items. Use its length or size of the list as



                }
            });
        }

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

        return new CardViewHolder(cv,ctn);

    }



    @Override

    public void onBindViewHolder(CardViewHolder cardViewHolder, int position) {

        CardView cardView = cardViewHolder.cardView;

        TextView title = (TextView)cardView.findViewById(R.id.info_text);

        String buf = cards.get(position);
        title.setText(buf);
        if(buf.equals(choice)) {

            title.setTextColor(ctn.getResources().getColor(R.color.colorAccent));
            title.setPadding(0,-7,0,0);
            ImageButton btn =(ImageButton)cardView.findViewById(R.id.imageButton);
            btn.setVisibility(View.VISIBLE);
            View view = (View)cardView.findViewById(R.id.view);
            view.setVisibility(View.VISIBLE);
        }

    }



    @Override

    public int getItemCount() {

        return cards.size();

    }

}
