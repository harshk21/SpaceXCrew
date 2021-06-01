package com.hk210.spacexcrew.Repository;

import android.app.Application;
import android.os.AsyncTask;
import android.text.style.AlignmentSpan;

import androidx.lifecycle.LiveData;

import com.hk210.spacexcrew.Dao.CrewDao;
import com.hk210.spacexcrew.Database.CrewDatabase;
import com.hk210.spacexcrew.Model.Crew;

import java.util.List;

public class CrewRepository {
    private CrewDatabase database;
    private LiveData<List<Crew>> getAllData;

    public CrewRepository(Application application){
        database = CrewDatabase.getINSTANCE(application);
        getAllData = database.crewDao().getAllCrew();
    }
    public void insert(List<Crew> list){
        new InsertAsyncTask(database).execute(list);

    }
    public void delete(List<Crew> list){
        new DeleteAsyncTask(database).execute(list);
    }
    public LiveData<List<Crew>> getAllData(){
        return getAllData;
    }
    static class InsertAsyncTask extends AsyncTask<List<Crew>, Void, Void>{
        private CrewDao crewDao;
        InsertAsyncTask(CrewDatabase crewDatabase){
            crewDao = crewDatabase.crewDao();
        }

        @Override
        protected Void doInBackground(List<Crew>... lists) {
            crewDao.insert(lists[0]);
            return null;
        }

    }
    static class DeleteAsyncTask extends AsyncTask<List<Crew>, Void, Void>{
        private CrewDao crewDao;
        DeleteAsyncTask(CrewDatabase crewDatabase){
            crewDao = crewDatabase.crewDao();

        }

        @Override
        protected Void doInBackground(List<Crew>... lists) {
            crewDao.deleteAll();
            return null;
        }
    }
}
