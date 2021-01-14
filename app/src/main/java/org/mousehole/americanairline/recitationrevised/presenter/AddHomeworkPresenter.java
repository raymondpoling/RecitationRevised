package org.mousehole.americanairline.recitationrevised.presenter;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.mousehole.americanairline.recitationrevised.model.data.Homework;
import org.mousehole.americanairline.recitationrevised.model.db.HomeworkDatabase;

public class AddHomeworkPresenter implements AddHomeworkPresenterContractor.AddHomeworkPresenter {

    private AddHomeworkPresenterContractor.AddHomeworkView addHomeworkView;
    private HomeworkDatabase homeworkDatabase;

    public AddHomeworkPresenter(AddHomeworkPresenterContractor.AddHomeworkView addHomeworkView) {
        this.addHomeworkView = addHomeworkView;
        homeworkDatabase = Room.databaseBuilder(addHomeworkView.getContext(),
                HomeworkDatabase.class,
                HomeworkDatabase.DATABASE_NAME).build();
    }

    @Override
    public void saveHomework(Homework homework) {
        homeworkDatabase.getHomeworkDao().addHomework(homework);
    }
}
