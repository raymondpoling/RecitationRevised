package org.mousehole.americanairline.recitationrevised.presenter;

import androidx.room.Room;

import org.mousehole.americanairline.recitationrevised.model.data.Homework;
import org.mousehole.americanairline.recitationrevised.model.data.Task;
import org.mousehole.americanairline.recitationrevised.model.db.HomeworkDatabase;

import java.util.List;

public class TaskPresenter implements TaskPresenterContract.TaskPresenter {

    private TaskPresenterContract.TaskView taskView;

    private HomeworkDatabase homeworkDatabase;

    public TaskPresenter(TaskPresenterContract.TaskView taskView) {
        homeworkDatabase = Room.databaseBuilder(taskView.getContext(),
                HomeworkDatabase.class,
                HomeworkDatabase.DATABASE_NAME).build();
        this.taskView = taskView;
    }

    @Override
    public void getTasks(long homeworkId) {
        taskView.displayTask(homeworkDatabase.getHomeworkDao().getTasks(homeworkId));
    }

    @Override
    public void updateTask(Task task) {
        new Thread(() -> {
            homeworkDatabase.getHomeworkDao().updateTask(task);
            getTasks(task.getHomework_key());
        }).start();
    }

}
