package org.mousehole.americanairline.recitationrevised.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.mousehole.americanairline.recitationrevised.R;
import org.mousehole.americanairline.recitationrevised.model.data.Task;
import org.mousehole.americanairline.recitationrevised.presenter.TaskPresenterContract;
import org.mousehole.americanairline.recitationrevised.view.MainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> taskList;
    private Fragment fragment;

    public TaskAdapter(List<Task> taskList, Fragment fragment) {
        this.taskList = taskList;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item_layout, parent, false);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.taskTextView.setText(task.getName());
        holder.completedCheckbox.setChecked(task.isCompleted());

        holder.completedCheckbox.setOnClickListener((v) -> {
            task.setCompleted(!task.isCompleted());
            ((MainActivity)fragment.getActivity()).getTaskPresenter().updateTask(task);
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.task_textview)
        TextView taskTextView;

        @BindView(R.id.completed_checkbox)
        CheckBox completedCheckbox;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

    public void updateTasks(List<Task> taskList) {
        this.taskList = taskList;
        notifyDataSetChanged();
    }

}
