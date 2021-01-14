package org.mousehole.americanairline.recitationrevised.presenter;

import android.content.Context;

import org.mousehole.americanairline.recitationrevised.model.data.Homework;

import java.util.List;

public interface HomeworkListPresenterContract {
    interface HomeworkListView {
        void displayList(List<Homework> homeworkList);
        Context getContext();
    }
    interface HomeworkListPresenter {
        void getAllHomework();
    }
}
