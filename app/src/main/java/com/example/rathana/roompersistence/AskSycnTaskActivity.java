package com.example.rathana.roompersistence;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AskSycnTaskActivity extends AppCompatActivity implements ProgressDialogFragment.DialogFragmentListener {

    DownloadAskSycnTask askSycnTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_sycn_task);

    }


    public void onDownload(View view) {

        askSycnTask=new DownloadAskSycnTask(this);
        askSycnTask.execute("downloading");
    }

    @Override
    public void onCancel() {
        Toast.makeText(this, "onCancel", Toast.LENGTH_SHORT).show();
        askSycnTask.cancel(false);
    }
}
