package com.appocean.callingapp;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.appocean.callingapp.databinding.ActivityEnterDetailsBinding;
import com.appocean.callingapp.util.BaseActivity;
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;

import java.util.Date;

public class EnterDetailsActivity extends BaseActivity {

    ActivityEnterDetailsBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_enter_details);
        new SingleDateAndTimePickerDialog.Builder(this)
                .bottomSheet()
                .curved()
                .displayMinutes(false)
                .displayHours(false)
                .displayDays(false)
                .displayMonth(true)
                .displayYears(true)
                .displayDaysOfMonth(true)
                .listener(new SingleDateAndTimePickerDialog.Listener() {
                    @Override
                    public void onDateSelected(Date date) {
                        setSelectedDate(date);
                    }
                })
                .display();

    }

    private void setSelectedDate(Date date) {
    }
}