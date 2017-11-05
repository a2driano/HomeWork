package ua.work.home.vrgcources.task.lesson1.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import ua.work.home.vrgcources.R;
import ua.work.home.vrgcources.task.lesson1.view.presentation.DetailContract;
import ua.work.home.vrgcources.task.lesson1.view.presentation.PresenterDetail;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDetail extends Fragment implements View.OnClickListener, DetailContract.ViewDetailContract {
    private final static String HEADER = "header";
    private final static String DESCRIPTION = "description";
    private final static String POSITION = "position";

    OnDataUpgradeListener mOnDataUpgradeListener;

    private EditText etHeaderText;
    private EditText etDescriptionText;
    private ImageButton bEditButton;
    private ImageButton bSaveButton;

    private String mHeaderArgText;
    private String mDescriptionArgText;
    private int mPosition;
    private PresenterDetail mPresenterDetail;

    public FragmentDetail() {
        // Required empty public constructor
    }

    /**
     * Factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentDetail.
     */
    public static FragmentDetail newInstance(String header, String description, Integer position) {
        FragmentDetail fragment = new FragmentDetail();

        Bundle args = new Bundle();
        args.putString(HEADER, header);
        args.putString(DESCRIPTION, description);
        args.putInt(POSITION, position);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mHeaderArgText = getArguments().getString(HEADER);
            mDescriptionArgText = getArguments().getString(DESCRIPTION);
            mPosition = getArguments().getInt(POSITION);
        }

        mPresenterDetail = new PresenterDetail(getActivity(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        etHeaderText = (EditText) rootView.findViewById(R.id.text_header);
        etDescriptionText = (EditText) rootView.findViewById(R.id.text_description);

        //when Header & Description is not empty, bEditButton is Visible
        bEditButton = (ImageButton) rootView.findViewById(R.id.button_edit);
        //when Header & Description - empty, bSaveButton is Visible
        bSaveButton = (ImageButton) rootView.findViewById(R.id.button_save);
        bEditButton.setOnClickListener(this);
        bSaveButton.setOnClickListener(this);

        setTextToView();
        return rootView;
    }

    private void setTextToView() {
        //set text to views
        if (mHeaderArgText != null && mDescriptionArgText != null) {
            etHeaderText.setText(mHeaderArgText);
            etDescriptionText.setText(mDescriptionArgText);
            //disable edittext
            setEditTextDisabled();
            //set Edit button Visible
            editButtonActive();
        } else {
            //set focus to Header View
            etHeaderText.requestFocus();
        }
        //detect new entry
        if (mPosition == -1) {
            saveButtonActive();
//
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_edit:
                editEntry();
                break;
            case R.id.button_save:
//                saveNewEntry();
                saveEntry();
                break;
        }
    }

    @Override
    public void saveEntry() {
        mPresenterDetail.setEntry(mPosition, etHeaderText.getText().toString(), etDescriptionText.getText().toString());
    }

    @Override
    public void onSuccessCreate() {
        onDataChange();
        Toast.makeText(getActivity().getApplicationContext(), R.string.message_saved, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessUpdate() {
        onDataChange();
        Toast.makeText(getActivity().getApplicationContext(), R.string.message_changes_saved, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError() {
        Toast.makeText(getActivity().getApplicationContext(), R.string.message_empty_edittext, Toast.LENGTH_SHORT).show();
    }

    private void onDataChange() {
        mOnDataUpgradeListener.onDataUpgrade(mPosition);
        editButtonActive();
        setEditTextDisabled();
    }


    /**
     * when data save result, upgrade data in List fragment
     */
    public interface OnDataUpgradeListener {
        void onDataUpgrade(int position);
    }

    /**
     * Edit current Entry
     */
    private void editEntry() {
        saveButtonActive();
        etHeaderText.setEnabled(true);
        etDescriptionText.setEnabled(true);
    }

    private void setEditTextDisabled() {
        etHeaderText.setEnabled(false);
        etDescriptionText.setEnabled(false);
    }

    private void editButtonActive() {
        bEditButton.setVisibility(View.VISIBLE);
        bSaveButton.setVisibility(View.GONE);
    }

    private void saveButtonActive() {
        bEditButton.setVisibility(View.GONE);
        bSaveButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnDataUpgradeListener = (OnDataUpgradeListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
}
