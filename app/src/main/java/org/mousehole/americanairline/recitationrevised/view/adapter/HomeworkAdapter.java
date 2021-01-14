package org.mousehole.americanairline.recitationrevised.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.mousehole.americanairline.recitationrevised.R;
import org.mousehole.americanairline.recitationrevised.model.data.Homework;
import org.mousehole.americanairline.recitationrevised.model.data.HomeworkDetails;
import org.mousehole.americanairline.recitationrevised.model.data.Task;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeworkAdapter extends RecyclerView.Adapter<HomeworkAdapter.HomeworkViewHolder> {

    public List<Homework> homeworkList;

    private Fragment fragment;

    public interface HomeworkDelegate {
        void selectHomework(Homework homework);
    }


    public HomeworkAdapter(List<Homework> homeworkList, Fragment fragment) {
        this.homeworkList = homeworkList;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public HomeworkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.homework_item_layout, parent, false);
        return new HomeworkViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeworkViewHolder holder, int position) {
        Homework homework = homeworkList.get(position);
        HomeworkDetails homeworkDetails = homework.getHomeworkDetails();
        boolean completed = true;
        for(Task t: homework.getTaskList()) {
            completed &= t.isCompleted();
        }
        holder.weekDayComplete.setText(String.format("Week %d day %d is %s", homeworkDetails.getWeek(),
                homeworkDetails.getDay(),
                Boolean.toString(completed)));
        holder.homeworkCardView.setOnClickListener((v) -> {
            ((HomeworkDelegate)fragment.getActivity()).selectHomework(homework);
        });
    }

    public void updateList(List<Homework> homeworkList) {
        this.homeworkList = homeworkList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return homeworkList.size();
    }

    public class HomeworkViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.week_day_completed)
        TextView weekDayComplete;

        @BindView(R.id.homework_cardview)
        CardView homeworkCardView;

        public HomeworkViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
