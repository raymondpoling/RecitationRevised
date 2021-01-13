package org.mousehole.americanairline.recitationrevised.model.data;

import androidx.room.Embedded;
import androidx.room.Ignore;
import androidx.room.Relation;

import java.util.List;

public class HomeWork {

    @Embedded
    private HomeWorkDetails homeWorkDetails;

    @Relation(entity = Task.class, parentColumn = "homework_id", entityColumn = "homework_key")
    private List<Task> taskList;

    public HomeWork(HomeWorkDetails homeWorkDetails) {
        this.homeWorkDetails = homeWorkDetails;
    }

    @Ignore
    public HomeWork(HomeWorkDetails homeWorkDetails, List<Task> taskList) {
        this.homeWorkDetails = homeWorkDetails;
        this.taskList = taskList;
    }

    public HomeWorkDetails getHomeWorkDetails() {
        return homeWorkDetails;
    }

    public void setHomeWorkDetails(HomeWorkDetails homeWorkDetails) {
        this.homeWorkDetails = homeWorkDetails;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(homeWorkDetails).append('\n');
        for(Task t: taskList) {
            sb.append(t.toString()).append('\n');
        }
        return sb.toString();

    }
}
