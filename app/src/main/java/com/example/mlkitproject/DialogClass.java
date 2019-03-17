package com.example.mlkitproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.zip.Inflater;

public class DialogClass extends DialogFragment {
    TextView result_text_view;
    Button result_ok_button;
static String resultText="";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_layout,container,false);
        result_text_view=view.findViewById(R.id.result_text_view);
        result_ok_button=view.findViewById(R.id.result_ok_button);
        Bundle bundle=getArguments();
        resultText=bundle.getString(InitializeFirebase.resultText);
        result_text_view.setText(resultText);
        result_ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });



        return view;
    }
}
