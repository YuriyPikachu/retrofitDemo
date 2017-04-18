package com.goach.client.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;
import com.goach.client.R;
import com.goach.client.api.IRegisterService;
import com.goach.client.model.RegisterBean;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends BaseActivity implements Callback<RegisterBean> {
    private AutoCompleteTextView mUserName;
    private EditText mPasswordEditText;
    private EditText mConfirmationEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mUserName = (AutoCompleteTextView) findViewById(R.id.id_username);
        mPasswordEditText = (EditText)findViewById(R.id.password);
        mConfirmationEditText = (EditText)findViewById(R.id.confirmation_password);
    }
    public void startRegister(View view){
        String userName = mUserName.getText().toString();
        String password = mPasswordEditText.getText().toString();
        String mConfirmation = mConfirmationEditText.getText().toString();
        if(!TextUtils.isEmpty(userName)&&!TextUtils.isEmpty(password)
                &&!TextUtils.isEmpty(mConfirmation)){
            if(password.equals(mConfirmation)){
                IRegisterService loginService = retrofitNetHelper.getAPIService(IRegisterService.class);
                Map<String,String> mParamsMap = new HashMap<>();
                mParamsMap.put("username",userName);
                mParamsMap.put("password",password);
               Call<RegisterBean> call =  loginService.createUser(mParamsMap);
                call.enqueue(this);
            }else {
                Toast.makeText(getBaseContext(),"密码不一致",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(getBaseContext(),"请填写完整",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResponse(Call<RegisterBean> call, Response<RegisterBean> response) {
        if(response.body().getErrorCode()==1){
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(getBaseContext(),"注册失败",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<RegisterBean> call, Throwable t) {

    }
}

