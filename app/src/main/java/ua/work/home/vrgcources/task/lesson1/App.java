package ua.work.home.vrgcources.task.lesson1;

import android.app.Application;

import ua.work.home.vrgcources.task.lesson1.domain.DataProvider;
import ua.work.home.vrgcources.task.lesson1.domain.DataProviderImpl;

/**
 * Created by Andrii Papai on 07.10.2017.
 */

public class App extends Application {
    private static DataProvider mDataProvider;

    @Override
    public void onCreate() {
        super.onCreate();

        mDataProvider = new DataProviderImpl();
    }

    public static DataProvider getDataProvider() {
        return mDataProvider;
    }
}
