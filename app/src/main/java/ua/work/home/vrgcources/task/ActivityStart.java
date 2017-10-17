package ua.work.home.vrgcources.task;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import ua.work.home.vrgcources.R;
import ua.work.home.vrgcources.task.lesson1.view.activity.ActivityLesson1;
import ua.work.home.vrgcources.task.lesson2.view.activity.ActivityLesson2;

public class ActivityStart extends AppCompatActivity implements View.OnClickListener {

    FrameLayout mLesson1;
    FrameLayout mLesson2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mLesson1 = (FrameLayout) findViewById(R.id.lesson_1);
        mLesson1.setOnClickListener(this);
        mLesson2 = (FrameLayout) findViewById(R.id.lesson_2);
        mLesson2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lesson_1:
                startLesson(ActivityLesson1.class);
                break;
            case R.id.lesson_2:
                startLesson(ActivityLesson2.class);
                break;
        }
    }

    private void startLesson(Class activity) {
        Intent intent = new Intent(ActivityStart.this, activity);
        startActivity(intent);
    }
}
