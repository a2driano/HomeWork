package ua.work.home.vrgcources.task;

import android.app.Application;

import ua.work.home.vrgcources.task.domain.DataProvider;
import ua.work.home.vrgcources.task.domain.DataProviderImpl;
import ua.work.home.vrgcources.task.lesson2.view.utils.ItemManager;


/**
 * Created by Andrii Papai on 07.10.2017.
 */

public class App extends Application {
    private static DataProvider sDataProvider;
    private static ItemManager sItemManager;

    @Override
    public void onCreate() {
        super.onCreate();

        sDataProvider = new DataProviderImpl();
        sItemManager = new ItemManager();
    }

    public static DataProvider getDataProvider() {
        return sDataProvider;
    }

    public static ItemManager getItemMabager() {
        return sItemManager;
    }
}
