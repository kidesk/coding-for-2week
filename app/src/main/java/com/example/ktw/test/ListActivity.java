package com.example.ktw.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class ListActivity extends AppCompatActivity {
private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

//final String[] sampleArray = {"hello", "this", "is", "test"};
//Article[] articles = {
//        new Article("title1", "content1"),
//        new Article("title2", "content2"),
//        new Article("title3", "content3"),

    //};

    mListView = (ListView) findViewById(R.id.listView);
        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item, (List<String>) articles);
        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance().getDefaultInstance();
        final RealmResults<Article> articles = realm.where(Article.class).findAll();
                final CustomAdapter adapter =  new CustomAdapter(
                        this,
                        R.layout.list_row,
                        //new ArrayList<Article>(Arrays.asList(articles)));
                        articles
                );
        articles.addChangeListener(new RealmChangeListener<RealmResults<Article>>() {
            @Override
            public void onChange(RealmResults<Article> articles) {
                adapter.notifyDataSetChanged();
            }
        });
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                // todo : fix Intent extra
                //intent.putExtra("item", sampleArray[i]);
                intent.putExtra("title", articles.get(i).getTitle());
                intent.putExtra("content", articles.get(i).getContent());


                startActivity(intent);
            }
        });
    }
}