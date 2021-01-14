package org.mousehole.americanairline.recitationrevised.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.EditText;

import org.mousehole.americanairline.recitationrevised.R;
import org.mousehole.americanairline.recitationrevised.model.data.Homework;
import org.mousehole.americanairline.recitationrevised.model.data.HomeworkDetails;
import org.mousehole.americanairline.recitationrevised.model.data.Task;
import org.mousehole.americanairline.recitationrevised.presenter.AddHomeworkPresenter;
import org.mousehole.americanairline.recitationrevised.presenter.AddHomeworkPresenterContractor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddHomeworkActivity extends AppCompatActivity implements AddHomeworkPresenterContractor.AddHomeworkView {

    @BindView(R.id.week_edittext)
    EditText weekEditText;

    @BindView(R.id.day_edittext)
    EditText dayEditText;

    @BindView(R.id.subject_edittext)
    EditText subjectEditText;

    @BindView(R.id.description_edittext)
    EditText descriptionEditText;

    @BindView(R.id.concepts_edittext)
    EditText conceptsEditText;

    @BindView(R.id.git_repository_edittext)
    EditText gitRepositoryEditText;

    @BindView(R.id.due_date_calendarview)
    CalendarView dueDateCalendarView;

    @BindView(R.id.tasks_edittext)
    EditText tasksEditText;

    AddHomeworkPresenterContractor.AddHomeworkPresenter addHomeworkPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_homework);

        ButterKnife.bind(this);

        addHomeworkPresenter = new AddHomeworkPresenter(this);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @OnClick(R.id.add_button)
    public void addOnClick() {
        new Thread(() -> {
            DateFormat df = new SimpleDateFormat("YYYY-mm-DD");
            int week = Integer.parseInt(weekEditText.getText().toString());
            int day = Integer.parseInt(dayEditText.getText().toString());
            String subject = subjectEditText.getText().toString();
            String concepts = conceptsEditText.getText().toString();
            String description = descriptionEditText.getText().toString();
            String repository = gitRepositoryEditText.getText().toString();
            Date dueDate =  new Date(dueDateCalendarView.getDate());
            List<String> taskList = Arrays.asList(tasksEditText.getText().toString().split(","));
            List<Task> trimmedTasks = new ArrayList<>(taskList.size());
            for(String s: taskList) {
                trimmedTasks.add(new Task(s.trim(), false));
            }
            HomeworkDetails hd = new HomeworkDetails(week, day, subject, description, concepts, repository, dueDate);

            Homework hw = new Homework(hd, trimmedTasks);
            addHomeworkPresenter.saveHomework(hw);
            finish();
        }).start();
    }
}