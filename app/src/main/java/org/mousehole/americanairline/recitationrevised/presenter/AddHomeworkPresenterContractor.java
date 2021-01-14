package org.mousehole.americanairline.recitationrevised.presenter;

import android.content.Context;

import org.mousehole.americanairline.recitationrevised.model.data.Homework;

public interface AddHomeworkPresenterContractor {
    interface AddHomeworkView {
        Context getContext();
    }
    interface AddHomeworkPresenter {
        void saveHomework(Homework homework);
    }
}
