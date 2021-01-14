package org.mousehole.americanairline.recitationrevised.presenter;

import androidx.room.Room;

import org.mousehole.americanairline.recitationrevised.model.data.Homework;
import org.mousehole.americanairline.recitationrevised.model.db.HomeworkDatabase;

import java.util.List;

public class HomeworkListPresenter implements HomeworkListPresenterContract.HomeworkListPresenter {

    HomeworkDatabase homeworkDatabase;

    HomeworkListPresenterContract.HomeworkListView homeworkListView;

    public HomeworkListPresenter(HomeworkListPresenterContract.HomeworkListView homeworkListView) {
        homeworkDatabase = Room.databaseBuilder(homeworkListView.getContext(),
                HomeworkDatabase.class,
                HomeworkDatabase.DATABASE_NAME).build();
        this.homeworkListView = homeworkListView;
    }

    @Override
    public void getAllHomework() {
        new Thread(() -> {
            List<Homework> homeworkList = homeworkDatabase.getHomeworkDao().getHomeworks();
            homeworkListView.displayList(homeworkList);
        }).start();
    }
}
