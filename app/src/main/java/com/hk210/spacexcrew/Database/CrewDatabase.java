package com.hk210.spacexcrew.Database;

import android.content.Context;
import android.os.AsyncTask;
import android.os.strictmode.CredentialProtectedWhileLockedViolation;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.hk210.spacexcrew.Dao.CrewDao;
import com.hk210.spacexcrew.Model.Crew;

@Database(entities = {Crew.class}, version = 1)
public abstract class CrewDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "CrewDatabase";
    public abstract CrewDao crewDao();
    public static volatile CrewDatabase INSTANCE;
    public static CrewDatabase getINSTANCE(Context context){
        if(INSTANCE == null){
            synchronized (CrewDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context, CrewDatabase.class, DATABASE_NAME).addCallback(callback).build();
                }
            }
        }
        return INSTANCE;
    }
    static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsyncTask(INSTANCE);
        }
    };
    static class PopulateAsyncTask extends AsyncTask<Void, Void , Void>{
        private CrewDao crewDao;
        PopulateAsyncTask(CrewDatabase crewDatabase){
            crewDao = crewDatabase.crewDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            crewDao.deleteAll();
            return null;
        }
    }
}
