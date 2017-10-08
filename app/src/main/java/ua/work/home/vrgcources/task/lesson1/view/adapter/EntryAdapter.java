package ua.work.home.vrgcources.task.lesson1.view.adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ua.work.home.vrgcources.R;
import ua.work.home.vrgcources.task.lesson1.data.model.EntryModel;
import ua.work.home.vrgcources.task.lesson1.view.activity.ActivityMain;
import ua.work.home.vrgcources.task.lesson1.view.fragment.FragmentDetail;

/**
 * Created by Andrii Papai on 07.10.2017.
 */

public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.EntryViewHolder> {
    private List<EntryModel> mEntryModels;
    private Context mContext;

    public EntryAdapter(List<EntryModel> entryModels, Context context) {
        mEntryModels = entryModels;
        mContext = context;
    }

    /**
     * ViewHolder class
     */
//    public static class EntryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public class EntryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTextHeader;
        public TextView mTextDescription;

        public EntryViewHolder(View itemView) {
            super(itemView);
            mTextHeader = (TextView) itemView.findViewById(R.id.text_header);
            mTextDescription = (TextView) itemView.findViewById(R.id.text_description);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            String header = mTextHeader.getText().toString();
            String description = mTextDescription.getText().toString();

            FragmentDetail fragment = FragmentDetail.newInstance(header, description, position);
            FragmentTransaction transaction = ((ActivityMain) mContext)
                    .getSupportFragmentManager().beginTransaction();

            if (mContext.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                //if portrait orientation
                transaction
                        .replace(R.id.container_list, fragment, FragmentDetail.class.getSimpleName())
                        .addToBackStack(FragmentDetail.class.getSimpleName())
                        .commit();
            } else {
                //if landscape orientation
                transaction
                        .replace(R.id.container_detail, fragment, FragmentDetail.class.getSimpleName())
                        .addToBackStack(FragmentDetail.class.getSimpleName())
                        .commit();
            }
        }
    }

    @Override
    public EntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //create view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new EntryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EntryViewHolder holder, int position) {
        holder.mTextHeader.setText(mEntryModels.get(position).getHeader());
        holder.mTextDescription.setText(mEntryModels.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mEntryModels.size();
    }

    public List<EntryModel> getEntryModels() {
        return mEntryModels;
    }

    public void setEntryModels(List<EntryModel> entryModels) {
        mEntryModels = entryModels;
    }
}
