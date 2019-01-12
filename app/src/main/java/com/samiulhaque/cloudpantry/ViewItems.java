package com.samiulhaque.cloudpantry;

import android.content.ContextWrapper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.DataSource;
import com.couchbase.lite.Database;
import com.couchbase.lite.DatabaseConfiguration;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryBuilder;
import com.couchbase.lite.Result;
import com.couchbase.lite.ResultSet;
import com.couchbase.lite.SelectResult;

import java.util.ArrayList;

/**
 * Created by haquekm on 2017-11-26.
 */

public class ViewItems extends Fragment {
    ListView listView;
    ArrayList<String> arr;
    ContextWrapper cw;
    DatabaseConfiguration config;
    Database database;
    Query query;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.view_items_tab, container, false);
        listView = (ListView) rootView.findViewById(R.id.ItemsList);
        arr = new ArrayList<>();
        cw = new ContextWrapper(getActivity());
        config = new DatabaseConfiguration(cw);
        try {
            database = new Database("mydb", config);
            query = QueryBuilder.select(SelectResult.all()).from(DataSource.database(database));
            ResultSet rs = query.execute();
            for (Result curr : rs.allResults()) {
                arr.add(String.format("%s\t\t%s",
                        curr.getDictionary("mydb").getString("name"),
                        curr.getDictionary("mydb").getInt("estimatedDate")));
                Log.d("testing", "createdAt=" + curr.getDictionary("mydb").getString("createdAt"));
            }
            Log.d("testing", "size=" + Integer.toString(rs.allResults().size()));

        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> ard = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arr);
        listView.setAdapter(ard);

        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            try {
                ResultSet rs = query.execute();
                arr.clear();
                for (Result curr : rs.allResults()) {
                    arr.add(String.format("%s\t\t%s",
                            curr.getDictionary("mydb").getString("name"),
                            curr.getDictionary("mydb").getInt("estimatedDate")));
                    Log.d("testing", "createdAt=" + curr.getDictionary("mydb").getString("createdAt"));
                }

            } catch (CouchbaseLiteException e) {
                e.printStackTrace();
            }
        }
    }
}