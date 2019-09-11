package com.appocean.callingapp;

import android.os.Bundle;

import com.appocean.callingapp.databinding.ActivitySingupBinding;
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class MainActivity extends AppCompatActivity {

    ActivitySingupBinding activitySingupBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySingupBinding = DataBindingUtil.setContentView(this, R.layout.activity_singup);


    }
}
