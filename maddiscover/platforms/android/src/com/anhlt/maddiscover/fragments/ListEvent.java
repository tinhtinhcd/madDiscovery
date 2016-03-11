package com.anhlt.maddiscover.fragments;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.anhlt.maddiscover.R;
import com.anhlt.maddiscover.adapter.EventListAdapter;
import com.anhlt.maddiscover.services.EventService;

import android.app.ListFragment;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by anhlt on 2/19/16.
 */
public class ListEvent extends ListFragment implements OnItemClickListener {

    Context context;
    EventService eventService;
    EventListAdapter adapter;

    @Override
    public void onAttach(Context context) {
        this.context =context;
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        eventService = new EventService(getActivity().getApplicationContext());
        adapter = new EventListAdapter(getActivity(),eventService.getEventList());
        setListAdapter(adapter);
        return inflater.inflate(R.layout.list_event,container,false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_event, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onStart() {
        super.onStart();
        listFilter();
    }

    private void listFilter() {

        EditText filter = (EditText) getView().findViewById(R.id.filter);
        filter.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }
        });
    }

}
