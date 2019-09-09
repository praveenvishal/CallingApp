package com.appocean.callingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.appocean.callingapp.databinding.ActivityAuthBinding;
import com.appocean.callingapp.phonenumberui.PhoneNumberActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class AuthActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityAuthBinding mBinding;
    private static final int REQUEST_PHONE_VERIFICATION = 1080;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_auth);


    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.buttonVerify:
                verify();
                break;
        }

    }

    private void verify() {
        Intent intent = new Intent(AuthActivity.this, PhoneNumberActivity.class);
        intent.putExtra("", getResources().getString(R.string.app_name));
        intent.putExtra("PHONE_NUMBER", "");
        startActivityForResult(intent, REQUEST_PHONE_VERIFICATION);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_PHONE_VERIFICATION:
// If mobile number is verified successfully then you get your phone number to perform further operations.
                if (data != null && data.hasExtra("PHONE_NUMBER") && data.getStringExtra("PHONE_NUMBER") != null) {
                    String phoneNumber = data.getStringExtra("PHONE_NUMBER");
//                    mobileNumber = phoneNumber;
                } else {
                    // If mobile number is not verified successfully You can hendle according to your requirement.
                }
                break;
        }
    }
}
