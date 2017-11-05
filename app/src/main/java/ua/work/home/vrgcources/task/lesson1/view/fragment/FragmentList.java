package ua.work.home.vrgcources.task.lesson1.view.fragment;


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

import java.util.List;

import ua.work.home.vrgcources.R;
import ua.work.home.vrgcources.task.App;
import ua.work.home.vrgcources.task.data.model.EntryModel;
import ua.work.home.vrgcources.task.lesson1.view.adapter.EntryAdapter;
import ua.work.home.vrgcources.task.lesson1.view.adapter.drag.drop.SimpleItemTouchHelperCallback;
import ua.work.home.vrgcources.task.lesson1.view.presentation.ListContract;
import ua.work.home.vrgcources.task.lesson1.view.presentation.PresenterList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentList extends Fragment implements View.OnClickListener, ListContract.ViewList {
    private RecyclerView mRecyclerView;
    private EntryAdapter mAdapter;
    private ImageButton bAddButton;
    private PresenterList mPresenter;

    //drag and drop func
    private ItemTouchHelper mItemTouchHelper;

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

        mPresenter = new PresenterList(getActivity(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        bAddButton = (ImageButton) rootView.findViewById(R.id.button_add);
        bAddButton.setOnClickListener(this);

        updateUI();
        mPresenter.getData();

        return rootView;
    }

    private void updateUI() {
//
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
                startAddNewEntryDetailFragment();
                break;
        }
    }

    private void startAddNewEntryDetailFragment() {
        FragmentDetail fragmentDetail = FragmentDetail.newInstance(null, null, -1);
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_list, fragmentDetail)
                    .addToBackStack(FragmentList.class.getSimpleName())
                    .commit();
        } else {
            FragmentDetail fragmentDetailFind = (FragmentDetail) getActivity()
                    .getSupportFragmentManager().findFragmentByTag(FragmentDetail.class.getSimpleName());
            if (fragmentDetailFind != null) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .remove(fragmentDetailFind)
                        .commit();
            }
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_detail, fragmentDetail)
                    .addToBackStack(FragmentList.class.getSimpleName())
                    .commit();
        }
    }

    @Override
    public void setData(EntryAdapter adapter) {
//        List<EntryModel> models = App.getDataProvider().getListData();
//        mAdapter = new EntryAdapter(models, getActivity());
        mRecyclerView.setAdapter(adapter);

        //drag and drop plus swipe
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }
}
