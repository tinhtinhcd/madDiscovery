package com.anhlt.maddiscover.services;

import android.app.FragmentManager;
import android.content.Context;

import com.anhlt.maddiscover.fragments.organizer.CreateOrganizer;
import com.anhlt.maddiscover.entities.Organizer;
import com.anhlt.maddiscover.data.repositories.OrganizerRepository;

import java.util.List;

/**
 * Created by anhlt on 3/5/16.F
 */
public class OrganizerService {

    Context context;
    OrganizerRepository organizerRepository;
    BaseService baseService;

    public OrganizerService(Context context) {
        this.context = context;
        organizerRepository = new OrganizerRepository(context);
    }

    public Organizer findById(long id){
        return organizerRepository.findById(id);
    }

    public Organizer findByName(String name){
        return organizerRepository.findByName(name);
    }

    public List<Organizer> getOrganizerList(){
        return organizerRepository.getAllOrganizer();
    }

    public void createOrganizerForm(FragmentManager fm, Context context){
        baseService = new BaseService(fm,context);
        baseService.replaceFragment(CreateOrganizer.getInstance());
    }

    public void saveNewOrganizer(Organizer organizer){
        organizerRepository.create(organizer);
    }

    public void editOrganizer(){
    }

    public void deleteOrganizer(){
    }

    public void searchOrganizer(){
    }

    public List<String> listOrganizerName(){
        return organizerRepository.getListOrganizerName();
    }
}
