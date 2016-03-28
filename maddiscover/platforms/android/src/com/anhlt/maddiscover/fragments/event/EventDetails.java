package com.anhlt.maddiscover.fragments.event;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.anhlt.maddiscover.R;
import com.anhlt.maddiscover.adapter.ImageListAdapter;
import com.anhlt.maddiscover.adapter.ReportListAdapter;
import com.anhlt.maddiscover.entities.Report;
import com.anhlt.maddiscover.form.EventForm;
import com.anhlt.maddiscover.fragments.BaseFragment;
import com.anhlt.maddiscover.services.BaseService;
import com.anhlt.maddiscover.services.EventService;
import com.anhlt.maddiscover.services.ReportService;
import com.anhlt.maddiscover.utils.ExpandableHeightGridView;

import android.view.View.MeasureSpec;
import android.widget.AbsListView.LayoutParams;

import java.util.Date;

/**
 * Created by anhlt on 3/13/16.
 */
public class EventDetails extends BaseFragment{

    Long eventId;
    Context context;
    EventService eventService;
    TextView eventName,startDate,organizer,venue,remark;
    BaseService baseService;
    ReportService reportService;
    ReportListAdapter adapter;
    ListView list;

    public static String[] eatFoodyImages = {
            "http://i.imgur.com/rFLNqWI.jpg",
            "http://i.imgur.com/C9pBVt7.jpg",
            "http://i.imgur.com/rT5vXE1.jpg",
            "http://i.imgur.com/aIy5R2k.jpg",
            "http://i.imgur.com/MoJs9pT.jpg",
            "http://i.imgur.com/S963yEM.jpg",
            "http://i.imgur.com/rLR2cyc.jpg",
            "http://i.imgur.com/SEPdUIx.jpg",
            "http://i.imgur.com/aC9OjaM.jpg",
            "http://i.imgur.com/76Jfv9b.jpg",
            "http://i.imgur.com/fUX7EIB.jpg",
            "http://i.imgur.com/syELajx.jpg",
            "http://i.imgur.com/COzBnru.jpg",
            "http://i.imgur.com/Z3QjilA.jpg",
    };

    public static EventDetails getInstance(){
        return new EventDetails();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        eventId = getArguments().getLong("eventId");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        context = getActivity().getApplicationContext();
        eventService = new EventService(context);
        reportService = new ReportService(context);
        adapter = new ReportListAdapter(context,reportService.getReports(eventId));
        return inflater.inflate(R.layout.event_details, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_event_details, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()  == R.id.edit_event_detail){
            eventService.editEvent(getFragmentManager(),context, eventId);
        }else if (item.getItemId()  == R.id.delete_event_detail){
            deleteEvent(eventId);
        }else if(item.getItemId() == R.id.report_detail){
            showDialogReport();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        init();
        super.onStart();
    }

    private void init(){
        getUI();
        intiData();
    }

    private void getUI(){
        eventName = (TextView)getView().findViewById(R.id.event_name);
        startDate = (TextView)getView().findViewById(R.id.txt_start_date);
        organizer = (TextView)getView().findViewById(R.id.organ);
        venue = (TextView)getView().findViewById(R.id.location);
        remark = (TextView)getView().findViewById(R.id.re);
    }

    private void intiData(){
        if(eventId>0){
            EventForm event = eventService.getEventInfo(eventId);
            eventName.setText(event.getEventName());
            startDate.setText(event.getStartDate());
            organizer.setText(event.getOrganizer());
            venue.setText(event.getVenue());
            remark.setText(event.getRemark());
            list = (ListView) getView().getRootView().findViewById(R.id.list_report);
            list.setAdapter(adapter);
            setListViewHeightBasedOnChildren(list);
        }

        ExpandableHeightGridView gridView = (ExpandableHeightGridView)getView().findViewById(R.id.event_image);

        WindowManager wm = (WindowManager)getActivity().getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        gridView.setNumColumns(size.x/100 -1);
        gridView.setExpanded(true);
        gridView.setAdapter(new ImageListAdapter(getActivity(), eatFoodyImages));

    }

    private void deleteEvent(final Long eventId){
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialogStyle);
            builder.setTitle("Delete Event");
            builder.setMessage("Do you want to delete event: " + eventName.getText());
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    eventService.deleteEventId(eventId);
                    baseService = new BaseService(getActivity().getFragmentManager(),context);
                    baseService.replaceFragment(new ListEvent());
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
    }

    private void showDialogReport(){
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.report);
        dialog.setTitle("Add Report");

        final EditText reportTxt = (EditText)dialog.findViewById(R.id.report_value);
        Button add = (Button)dialog.findViewById(R.id.add_report);
        Button cancel = (Button) dialog.findViewById(R.id.cancel_report);
        final TextView valid = (TextView) dialog.findViewById(R.id.validator);

        reportTxt.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (reportTxt.getText().length() > 0)
                    valid.setText("");
            }

        });

        add.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       String reportValue = reportTxt.getText().toString();
                                       if (reportValue.length() == 0)
                                           valid.setText("Please enter report!");
               else {
                   Report report = new Report();

                   report.setCreateDate(new Date());
                   report.setDetail(reportValue);
                   report.setEventId(eventId);
                   report.setTitle("");

                   reportService.create(report);
                   adapter = new ReportListAdapter(context, reportService.getReports(eventId));
                   adapter.notifyDataSetChanged();
                   list.setAdapter(adapter);
                   setListViewHeightBasedOnChildren(list);
                   dialog.cancel();
               }}}
        );

        cancel.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          dialog.cancel();
                                      }
                                  }
        );

        dialog.show();

    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
