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

    private View v;
    private int number;//номер недели
    private Context ctn;

    public FragmentMain(int number, Context ctn) {

        this.number = number;
        this.ctn = ctn;

    }
    //метод вызывающися прм первой прорисовки фрагмента
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.tabs_main_fragment, container, false);

        //доступ к recieview, в котором будет распологаться список дней выбранной недели
        RecyclerView rv = (RecyclerView) v.findViewById(R.id.rv);

        String numberGroup = getNumberGroup();//получаем название группы

        AllGroup group = new JsonParse().importGroupJsonInFile(container.getContext());// получаем информацию о всех группах
        DataBase data = new JsonParse().importDataJsonInFile(container.getContext());// переводим название групп в виде массива строк

        // создаем менджер отвечающий за расположение объектов
        LinearLayoutManager llm = new LinearLayoutManager(container.getContext());// создаем менджер отвечающий за расположение объектов
        // в recieview
        rv.setLayoutManager(llm); // устанавливаем созданный менждер

        //получаем номер текущего дня
        Calendar c = Calendar.getInstance();//
        c.setTime(new Date());
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        //создаем адаптер недели
        RVAdapterWeek adapter = new RVAdapterWeek(group.getGroup(numberGroup).getWeek(number), data, dayOfWeek, container.getContext());
        rv.setAdapter(adapter);// устанавливаем адаптер для спика днец
        return v;
    }

    private String getNumberGroup() {

        SharedPreferences sPref = ctn.getSharedPreferences("prefs", MODE_PRIVATE); //доступ к настройкам с имененм prefs
        return sPref.getString("numberGroup", "-1");// получаем значение хранящиеся под именем "numberGroup"

    }

}
