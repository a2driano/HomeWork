package ua.work.home.vrgcources.task.lesson1.view.presentation;

/**
 * Created by Andrii Papai on 05.11.2017.
 */

public interface DetailContract {

    interface ViewDetailContract {

        void saveEntry();

        void onSuccessCreate();

        void onSuccessUpdate();

        void onError();

    }

    interface PresenterDetailContract {

        void setEntry(int position, String header, String description);

    }
}
