package com.example.rathana.roompersistence;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressDialogFragment extends DialogFragment {
    private ProgressBar progressBar;
    private TextView tvResult;
    private DialogFragmentListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener= (DialogFragmentListener) context;
    }

    public void setProgressBarValue(int value) {
        this.progressBar.setProgress(value);
    }

    public void setResult(String value) {
        this.tvResult.setText(value);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("Donwloading ... ");
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.progress_dialog_layout,null);


        progressBar=view.findViewById(R.id.progressBar);
        tvResult= view.findViewById(R.id.result);
        builder.setView(view);
        builder.setNegativeButton("Canccel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onCancel();
                dialog.dismiss();
            }
        });
        return builder.create();
    }

    public interface DialogFragmentListener{
        void onCancel();
    }

}
