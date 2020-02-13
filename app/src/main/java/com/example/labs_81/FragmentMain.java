package com.example.labs_81;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class FragmentMain extends Fragment {
    View v;
    int number;
    Context ctn;
    public FragmentMain(int number, Context ctn){
        this.number = number;
        this.ctn = ctn;
    }

    public int getNumber() {
        return number;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

    v=inflater.inflate(R.layout.tabs_main_fragment,container,false);
    RecyclerView rv = (RecyclerView) v.findViewById(R.id.rv);
    String numberGroup = getNumberGroup();
    AllGroup group = new JsonParse().importGroupJsonInFile(container.getContext());
    DataBase data = new JsonParse().importDataJsonInFile(container.getContext());

    LinearLayoutManager llm = new LinearLayoutManager(container.getContext());
    rv.setLayoutManager(llm);

    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
    ListDay as = group.getGroup(numberGroup).getWeek(1);

    RVAdapterWeek adapter = new RVAdapterWeek(group.getGroup(numberGroup).getWeek(number),data,dayOfWeek,container.getContext());
    rv.setAdapter(adapter);
        return v;
}
    private String getNumberGroup() {
        SharedPreferences sPref = ctn.getSharedPreferences("prefs",MODE_PRIVATE);
        return sPref.getString("numberGroup", "-1");
    }

}
