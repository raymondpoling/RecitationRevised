package org.mousehole.americanairline.recitationrevised.model.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import org.mousehole.americanairline.recitationrevised.model.data.HomeworkDetails;
import org.mousehole.americanairline.recitationrevised.model.data.Task;


@Database(version = 1, entities = {HomeworkDetails.class, Task.class}, exportSchema = false)
public abstract class HomeworkDatabase extends RoomDatabase {

    public final static String DATABASE_NAME = "homework.db";

    public abstract HomeworkDao getHomeworkDao();
}
