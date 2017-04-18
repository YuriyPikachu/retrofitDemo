package com.goach.client.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.goach.client.R;
import com.goach.client.api.ILoginService;
import com.goach.client.model.RegisterBean;
import com.goach.client.util.BaseResp;
import com.goach.client.util.HRetrofitNetHelper;

import java.util.Date;
import retrofit2.Call;

public class LoginActivity extends BaseActivity implements View.OnClickListener,HRetrofitNetHelper.RetrofitCallBack<RegisterBean> {
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mLoginFormView;
    private Button mSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    return true;
                }
                return false;
            }
        });

        mSignInButton = (Button) findViewById(R.id.sign_in_button);
        mSignInButton.setOnClickListener(this);
        mLoginFormView = findViewById(R.id.login_form);
    }
    public void startRegister(View view){
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sign_in_button:
                mDialog.setMessage("正在登录中，请稍后...");
                mDialog.show();
                ILoginService loginService = retrofitNetHelper.getAPIService(ILoginService.class);
                String username = mEmailView.getText().toString();
                String password = mPasswordView.getText().toString();
                if(!TextUtils.isEmpty(username)&&!TextUtils.isEmpty(password)){
                    final Call<BaseResp<RegisterBean>> repos = loginService.userLogin(username,password);
                    retrofitNetHelper.enqueueCall(repos,this);
                }
                break;
        }
    }
    @Override
    public void onSuccess(BaseResp<RegisterBean> baseResp) {
        Log.d("zgx","onResponse======"+baseResp.getData().getErrorCode());
        Date date = baseResp.getResponseTime();
        Log.d("zgx","RegisterBean======"+date);
        if(baseResp.getData().getErrorCode()==1){
            Intent intent = new Intent(LoginActivity.this, NewsActivity.class);
            intent.putExtra("intent_user_id",String.valueOf(baseResp.getData().getUserId()));
            startActivity(intent);
            Toast.makeText(getBaseContext(),"登录成功",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getBaseContext(),"用户不存在",Toast.LENGTH_SHORT).show();
        }
        mDialog.dismiss();
    }

    @Override
    public void onFailure(String error) {
        Log.d("zgx","onFailure======"+error);
        mDialog.dismiss();
    }
}

