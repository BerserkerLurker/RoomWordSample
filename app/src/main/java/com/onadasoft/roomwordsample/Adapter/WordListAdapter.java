package com.onadasoft.roomwordsample.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.onadasoft.roomwordsample.R;
import com.onadasoft.roomwordsample.db.entity.Word;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private LayoutInflater mInflater;
    private List<Word> mWords; // cached copy of words

    public WordListAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {

        if (mWords != null){
            Word current = mWords.get(position);
            holder.wordItemView.setText(current.getWord());
        }else{
            // Cover the case of data not being ready yet
            holder.wordItemView.setText("No Word");
        }
    }
    void setWords(List<Word> words){
        mWords = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mWords != null){
            return mWords.size();
        }else {
            return 0;
        }
    }

    public class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        public WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }


}
