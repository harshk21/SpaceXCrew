package com.hk210.spacexcrew.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.hk210.spacexcrew.Model.Crew;
import com.hk210.spacexcrew.Repository.CrewRepository;

import java.util.List;

public class CrewViewModel extends AndroidViewModel {

    private CrewRepository crewRepository;
    private LiveData<List<Crew>> getAllCrew;

    public CrewViewModel(@NonNull Application application) {
        super(application);
        crewRepository = new CrewRepository(application);
        getAllCrew = crewRepository.getAllData();
    }
    public void insert(List<Crew> list){
        crewRepository.insert(list);
    }
    public void delete(List<Crew> list){
        crewRepository.delete(list);
    }
    public LiveData<List<Crew>> getAllCrew(){
        return getAllCrew;
    }
}
