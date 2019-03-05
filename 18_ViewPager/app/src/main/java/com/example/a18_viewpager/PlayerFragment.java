package com.example.a18_viewpager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a18_viewpager.model.PodcastItem;


public class PlayerFragment extends Fragment {
    PodcastItem mItem;

    private static final String MY_ITEM = "MY_ITEM";
    public static PlayerFragment newInstance(PodcastItem item) {
        PlayerFragment fragment = new PlayerFragment();
        Bundle args = new Bundle();
        args.putSerializable(MY_ITEM, item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mItem = (PodcastItem) getArguments().getSerializable(MY_ITEM);
        }
    }

    TextView textViewTitle, textViewSummary, textViewPubDate, textViewDuration;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_player, container, false);
        textViewTitle = v.findViewById(R.id.textViewDetailTitle);
        textViewSummary= v.findViewById(R.id.textViewDetailSummary);
        textViewPubDate = v.findViewById(R.id.textViewDetailPubDate);
        textViewDuration = v.findViewById(R.id.textViewDetailDuration);

        textViewTitle.setText(mItem.getTitle());
        textViewSummary.setText(mItem.getSummary());
        textViewPubDate.setText(mItem.getPubDate());
        textViewDuration.setText(mItem.getDuration());

        return v;
    }

}
