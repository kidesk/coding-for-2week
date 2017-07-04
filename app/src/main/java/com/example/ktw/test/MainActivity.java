package com.example.ktw.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    //private TextView mtextView;
    private EditText mtitleEditText;
    private EditText mcontentEditText;
    private Button mbutton;
    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // mtextView = (TextView) findViewById(R.id.textView);
        mtitleEditText = (EditText) findViewById(R.id.titleEditText);
        mcontentEditText = (EditText) findViewById(R.id.contentEditText);
        mbutton = (Button) findViewById(R.id.button2);

        View.OnClickListener firstOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Realm.init(getApplicationContext());
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
               final Article article = realm.where(Article.class).equalTo("title",mTitle).findFirst();
                article.deleteFromRealm();
                realm.commitTransaction();

                finish();
                //article addition
//                String titletext = mtitleEditText.getText().toString();
//                String contenttext = mcontentEditText.getText().toString();
//                //mtextView.setText(text);
//                mtitleEditText.setText("");
//                mcontentEditText.setText("");
//
//                Realm.init(getApplicationContext());
//                Realm realm = Realm.getDefaultInstance();
//
//                realm.beginTransaction();
//                Article article = realm.createObject(Article.class);
//                article.setTitle(titletext);
//                article.setContent(contenttext);
//                realm.commitTransaction();
//

            }
        };
        mbutton.setOnClickListener(firstOnClickListener);
// todo : todo Below
        if (savedInstanceState == null) {
//            String text = getIntent().getStringExtra("item");
//            mtextView.setText(text);
            String title = getIntent().getStringExtra("title");
            String content = getIntent().getStringExtra("content");
            mTitle = title;
            mtitleEditText.setText(title);
            mcontentEditText.setText(content);
        }
        ;
    }
}