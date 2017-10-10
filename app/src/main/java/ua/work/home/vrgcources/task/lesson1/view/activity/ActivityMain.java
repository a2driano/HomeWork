package ua.work.home.vrgcources.task.lesson1.view.activity;

import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ua.work.home.vrgcources.R;
import ua.work.home.vrgcources.task.lesson1.view.fragment.FragmentDetail;
import ua.work.home.vrgcources.task.lesson1.view.fragment.FragmentList;

public class ActivityMain extends AppCompatActivity implements FragmentDetail.OnDataUpgradeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragment();
    }


    private void addFragment() {
        FragmentManager manager = getSupportFragmentManager();


        FragmentList fragmentList = FragmentList.newInstance();
        manager.beginTransaction()
                .add(R.id.container_list, fragmentList, FragmentList.class.getSimpleName())
                .addToBackStack(FragmentList.class.getSimpleName())
                .commit();

        //check if FragmentDetail exist - remove
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            FragmentDetail fragmentDetailFind = (FragmentDetail) getSupportFragmentManager()
                    .findFragmentByTag(FragmentDetail.class.getSimpleName());
            if (fragmentDetailFind != null) {
                manager.beginTransaction()
                        .remove(fragmentDetailFind)
                        .commit();
            }
        }

        //if orientation Landscape add FragmentDetail to right side
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //empty fragment
            FragmentDetail fragmentDetail = FragmentDetail.newInstance(null, null, -1);
            manager.beginTransaction()
                    .add(R.id.container_detail, fragmentDetail, FragmentDetail.class.getSimpleName())
                    .addToBackStack(FragmentDetail.class.getSimpleName())
                    .commit();
        }
    }

    @Override
    public void onDataUpgrade(int position) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            FragmentList fragmentList = (FragmentList) getSupportFragmentManager()
                    .findFragmentByTag(FragmentList.class.getSimpleName());
            if (fragmentList != null) {
                fragmentList.updatePosition(position);
            }
        }
    }

//    @Override
//    public void onBackPressed() {
//        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
//                getSupportFragmentManager().popBackStack();
//            } else {
//                finish();
//            }
//        }
//    }
}
