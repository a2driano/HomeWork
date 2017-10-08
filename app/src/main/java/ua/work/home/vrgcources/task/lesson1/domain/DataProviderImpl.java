package ua.work.home.vrgcources.task.lesson1.domain;

import java.util.List;

import ua.work.home.vrgcources.task.lesson1.data.repository.DataLab;
import ua.work.home.vrgcources.task.lesson1.data.model.EntryModel;

/**
 * Created by Andrii Papai on 07.10.2017.
 */

public class DataProviderImpl implements DataProvider {
    private DataLab mDataLab;

    public DataProviderImpl() {
        mDataLab = new DataLab();
    }

    @Override
    public List<EntryModel> getListData() {
        return mDataLab.getDataList();
    }

    @Override
    public EntryModel getEntry(int position) {
        return mDataLab.getEntry(position);
    }

    @Override
    public void addData(EntryModel model) {
        mDataLab.addData(model);
    }

    @Override
    public void updateData(EntryModel model, int position) {
        mDataLab.updateData(model, position);
    }
}
