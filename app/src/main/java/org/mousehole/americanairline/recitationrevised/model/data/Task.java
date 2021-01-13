package org.mousehole.americanairline.recitationrevised.model.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "task")
@ForeignKey(entity = HomeWorkDetails.class, parentColumns = {"homework_id"}, childColumns = {"homework_key"})
public class Task {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_id")
    long id;

    @ColumnInfo(name = "homework_key")
    long homework_key;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "task_completed")
    boolean completed;

    @Ignore
    public Task(String name, boolean completed) {
        this.name = name;
        this.completed = completed;
    }

    public Task(long id, long homework_key, String name, boolean completed) {
        this.id = id;
        this.homework_key = homework_key;
        this.name = name;
        this.completed = completed;
    }

    public long getId() {
        return id;
    }

    public long getHomework_key() {return homework_key; }

    public void setHomework_key(long homework_key) { this.homework_key = homework_key; };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String toString() {return "{ " + name + ", " + ", " + completed + "}";}
}
