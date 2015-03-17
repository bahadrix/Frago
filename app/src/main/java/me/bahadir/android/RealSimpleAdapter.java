package me.bahadir.android;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by bahadir on 16/03/15.
 */
public class RealSimpleAdapter  {
    private OnItemSelectListener listener = null;
    ArrayList<LinkedHashMap<String, String>> items;

    private final ListView listView;
    private SimpleAdapter adapter;

    public RealSimpleAdapter(ListView listView) {

        this.listView = listView;
        items = new ArrayList<>();
        adapter = new SimpleAdapter(
                listView.getContext(),
                items,
                android.R.layout.simple_list_item_2,
                new String[]{"title", "desc"},
                new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(listener!=null) {
                    listener.onItemSelect(position, items.get(position).get("data"));
                }
            }
        });
    }


    public void addItem(String title, String description, String data) {
        LinkedHashMap<String, String> item = new LinkedHashMap<String, String>();
        item.put("title", title);
        item.put("desc", description);
        item.put("data", data);
        items.add(item);
    }

    public void build() {

    }

    public void setListener(OnItemSelectListener listener) {
        this.listener = listener;
    }

    public String getData(int index) {
        return items.get(index).get("data");
    }

    public interface OnItemSelectListener {
        public void onItemSelect(int index, String data);
    }
}
