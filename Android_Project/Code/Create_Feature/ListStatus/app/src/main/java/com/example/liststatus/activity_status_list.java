package com.example.liststatus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class activity_status_list extends AppCompatActivity {
    List<Tag> tagList;
    List<Tag> tagList_0;
    List<Tag> tagList_1;
    List<Tag> tagList_2;
    List<Tag> tagList_3;
    TextView tvUnscheduledNumber, tvInProgressNumber, tvCompletedNumber, tvOverdueNumber;
    private RecyclerView rvUnscheduled,rvInProgress ,rvCompleted , rvOverdue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_list);
        findViewbyIds();
        initTagList();
        splitTagListByStatus();
        displayTagNumbers();
        setRecyclerViews();
    }

    void findViewbyIds()
    {
        rvUnscheduled = findViewById(R.id.rv_unscheduled);
        rvInProgress = findViewById(R.id.rv_in_process);
        rvCompleted = findViewById(R.id.rv_completed);
        rvOverdue = findViewById(R.id.rv_overdue);
        tvUnscheduledNumber = findViewById(R.id.tv_unscheduled_number);
        tvInProgressNumber = findViewById(R.id.tv_in_process_number);
        tvCompletedNumber = findViewById(R.id.tv_completed_number);
        tvOverdueNumber = findViewById(R.id.tv_overdue_number);
    }



    void initTagList()
    {
        tagList = new ArrayList<>();

        // Thêm 20 đối tượng Tag vào danh sách với các giá trị status khác nhau
        tagList.add(new Tag(1, "Work", 1)); // Đang thực hiện
        tagList.add(new Tag(2, "Personal", 0)); // Chưa đặt lịch
        tagList.add(new Tag(3, "Fitness", 2)); // Đã hoàn thành
        tagList.add(new Tag(4, "Study", 3)); // Trễ hạn
        tagList.add(new Tag(5, "Shopping", 1)); // Đang thực hiện
        tagList.add(new Tag(6, "Cooking", 2)); // Đã hoàn thành
        tagList.add(new Tag(7, "Gardening", 0)); // Chưa đặt lịch
        tagList.add(new Tag(8, "Meeting", 3)); // Trễ hạn
        tagList.add(new Tag(9, "Gym", 1)); // Đang thực hiện
        tagList.add(new Tag(10, "Reading", 2)); // Đã hoàn thành
        tagList.add(new Tag(11, "Running", 0)); // Chưa đặt lịch
        tagList.add(new Tag(12, "Coding", 3)); // Trễ hạn
        tagList.add(new Tag(13, "Writing", 1)); // Đang thực hiện
        tagList.add(new Tag(14, "Drawing", 2)); // Đã hoàn thành
        tagList.add(new Tag(15, "Singing", 0)); // Chưa đặt lịch
        tagList.add(new Tag(16, "Dancing", 3)); // Trễ hạn
        tagList.add(new Tag(17, "Volunteering", 1)); // Đang thực hiện
        tagList.add(new Tag(18, "Programming", 2)); // Đã hoàn thành
        tagList.add(new Tag(19, "Exploring", 0)); // Chưa đặt lịch
        tagList.add(new Tag(20, "Traveling", 3)); // Trễ hạn
        tagList.add(new Tag(21, "Dancing", 3)); // Trễ hạn
        tagList.add(new Tag(22, "Volunteering", 0)); // Đang thực hiện
        tagList.add(new Tag(23, "Programming", 0)); // Đã hoàn thành
        tagList.add(new Tag(24, "Exploring", 0)); // Chưa đặt lịch
        tagList.add(new Tag(25, "Traveling", 0)); // Trễ hạn
    }

    void splitTagListByStatus() {
        tagList_0 = new ArrayList<>();
        tagList_1 = new ArrayList<>();
        tagList_2 = new ArrayList<>();
        tagList_3 = new ArrayList<>();

        for (Tag tag : tagList) {
            switch (tag.status) {
                case 0:
                    tagList_0.add(tag);
                    break;
                case 1:
                    tagList_1.add(tag);
                    break;
                case 2:
                    tagList_2.add(tag);
                    break;
                case 3:
                    tagList_3.add(tag);
                    break;
                default:

                    break;
            }
        }


    }
    void displayTagNumbers() {
        // Hiển thị số lượng tag cho từng trạng thái vào các TextViews tương ứng
        tvUnscheduledNumber.setText("(" + tagList_0.size() + ")");
        tvInProgressNumber.setText("(" + tagList_1.size() + ")");
        tvCompletedNumber.setText("(" + tagList_2.size() + ")");
        tvOverdueNumber.setText("(" + tagList_3.size() + ")");
    }
    void setRecyclerViews() {
        TagAdapter unscheduledAdapter = new TagAdapter(tagList_0);
        rvUnscheduled.setLayoutManager(new LinearLayoutManager(this));
        rvUnscheduled.setAdapter(unscheduledAdapter);

        TagAdapter inProgressAdapter = new TagAdapter(tagList_1);
        rvInProgress.setLayoutManager(new LinearLayoutManager(this));
        rvInProgress.setAdapter(inProgressAdapter);

        TagAdapter completedAdapter = new TagAdapter(tagList_2);
        rvCompleted.setLayoutManager(new LinearLayoutManager(this));
        rvCompleted.setAdapter(completedAdapter);

        TagAdapter overdueAdapter = new TagAdapter(tagList_3);
        rvOverdue.setLayoutManager(new LinearLayoutManager(this));
        rvOverdue.setAdapter(overdueAdapter);
    }

}