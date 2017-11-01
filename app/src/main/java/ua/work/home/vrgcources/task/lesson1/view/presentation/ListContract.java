package ua.work.home.vrgcources.task.lesson1.view.presentation;

import ua.work.home.vrgcources.task.lesson1.view.adapter.EntryAdapter;

/**
 * Created by kunde on 01.11.2017.
 */

public interface ListContract {
    interface ViewList {

        void setData(EntryAdapter adapter);
    }

    interface PresenterList {
      void getData();
    }
}
