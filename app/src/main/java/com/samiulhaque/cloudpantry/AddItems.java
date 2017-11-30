package com.samiulhaque.cloudpantry; /**
 * Created by haquekm on 2017-11-26.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

public class AddItems extends Fragment {
    EditText item1;
    EditText item2;
    EditText item3;
    EditText item4;
    EditText item5;
    EditText item6;
    EditText date1;
    EditText date2;
    EditText date3;
    EditText date4;
    EditText date5;
    EditText date6;
    Button addItems;
    HashMap<String, Integer> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.add_items_tab, container, false);
        item1 = rootView.findViewById(R.id.item1);
        item2 = rootView.findViewById(R.id.item2);
        item3 = rootView.findViewById(R.id.item3);
        item4 = rootView.findViewById(R.id.item4);
        item5 = rootView.findViewById(R.id.item5);
        item6 = rootView.findViewById(R.id.item6);
        date1 = rootView.findViewById(R.id.date1);
        date2 = rootView.findViewById(R.id.date2);
        date3 = rootView.findViewById(R.id.date3);
        date4 = rootView.findViewById(R.id.date4);
        date5 = rootView.findViewById(R.id.date5);
        date6 = rootView.findViewById(R.id.date6);
        addItems = rootView.findViewById(R.id.addItemsBtn);
        final EditText[] items = {item1, item2, item3, item4, item5, item6};
        final EditText[] dates = {date1, date2, date3, date4, date5, date6};
        data = new HashMap<>();
        addItems.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                for (int i = 0; i < items.length; i++) {
                    String item_name = items[i].getText().toString().trim();
                    String item_date = dates[i].getText().toString().trim();
                    if (TextUtils.isEmpty(item_name) && !TextUtils.isEmpty(item_date)) {
                        items[i].setError("Item name cannot be empty");
                    } else if (!TextUtils.isEmpty(item_name) && TextUtils.isEmpty(item_date)) {
                        dates[i].setError("Item date cannot be empty");
                    } else if (!TextUtils.isEmpty(item_name) && !TextUtils.isEmpty(item_date)) {
                        if (data.containsKey(item_name)) {
                            items[i].setError("Item with the same name already exists in your list");
                        } else {
                            data.put(item_name, Integer.parseInt(item_date));
                        }
                    }
                }
                Log.d(data.toString(), "addItems button on click being executed");

            }
        });


        return rootView;
    }
}
