package com.maxim.denisov.tander;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Максим on 06.08.2017.
 */

public class NumberAdapter extends BaseAdapter {
    private Context context;

    private List<Number> numbers;

    public NumberAdapter(Context context, List<Number> numbers) {
        this.context = context;
        this.numbers = numbers;
    }


    @Override
    public int getCount() {
        return numbers.size();
    }

    @Override
    public Object getItem(int position) {
        return numbers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int id, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        View grid;

        if (convertView == null) {
            grid = new View(context);
            //LayoutInflater inflater = getLayoutInflater();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            grid = inflater.inflate(R.layout.cellgrid, parent, false);
        } else {
            grid = (View) convertView;
        }

        Button button = (Button) grid.findViewById(R.id.button);
        TextView textView = (TextView) grid.findViewById(R.id.textpart);

        textView.setText(String.valueOf(id));
        button.setText("Моя кнопка");
        //button.setRatio(0.4f);


        return grid;
    }

}
