package com.example.ktw.test;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import io.realm.RealmResults;

/**
 * Created by ktw on 2017-07-03.
 */

public class CustomAdapter extends ArrayAdapter<Article> {
    private Context mContext;
    private RealmResults<Article> mArticles;

    public CustomAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Article> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mArticles = (RealmResults<Article>) objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE
       );
        View row = inflater.inflate(R.layout.list_row, parent, false);

        TextView titleView = row.findViewById(R.id.titleView);
        TextView contentView = row.findViewById(R.id.contentView);
        titleView.setText(mArticles.get(position).getTitle());
        contentView.setText(mArticles.get(position).getContent());

        return row;

    }
}
