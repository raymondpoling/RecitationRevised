package org.mousehole.americanairline.recitationrevised.presenter;

import android.content.Context;

import org.mousehole.americanairline.recitationrevised.model.data.Homework;
import org.mousehole.americanairline.recitationrevised.model.data.Task;

import java.util.List;

public interface TaskPresenterContract {
    interface TaskView {
        void displayTask(List<Task> taskList);
        Context getContext();
    }
    interface TaskPresenter {
        void updateTask(Task task);
        void getTasks(long taskId);
    }
}
