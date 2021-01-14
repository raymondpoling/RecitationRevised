package org.mousehole.americanairline.recitationrevised.model.data;

import androidx.room.Embedded;
import androidx.room.Ignore;
import androidx.room.Relation;

import java.util.List;

public class Homework {

    @Embedded
    private HomeworkDetails homeworkDetails;

    @Relation(entity = Task.class, parentColumn = "homework_id", entityColumn = "homework_key")
    private List<Task> taskList;

    public Homework(HomeworkDetails homeworkDetails) {
        this.homeworkDetails = homeworkDetails;
    }

    @Ignore
    public Homework(HomeworkDetails homeworkDetails, List<Task> taskList) {
        this.homeworkDetails = homeworkDetails;
        this.taskList = taskList;
    }

    public HomeworkDetails getHomeworkDetails() {
        return homeworkDetails;
    }

    public void setHomeworkDetails(HomeworkDetails homeworkDetails) {
        this.homeworkDetails = homeworkDetails;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(homeworkDetails).append('\n');
        for(Task t: taskList) {
            sb.append(t.toString()).append('\n');
        }
        return sb.toString();

    }
}
