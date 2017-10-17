package ua.work.home.vrgcources.task.lesson2.view.utils;

import java.util.ArrayList;
import java.util.List;

import ua.work.home.vrgcources.task.App;
import ua.work.home.vrgcources.task.data.model.EntryModel;

/**
 * Created by Andrii Papai on 17.10.2017.
 */
public class ItemManager {
    private List<EntryModel> mModelList;
    private List<EntryModel> mCurrentList;
    private int mCurrentPosition;

    public ItemManager() {
        mModelList = App.getDataProvider().getListData();
        mCurrentList = new ArrayList<>();
        mCurrentPosition = 0;
    }

    //return current List of elements
    public List<EntryModel> getCurrentList() {
        if (mCurrentPosition >= mModelList.size())
            return mModelList;
//        else if (mCurrentList.size() == 0) {
//            return mCurrentList;
//        }
//        for (int i = 0; i < mCurrentPosition + 1; i++) {
//            mCurrentList.add(mModelList.get(i));
//        }
        return mCurrentList;
    }
//    public List<EntryModel> getCurrentList() {
//        List<EntryModel> tempList = new ArrayList<>();
//        if (mCurrentPosition >= mModelList.size())
//            return mModelList;
//        else if (mCurrentPosition == 0) {
//            return new ArrayList<EntryModel>();
//        }
//        for (int i = 0; i < mCurrentPosition + 1; i++) {
//            tempList.add(mModelList.get(i));
//        }
//        return tempList;
//    }

    public EntryModel addNewElement() {
        //if all elements adding to adapter
        if (mCurrentPosition >= mModelList.size())
            return null;
        //add elements
        mCurrentList.add(mModelList.get(mCurrentPosition));
        return mCurrentList.get(mCurrentPosition++);
    }

    public List<EntryModel> getModelList() {
        return mModelList;
    }

    public void setModelList(List<EntryModel> modelList) {
        mModelList = modelList;
    }

    public int getCurrentPosition() {
        return mCurrentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        mCurrentPosition = currentPosition;
    }
}
