package com.example.rathana.roompersistence;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

public class DownloadAskSycnTask extends AsyncTask<String ,Integer,Boolean> {

    AppCompatActivity activity;

    public DownloadAskSycnTask(AppCompatActivity activity) {
        this.activity = activity;
    }

    ProgressDialogFragment  dialog;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog=new ProgressDialogFragment();
        dialog.show(activity.getSupportFragmentManager(),"dialog");
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        int i=0;
        for(i=0;i<=100;i++){
            publishProgress(i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        dialog.setProgressBarValue(values[0]);
        dialog.setResult(""+values[0]);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        dialog.setResult("completed");
    }
}
