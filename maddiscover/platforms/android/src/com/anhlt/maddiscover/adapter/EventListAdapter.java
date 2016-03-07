package com.anhlt.maddiscover.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;

import com.anhlt.maddiscover.R;
import com.anhlt.maddiscover.entities.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anhlt on 2/26/16.
 */

public class EventListAdapter extends BaseAdapter implements Filterable {

    Context context;
    List<Event> events;
    List<Event> fixEventList;

    public EventListAdapter(Context context, List<Event> events) {
        this.context = context;
        fixEventList = events;
        this.events = events;
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Object getItem(int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return events.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_event_item, null);
        }

        CheckBox eventItem = (CheckBox) convertView.findViewById(R.id.event_item);
        eventItem.setText(events.get(position).getEventName());
        return convertView;

    }

    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                events = (List<Event>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                List<Event> filterEvent;

                if (constraint == null || constraint.length() == 0) {
                    results.count = fixEventList.size();
                    results.values = fixEventList;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    filterEvent = new ArrayList<Event>();
                    events = fixEventList;
                    for (int i = 0; i < events.size(); i++) {
                        Event event = events.get(i);
                        if (event.getEventName().toLowerCase().startsWith(constraint.toString()))  {
                            filterEvent.add(event);
                        }
                    }

                    results.count = filterEvent.size();
                    results.values = filterEvent;
                }

                return results;
            }
        };

        return filter;
    }
}

