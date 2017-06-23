package com.example.tehlivets.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class StringAdapterWithDeleteButton extends ArrayAdapter<String> {

    private List<String> items;

    public StringAdapterWithDeleteButton(Context context, int resource, List<String> items) {
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
                    notifyDataSetChanged();
                }
            });
        }
        return result;
    }
}
