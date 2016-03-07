package com.anhlt.maddiscover.activities;

import com.anhlt.maddiscover.entities.Event;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.anhlt.maddiscover.R;
import com.anhlt.maddiscover.entities.Organizer;
import com.anhlt.maddiscover.entities.Venue;
import com.anhlt.maddiscover.fragments.ListEvent;
import com.anhlt.maddiscover.services.BaseService;
import com.anhlt.maddiscover.services.EventService;
import com.anhlt.maddiscover.services.OrganizerService;
import com.anhlt.maddiscover.services.VenueService;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class CreateEvent extends BaseFragment{

    Context context;
    BaseService baseService;
    VenueService venueService;
    OrganizerService organizerService;
    AutoCompleteTextView venueTxt;
    AutoCompleteTextView organizerTxt;
    EventService eventService;
    EditText eventTxt,startDatetxt,remarkTxt;
    public boolean back = false;

    public static CreateEvent getInstance(){
        return new CreateEvent();
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        context = getActivity().getApplicationContext();
        eventService = new EventService(context);
        return inflater.inflate(R.layout.event_form, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_create_event, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()  == R.id.save){
            back = true;
            saveNewEvent();
        }if (item.getItemId()  == R.id.save_more){
            saveNewEvent();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
    }

    private void saveNewEvent(){
        String validData = validData();
        if (eventTxt.getText().toString().isEmpty()){
            showErrorDialog("Error", "Please enter event name");
        }else if(validData.length()>0){
            actionOnInvalidData(validData);
        }else{
            if(validEvent()){
                save();
            }else{
                showErrorDialog("Error", "This event has been create by the same organizer on this location!");
            }
        }
    }

    public void actionOnInvalidData(String validData){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialogStyle);
        builder.setTitle("Invalid Data!");
        builder.setMessage(validData);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                save();
            }
        }).setCancelable(true);
        builder.show();
    }

    private void back(){
        if(back){
            baseService = new BaseService(getActivity().getFragmentManager(),context);
            baseService.replaceFragment(new ListEvent());
        }else{
            resetForm();
        }
    }

    private void save(){
        Event event = new Event();
        event.setEventName(eventTxt.getText().toString());

        Long venueId = venueId();
        String venueName =  venueTxt.getText().toString();

        Long organizerId = oganizerId();
        String organizerName =  organizerTxt.getText().toString();

        if(venueId!=null && venueId > 0L){
            event.setVenueId(venueId);
        }else if(venueId == null){
            Venue venue = new Venue();
            venue.setName(venueName);
            venueService.saveNewVenue(venue);
            event.setVenueId(venueService.findByName(venueName).getId());
        }

        if(organizerId!=null && organizerId > 0L){
            event.setOrganizer(organizerId);
        }else if(organizerId == null){
            Organizer organizer = new Organizer();
            organizer.setName(organizerName);
            organizerService.saveNewOrganizer(organizer);
            event.setOrganizer(organizerService.findByName(organizerName).getId());
        }

        String startDate = startDatetxt.getText().toString();
            if(startDate.length()>0){try {
                event.setStartDate(new Date(startDate));
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        String remark = remarkTxt.getText().toString();
        if(remark.length()>0)
            event.setRemark(remark);

        eventService.saveNewEvent(event);
        back();

    }

    private void resetForm(){

        eventTxt.setText("");
        organizerTxt.setText("");
        venueTxt.setText("");
        startDatetxt.setText("");
        remarkTxt.setText("");

    }

    private String validData(){
        StringBuilder error = new StringBuilder();
        if(venueId()==null)
            error.append("This venue has not exists. Would you like to quick create with this name?");
        if(oganizerId()==null)
            error.append("This organizer has not exists. Would you like to quick create with this name?");
        return error.toString();
    }

    private Long venueId(){
        String venue = venueTxt.getText().toString();
        if(venue == null || venue.isEmpty())
            return 0L;
        else
            try {
                return venueService.findByName(venue).getId();
            }catch (Exception e){
                return null;
            }
    }

    private Long oganizerId(){
        String organizer = organizerTxt.getText().toString();
        if(organizer == null || organizer.isEmpty())
            return 0L;
        else
            try {
                return organizerService.findByName(organizer).getId();
            }catch (Exception e){
                return null;
            }
    }

    private boolean validEvent(){
        if(!venueTxt.getText().toString().isEmpty()&&!organizerTxt.getText().toString().isEmpty())
            return eventService.validEvent(eventTxt.getText().toString(), venueTxt.getText().toString(), organizerTxt.getText().toString());
        else
            return true;
    }

    private void initData(){
        loadVenue();
        loadOrganizer();
        initComponent();
    }

    private void initComponent(){
        eventTxt = (EditText)getView().findViewById(R.id.event_name);
        startDatetxt = (EditText)getView().findViewById(R.id.start_date);
        remarkTxt = (EditText)getView().findViewById(R.id.remark);
    }

    private void loadVenue(){

        venueService = new VenueService(context);

        List<String> venueNames = venueService.listVenueName();
        String[] array = new String[venueNames.size()];
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, venueNames.toArray(array));

        venueTxt = (AutoCompleteTextView)getView().findViewById(R.id.venue);
        venueTxt.setAdapter(adapter);
        venueTxt.setThreshold(1);

    }

    private void loadOrganizer(){

        organizerService = new OrganizerService(context);

        List<String> organizerNames = organizerService.listOrganizerName();
        String[] array = new String[organizerNames.size()];
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, organizerNames.toArray(array));

        organizerTxt = (AutoCompleteTextView)getView().findViewById(R.id.organizer);
        organizerTxt.setAdapter(adapter);
        organizerTxt.setThreshold(1);

    }

}
