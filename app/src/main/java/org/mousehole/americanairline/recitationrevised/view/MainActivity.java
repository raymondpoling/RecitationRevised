package org.mousehole.americanairline.recitationrevised.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import org.mousehole.americanairline.recitationrevised.R;
import org.mousehole.americanairline.recitationrevised.model.data.HomeWork;
import org.mousehole.americanairline.recitationrevised.model.data.HomeWorkDetails;
import org.mousehole.americanairline.recitationrevised.model.data.Task;
import org.mousehole.americanairline.recitationrevised.model.db.HomeWorkDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HomeWorkDatabase homeWorkDatabase = Room.databaseBuilder(this,
                HomeWorkDatabase.class,
                HomeWorkDatabase.DATABASE_NAME).build();

        List<Task> tasks = new ArrayList<>(3);
        Task one = new Task("Use Fragments", false);
        Task two = new Task("Use ROOM", false);
        Task three = new Task("Use Fragments", false);
        tasks.add(one);
        tasks.add(two);
        tasks.add(three);

        HomeWorkDetails hd = new HomeWorkDetails(3,2,"Items", "", new ArrayList<>(), "http://", new Date());
        new Thread(() -> {
            homeWorkDatabase.getHomeWorkDao().addHomeWork(new HomeWork(hd, tasks));

            for (HomeWork hw : homeWorkDatabase.getHomeWorkDao().getHomeWorks()) {
                Log.d("HomeWorks", hw.toString());
            }
        }).start();
    }
}