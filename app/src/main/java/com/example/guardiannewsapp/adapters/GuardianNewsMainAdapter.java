package com.example.guardiannewsapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guardiannewsapp.R;
import com.example.guardiannewsapp.models.Results;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class GuardianNewsMainAdapter extends RecyclerView.Adapter<GuardianNewsMainAdapter.GuardianNewsViewHolder> {
    private final String TAG = GuardianNewsMainAdapter.class.getSimpleName();
    private Context mContext;
    private List<Results> resultsArrayList;

    public GuardianNewsMainAdapter (Context context, List<Results> results){
        this.mContext = context;
        this.resultsArrayList = results;
    }

    public class GuardianNewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.guardian_news_list_tv)
        TextView guardian_news_tv;

        @BindView(R.id.guardian_news_card_view)
        CardView mCardView;


        GuardianNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) {

        }
    }

    @NonNull
    @Override
    public GuardianNewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        int layoutIdForListItem = R.layout.guardian_news_list_item;

        boolean shouldAttachToParentImmediately = false;

        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);

        return new GuardianNewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuardianNewsViewHolder holder, int position) {
        if(resultsArrayList == null){
            Log.d(TAG, "results data is null");
            return;
        }

        //gets category title based off position
        String news_link = resultsArrayList.get(position).getWebUrl();
        Log.i(TAG, news_link);
        //populate each category tv w/ proper title.
        holder.guardian_news_tv.setText(news_link);
    }

    @Override
    public int getItemCount() {
        if (null == resultsArrayList) return 0;
        return resultsArrayList.size();
    }

    public void notifyDataChanged(List<Results> newResultsArrList){
        resultsArrayList = newResultsArrList;
        notifyDataSetChanged();
    }



}
