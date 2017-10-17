package ua.work.home.vrgcources.task.lesson2.view.fragment;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import ua.work.home.vrgcources.R;
import ua.work.home.vrgcources.task.App;
import ua.work.home.vrgcources.task.lesson2.view.adapter.EntryAdapter;
import ua.work.home.vrgcources.task.lesson2.view.adapter.drag.drop.SimpleItemTouchHelperCallback;
import ua.work.home.vrgcources.task.data.model.EntryModel;
import ua.work.home.vrgcources.task.lesson2.view.utils.ItemAnimator;
import ua.work.home.vrgcources.task.lesson2.view.utils.ItemManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentList extends Fragment implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private EntryAdapter mAdapter;
    private ImageButton bAddButton;

    //drag and drop func
    private ItemTouchHelper mItemTouchHelper;
    //item animator
    private ItemAnimator mItemAnimator;

    private List<EntryModel> mEntryModels;

    public FragmentList() {
        // Required empty public constructor
    }

    /**
     * Factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentList.
     */
    public static FragmentList newInstance() {
        FragmentList fragment = new FragmentList();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mItemAnimator = new ItemAnimator(getActivity());
        mEntryModels = App.getItemMabager().getCurrentList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(mItemAnimator);

        bAddButton = (ImageButton) rootView.findViewById(R.id.button_add);
        bAddButton.setOnClickListener(this);

        updateUI();

        return rootView;
    }

    private void updateUI() {
//        List<EntryModel> models = App.getDataProvider().getListData();
        List<EntryModel> models = mEntryModels;
        mAdapter = new EntryAdapter(models, getActivity());
        mRecyclerView.setAdapter(mAdapter);

        //drag and drop plus swipe
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    /**
     * Update position
     */
    public void updatePosition(int position) {
        EntryModel modelNew = App.getDataProvider().getEntry(position);

        if (position == mAdapter.getItemCount()) {
            //if add new item
            mAdapter.getEntryModels().add(position, modelNew);
        } else {
            //if update exist item
            mAdapter.getEntryModels().get(position)
                    .setHeader(modelNew.getHeader())
                    .setDescription(modelNew.getDescription());
        }
        mAdapter.notifyItemChanged(position);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_add:
                addElement();
                break;
        }
    }

    private void addElement() {
        ItemManager manager = App.getItemMabager();
        EntryModel model = manager.addNewElement();
        if (model != null) {
            mAdapter.getEntryModels().add(model);
            mAdapter.notifyItemChanged(mAdapter.getItemCount());
        }
    }

}
