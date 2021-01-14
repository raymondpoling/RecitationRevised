package org.mousehole.americanairline.recitationrevised.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.mousehole.americanairline.recitationrevised.R;
import org.mousehole.americanairline.recitationrevised.model.data.Task;
import org.mousehole.americanairline.recitationrevised.presenter.TaskPresenterContract;
import org.mousehole.americanairline.recitationrevised.view.adapter.TaskAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskFragment extends Fragment implements TaskPresenterContract.TaskView {

    @BindView(R.id.task_list_recyclerview)
    RecyclerView taskListRecyclerView;

    TaskAdapter taskAdapter = new TaskAdapter(new ArrayList<>(0), this);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.task_list_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        taskListRecyclerView.setAdapter(taskAdapter);
    }

    @Override
    public void displayTask(List<Task> taskList) {
        getActivity().runOnUiThread(() -> {
            taskAdapter.updateTasks(taskList);
        });
    }

}
