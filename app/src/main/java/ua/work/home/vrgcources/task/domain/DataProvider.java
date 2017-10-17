package ua.work.home.vrgcources.task.domain;

import java.util.List;

import ua.work.home.vrgcources.task.data.model.EntryModel;

/**
 * Created by Andrii Papai on 07.10.2017.
 */

public interface DataProvider {

    /**
     * Get List of Data
     */
    List<EntryModel> getListData();

    /**
     * Get current Entry
     *
     * @param position
     */
    EntryModel getEntry(int position);

    /**
     * Save new Data
     */
    void addData(EntryModel model);

    /**
     * Update Data
     */
    void updateData(EntryModel model, int position);

    /**
     * Drag and drop view
     */
    void changePosition(int fromPosition, int toPosition);

    /**
     * Delete item from posotopn
     */
    void deleteView(int position);

}
