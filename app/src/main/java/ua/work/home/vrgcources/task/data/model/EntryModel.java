package ua.work.home.vrgcources.task.data.model;

/**
 * Created by Andrii Papai on 07.10.2017.
 * <p>
 * Model class
 */

public class EntryModel {
    private String mHeader;
    private String mDescription;

    public EntryModel() {

    }

    public EntryModel(String header, String description) {
        mHeader = header;
        mDescription = description;
    }

    public String getHeader() {
        return mHeader;
    }

    public EntryModel setHeader(String header) {
        mHeader = header;
        return this;
    }

    public String getDescription() {
        return mDescription;
    }

    public EntryModel setDescription(String description) {
        mDescription = description;
        return this;
    }
}
