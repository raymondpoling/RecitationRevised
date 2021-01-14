package org.mousehole.americanairline.recitationrevised.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import org.mousehole.americanairline.recitationrevised.R;
import org.mousehole.americanairline.recitationrevised.model.data.Homework;
import org.mousehole.americanairline.recitationrevised.model.data.HomeworkDetails;
import org.mousehole.americanairline.recitationrevised.model.data.Task;
import org.mousehole.americanairline.recitationrevised.model.db.HomeworkDao;
import org.mousehole.americanairline.recitationrevised.model.db.HomeworkDatabase;
import org.mousehole.americanairline.recitationrevised.presenter.HomeworkListPresenter;
import org.mousehole.americanairline.recitationrevised.presenter.HomeworkListPresenterContract;
import org.mousehole.americanairline.recitationrevised.presenter.TaskPresenter;
import org.mousehole.americanairline.recitationrevised.view.adapter.HomeworkAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements HomeworkAdapter.HomeworkDelegate {

    public static Integer ADD_CODE = 707;

    private HomeworkListPresenterContract.HomeworkListPresenter homeworkListPresenter;

    private HomeworkListFragment homeworkListFragment;

    private HomeworkDetailsFragment homeworkDetailsFragment = new HomeworkDetailsFragment();

    private TaskPresenter taskPresenter;

    private TaskFragment taskFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

//        makeTestData();


        homeworkListFragment = (HomeworkListFragment) getSupportFragmentManager().findFragmentById(R.id.homework_list_fragment);
        homeworkListPresenter = new HomeworkListPresenter(homeworkListFragment);

        taskFragment = (TaskFragment) getSupportFragmentManager().findFragmentById(R.id.tasks_fragment);
        taskPresenter = new TaskPresenter(taskFragment);

        homeworkDetailsFragment = (HomeworkDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.details_fragment);

        Log.d("TAGX", "Starting details display");
        homeworkListPresenter.getAllHomework();

    }

    private void makeTestData() {
        new Thread(() -> {
            HomeworkDetails hd = new HomeworkDetails(3, 1, "Room and Frame and more oh my", "Let's stay here", "Frames, Recycler, etc", "RecitationRevised", new Date());
            List<Task> tasks = new ArrayList<>(3);
            tasks.add(new Task("Recycler view", false));
            tasks.add(new Task("Fragments", false));
            tasks.add(new Task("Room DB", false));

            Homework hw = new Homework(hd, tasks);

            HomeworkDao dao = Room.databaseBuilder(this, HomeworkDatabase.class, HomeworkDatabase.DATABASE_NAME).build().getHomeworkDao();
            dao.addHomework(hw);
        }).start();
    }

    @OnClick(R.id.add_button)
    public void addButton() {
        Log.d("TAGX", "Click!");
        Intent intent = new Intent(this, AddHomeworkActivity.class);
        startActivityForResult(intent, ADD_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_CODE) {
            homeworkListPresenter.getAllHomework();
        }
    }

    @Override
    public void selectHomework(Homework homework) {
        homeworkDetailsFragment.displayHomeworkDetails(homework.getHomeworkDetails());
        taskFragment.displayTask(homework.getTaskList());
    }

    public TaskPresenter getTaskPresenter() {
        return taskPresenter;
    }
}