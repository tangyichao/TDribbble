package com.tyc.tdribbble.ui.login;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tyc.tdribbble.R;
import com.tyc.tdribbble.TDribbbleApp;
import com.tyc.tdribbble.api.ApiConstants;
import com.tyc.tdribbble.base.BaseActivity;
import com.tyc.tdribbble.entity.UserEntity;
import com.tyc.tdribbble.ui.oauth.OAuthActivity;
import com.tyc.tdribbble.utils.SpUtils;
import com.tyc.tdribbble.utils.StringOauth;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements ILoginView {

    private static final int REQUEST_CODE = 200;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.tv_come)
    TextView mTvCome;
    @BindView(R.id.pb_login)
    ProgressBar mPbLogin;
    private LoginPresenter mPresnter;

    @Override
    protected int layoutResID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        //
        mPresnter = new LoginPresenter(this);
        mTvCome.setVisibility(View.GONE);
        mPbLogin.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, OAuthActivity.class);
                intent.putExtra("url", StringOauth.getOauthSting());
                startActivityForResult(intent, REQUEST_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            mTvCome.setVisibility(View.VISIBLE);
            mPbLogin.setVisibility(View.VISIBLE);
            mBtnLogin.setVisibility(View.GONE);
            String code = data.getStringExtra("code");
            mPresnter.loadToken(this,code);
        }else{
            mTvCome.setVisibility(View.GONE);
            mPbLogin.setVisibility(View.GONE);
            mBtnLogin.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showUser(UserEntity userEntity) {
        EventBus.getDefault().post(userEntity);
        finish();
    }

    @Override
    public void showToken(String token) {
        TDribbbleApp.TOKEN = token;
        SpUtils.getSpUtils(this).putString(ApiConstants.OAuth.TOKEN, token);
        mPresnter.loadUser(this);
    }

    @Override
    public void showError() {
        mTvCome.setVisibility(View.GONE);
        mPbLogin.setVisibility(View.GONE);
        mBtnLogin.setVisibility(View.VISIBLE);
    }

}
