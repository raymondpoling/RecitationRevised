package org.mousehole.americanairline.recitationrevised.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.mousehole.americanairline.recitationrevised.R;
import org.mousehole.americanairline.recitationrevised.model.data.Homework;
import org.mousehole.americanairline.recitationrevised.presenter.HomeworkListPresenterContract;
import org.mousehole.americanairline.recitationrevised.view.adapter.HomeworkAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeworkListFragment extends Fragment implements HomeworkListPresenterContract.HomeworkListView {

    @BindView(R.id.homework_list_recyclerview)
    RecyclerView recyclerView;

    HomeworkAdapter homeworkAdapter = new HomeworkAdapter(new ArrayList<>(0), this);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.homework_list_frame_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        recyclerView.setAdapter(homeworkAdapter);
    }

    @Override
    public void displayList(List<Homework> homeworkList) {
        getActivity().runOnUiThread(() -> {
            homeworkAdapter.updateList(homeworkList);
        });
    }

}
