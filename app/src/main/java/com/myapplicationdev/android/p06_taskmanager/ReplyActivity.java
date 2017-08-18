package com.myapplicationdev.android.p06_taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import static android.R.attr.data;

public class ReplyActivity extends AppCompatActivity {
    Task data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);

        CharSequence reply = null;
        Intent intent = getIntent();
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null){
            reply = remoteInput.getCharSequence("status");
        }

        if(reply != null){
            DBHelper dbh = new DBHelper(ReplyActivity.this);
            dbh.deleteNote(data.getId());
            dbh.close();
            Intent i = getIntent();
            data = (Task) i.getSerializableExtra("data");
            Toast.makeText(ReplyActivity.this, "You have deleted: " + reply,
                    Toast.LENGTH_SHORT).show();

        }

    }
}
