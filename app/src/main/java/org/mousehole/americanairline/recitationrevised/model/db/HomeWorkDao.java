package org.mousehole.americanairline.recitationrevised.model.db;

import androidx.room.Dao;
import androidx.room.Index;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import org.mousehole.americanairline.recitationrevised.model.data.HomeWork;
import org.mousehole.americanairline.recitationrevised.model.data.HomeWorkDetails;
import org.mousehole.americanairline.recitationrevised.model.data.Task;

import java.util.List;

@Dao
public abstract class HomeWorkDao {

    @Transaction
    @Query("SELECT * FROM homework ORDER BY week, day ASC")
    public abstract List<HomeWork> getHomeWorks();

    @Insert()
    public abstract Long addHomeWorkDetails(HomeWorkDetails homeWorkDetails);

    @Insert()
    public abstract void addTasks(Task... tasks);

    @Transaction
    public void addHomeWork(HomeWork... homeWorks) {
        for(HomeWork hw: homeWorks) {
            HomeWorkDetails hwd = hw.getHomeWorkDetails();
            Long id = addHomeWorkDetails(hwd);
            for(Task t: hw.getTaskList()) {
                t.setHomework_key(id);
                addTasks(t);
            }

        }
    }

    @Update
    public abstract void updateHomeWorkDetails(HomeWorkDetails homeWorkDetails);

    @Transaction
    public void updateHomeWork(HomeWork homeWork) {
        updateHomeWorkDetails(homeWork.getHomeWorkDetails());
        long id = homeWork.getHomeWorkDetails().getId();
        for(Task t: homeWork.getTaskList()) {
            t.setHomework_key(id);
            destructiveInsertTask(t);
        }
    }

    /*
     * Kludgy. We don't need to preserve ids, since they aren't foreign keys,
     * so we can do this as an updateOrInsert operation
    */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void destructiveInsertTask(Task task);

}
