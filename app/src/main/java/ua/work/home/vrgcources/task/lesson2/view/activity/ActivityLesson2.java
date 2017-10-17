package ua.work.home.vrgcources.task.lesson2.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import ua.work.home.vrgcources.R;
import ua.work.home.vrgcources.task.lesson2.view.fragment.FragmentList;

public class ActivityLesson2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_2);
        addFragment();
    }


    private void addFragment() {
        FragmentManager manager = getSupportFragmentManager();


        FragmentList fragmentList = FragmentList.newInstance();
        manager.beginTransaction()
                .add(R.id.container_list, fragmentList, FragmentList.class.getSimpleName())
                .commit();
    }
}
