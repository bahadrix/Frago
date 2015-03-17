package me.bahadir.bil425.frago;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import me.bahadir.android.RealSimpleAdapter;


public class ListFragment extends Fragment {

    private RealSimpleAdapter.OnItemSelectListener listener;
    private RealSimpleAdapter adapter;

    public ListFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listBookmarks);

        adapter = new RealSimpleAdapter(listView);

        adapter.addItem("Google","Our only search engine", "http://www.google.com");
        adapter.addItem("Yahoo", "Legend!", "http://www.yahoo.com");

        adapter.setListener(listener);

        return view;

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            listener = (RealSimpleAdapter.OnItemSelectListener) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement RealSimpleAdapter.OnItemSelectListener");
        }
    }
}
