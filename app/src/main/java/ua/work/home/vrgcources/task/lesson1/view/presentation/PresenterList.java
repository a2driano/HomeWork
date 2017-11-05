package ua.work.home.vrgcources.task.lesson1.view.presentation;

import android.content.Context;

import java.util.List;

import ua.work.home.vrgcources.task.App;
import ua.work.home.vrgcources.task.data.model.EntryModel;
import ua.work.home.vrgcources.task.lesson1.view.adapter.EntryAdapter;

/**
 * Created by kunde on 01.11.2017.
 */

public class PresenterList implements ListContract.PresenterList {
    private Context mContext;
    private EntryAdapter mAdapter;
    private ListContract.ViewList viewList;

    public PresenterList(Context context, ListContract.ViewList viewList) {
        mContext = context;
        this.viewList = viewList;
    }

    @Override
    public void getData() {
        List<EntryModel> models = App.getDataProvider().getListData();
        mAdapter = new EntryAdapter(models, mContext);
        viewList.setData(mAdapter);
    }
}
