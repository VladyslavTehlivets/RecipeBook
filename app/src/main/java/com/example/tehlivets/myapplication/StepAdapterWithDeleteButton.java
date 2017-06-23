package com.example.tehlivets.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;
/**
 * Created by Vladyslav on 29.05.2017.
 */

public class StepAdapterWithDeleteButton extends ArrayAdapter<Step> {

    private List<Step> items;

    public StepAdapterWithDeleteButton(Context context, int resource, List<Step> items) {
        super(context, resource, items);
        this.items = items;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View result = convertView;
        final String item = getItem(position).toString();

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_with_delete, parent, false);
            result = convertView;
        }

        if (item != null) {
            TextView text = (TextView) result.findViewById(R.id.item);
            ImageButton delete = (ImageButton) result.findViewById(R.id.deleteItem);
            text.setText(item);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    items.remove(position);
                    for (int i = 0; i < items.size(); i++) {
                        items.get(i).update(i+1);
                    }
                    notifyDataSetChanged();
                }
            });
        }
        return result;
    }
}
