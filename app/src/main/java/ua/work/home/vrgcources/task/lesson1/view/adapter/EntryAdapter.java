package ua.work.home.vrgcources.task.lesson1.view.adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ua.work.home.vrgcources.R;
import ua.work.home.vrgcources.task.lesson1.data.model.EntryModel;
import ua.work.home.vrgcources.task.lesson1.view.activity.ActivityMain;
import ua.work.home.vrgcources.task.lesson1.view.adapter.drag.drop.ItemTouchHelperAdapter;
import ua.work.home.vrgcources.task.lesson1.view.adapter.drag.drop.ItemTouchHelperViewHolder;
import ua.work.home.vrgcources.task.lesson1.view.fragment.FragmentDetail;

/**
 * Created by Andrii Papai on 07.10.2017.
 */

public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.EntryViewHolder>
        implements ItemTouchHelperAdapter {

    private List<EntryModel> mEntryModels;
    private Context mContext;

    public EntryAdapter(List<EntryModel> entryModels, Context context) {
        mEntryModels = entryModels;
        mContext = context;
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

    /**
     * Drag&drop func
     */
    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        EntryModel prev = mEntryModels.remove(fromPosition);
        mEntryModels.add(toPosition > fromPosition ? toPosition - 1 : toPosition, prev);
        notifyItemMoved(fromPosition, toPosition);
    }

    /**
     * Swipe to del
     */
    @Override
    public void onItemDismiss(int position) {
        mEntryModels.remove(position);
        notifyItemRemoved(position);
    }

    public List<EntryModel> getEntryModels() {
        return mEntryModels;
    }

    public void setEntryModels(List<EntryModel> entryModels) {
        mEntryModels = entryModels;
    }

    /**
     * ViewHolder class
     */
    public class EntryViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, ItemTouchHelperViewHolder {

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

        @Override
        public void onItemSelected() {
            int color = ResourcesCompat.getColor(mContext.getResources(), R.color.colorGreyDark, null);
            itemView.setBackgroundColor(color);
        }

        @Override
        public void onItemClear() {
            Drawable drawable = ResourcesCompat.getDrawable(mContext.getResources(), R.drawable.rounded_corner_shape, null);
            itemView.setBackground(drawable);
        }
    }
}
