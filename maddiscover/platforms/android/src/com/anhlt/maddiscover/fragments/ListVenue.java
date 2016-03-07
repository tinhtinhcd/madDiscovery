package com.anhlt.maddiscover.fragments;
import android.app.Fragment;
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
import com.anhlt.maddiscover.adapter.VenueListAdapter;
import com.anhlt.maddiscover.services.OrganizerService;
import com.anhlt.maddiscover.services.VenueService;

/**
 * Created by anhlt on 2/18/16.
 */
public class ListVenue extends ListFragment implements AdapterView.OnItemClickListener {

    VenueService venueService;
    VenueListAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        venueService = new VenueService(getActivity().getApplicationContext());
        adapter = new VenueListAdapter(getActivity(),venueService.getVenueList());
        setListAdapter(adapter);
        return inflater.inflate(R.layout.list_venue,container,false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_venue, menu);
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
