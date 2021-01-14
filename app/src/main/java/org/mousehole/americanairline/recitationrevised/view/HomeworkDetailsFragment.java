package org.mousehole.americanairline.recitationrevised.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.mousehole.americanairline.recitationrevised.R;
import org.mousehole.americanairline.recitationrevised.model.data.Homework;
import org.mousehole.americanairline.recitationrevised.model.data.HomeworkDetails;
import org.mousehole.americanairline.recitationrevised.presenter.HomeworkDetailsPresenterContract;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeworkDetailsFragment extends Fragment implements HomeworkDetailsPresenterContract.HomeworkDetailsView {

    @BindView(R.id.week_textview)
    TextView weekTextView;

    @BindView(R.id.day_textview)
    TextView dayTextView;

    @BindView(R.id.subject_textview)
    TextView subjectTextView;

    @BindView(R.id.concepts_textview)
    TextView conceptsTextView;

    @BindView(R.id.due_date_textview)
    TextView dueDateTextView;

    @BindView(R.id.description_textview)
    TextView descriptionTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.homework_details_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
    }

    @Override
    public void displayHomeworkDetails(HomeworkDetails homeworkDetails) {
        weekTextView.setText("Week " + homeworkDetails.getWeek());
        dayTextView.setText("Day " + homeworkDetails.getDay());
        subjectTextView.setText(homeworkDetails.getSubject());
        conceptsTextView.setText(homeworkDetails.getConcepts());
        dueDateTextView.setText(homeworkDetails.getDueDate().toString());
        descriptionTextView.setText(homeworkDetails.getDescription());
    }

}
