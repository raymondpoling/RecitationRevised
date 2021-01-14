package org.mousehole.americanairline.recitationrevised.presenter;

import android.content.Context;

import org.mousehole.americanairline.recitationrevised.model.data.Homework;
import org.mousehole.americanairline.recitationrevised.model.data.HomeworkDetails;

public interface HomeworkDetailsPresenterContract {
    interface HomeworkDetailsView {
        void displayHomeworkDetails(HomeworkDetails homeworkDetails);
        Context getContext();
    }
}
