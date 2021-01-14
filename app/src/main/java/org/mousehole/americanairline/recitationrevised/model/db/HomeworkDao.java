package org.mousehole.americanairline.recitationrevised.model.db;

import androidx.room.Dao;
import androidx.room.Index;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import org.mousehole.americanairline.recitationrevised.model.data.Homework;
import org.mousehole.americanairline.recitationrevised.model.data.HomeworkDetails;
import org.mousehole.americanairline.recitationrevised.model.data.Task;

import java.util.List;

@Dao
public abstract class HomeworkDao {

    @Transaction
    @Query("SELECT * FROM homework ORDER BY week DESC, day DESC")
    public abstract List<Homework> getHomeworks();

    @Insert()
    public abstract Long addHomeworkDetails(HomeworkDetails homeworkDetails);

    @Insert()
    public abstract void addTasks(Task... tasks);

    @Transaction
    public void addHomework(Homework... homeworks) {
        for(Homework hw: homeworks) {
            HomeworkDetails hwd = hw.getHomeworkDetails();
            Long id = addHomeworkDetails(hwd);
            for(Task t: hw.getTaskList()) {
                t.setHomework_key(id);
                addTasks(t);
            }

        }
    }

    @Update
    public abstract void updateHomeworkDetails(HomeworkDetails homeworkDetails);

    @Transaction
    public void updateHomework(Homework homework) {
        updateHomeworkDetails(homework.getHomeworkDetails());
        long id = homework.getHomeworkDetails().getId();
        for(Task t: homework.getTaskList()) {
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

    @Query("SELECT * FROM task WHERE homework_key = :homeworkId")
    public abstract List<Task> getTasks(long homeworkId);

    @Update
    public abstract void updateTask(Task task);

}
