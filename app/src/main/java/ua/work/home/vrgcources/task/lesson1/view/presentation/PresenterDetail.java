package ua.work.home.vrgcources.task.lesson1.view.presentation;

import android.content.Context;
import ua.work.home.vrgcources.task.App;
import ua.work.home.vrgcources.task.data.model.EntryModel;

/**
 * Created by Andrii Papai on 05.11.2017.
 */

public class PresenterDetail implements DetailContract.PresenterDetailContract {
    private Context mContext;
    private DetailContract.ViewDetailContract IView;

    public PresenterDetail(Context context, DetailContract.ViewDetailContract IView) {
        mContext = context;
        this.IView = IView;
    }

    @Override
    public void setEntry(int position, String header, String description) {
        if (header.matches("") || description.matches("")) {
            IView.onError();

        } else {
            EntryModel entry;
            entry = new EntryModel(header, description);
            if (position != -1) {
                //Edit exist Entry
                App.getDataProvider().updateData(entry, position);
                IView.onSuccessUpdate();
            } else {
                //save new Entry to storage
                //detect position for new Entry (position == size because position start from 0)
                position = App.getDataProvider().getListData().size();
                App.getDataProvider().addData(entry);
                IView.onSuccessCreate();
            }

        }
    }
}
