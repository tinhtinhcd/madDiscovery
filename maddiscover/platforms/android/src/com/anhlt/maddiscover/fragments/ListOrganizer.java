package com.anhlt.maddiscover.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.anhlt.maddiscover.R;
import com.anhlt.maddiscover.adapter.OrganizerListAdapter;
import com.anhlt.maddiscover.services.OrganizerService;

/**
 * Created by anhlt on 2/19/16.
 */
public class ListOrganizer extends ListFragment implements AdapterView.OnItemClickListener {

    OrganizerService organizerService;
    OrganizerListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        organizerService = new OrganizerService(getActivity().getApplicationContext());
        adapter = new OrganizerListAdapter(getActivity(),organizerService.getOrganizerList());
        setListAdapter(adapter);
        return inflater.inflate(R.layout.list_organizer,container,false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_organizer, menu);
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

    private void listFilter(){

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
