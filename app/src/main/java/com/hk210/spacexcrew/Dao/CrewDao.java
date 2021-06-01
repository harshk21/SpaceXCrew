package com.hk210.spacexcrew.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.hk210.spacexcrew.Model.Crew;

import java.util.List;

@Dao
public interface CrewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Crew> crewList);
    @Query("SELECT * FROM crew_members")
    LiveData<List<Crew>> getAllCrew();

    @Query("DELETE FROM crew_members")
    void deleteAll();
}
