package com.ac57.ui.main.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ac57.R;
import com.ac57.framework.base.BaseMVPActivity;
import com.ac57.framework.utils.IntentUtils;
import com.ac57.ui.entity.UserInfoDetailEntity;
import com.ac57.ui.presenter.SelfPresenter;
import com.ac57.ui.presenter.view.ISelfView;
import com.ac57.ui.utils.LoginType;
import com.ac57.ui.utils.Tools;
import com.ac57.ui.view.CircleImageView;
import com.ac57.ui.view.MyProgressBar;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.OnClick;

import static com.ac57.R.id.self_top_linear1;
import static com.ac57.R.id.self_top_linear2;
import static com.ac57.R.id.self_view;

public class SelfActivity extends BaseMVPActivity<ISelfView, SelfPresenter> implements ISelfView {

    @BindView(R.id.iv_self_back)
    ImageView ivSelfBack;
    @BindView(R.id.civ_self_head)
    CircleImageView civSelfHead;
    @BindView(R.id.tv_self_login_register)
    TextView tvSelfLoginRegister;
    @BindView(self_view)
    View selfView;
    @BindView(R.id.tv_self_name)
    TextView tvSelfName;
    @BindView(R.id.iv_self_level)
    ImageView ivSelfLevel;
    @BindView(self_top_linear1)
    LinearLayout selfTopLinear1;
    @BindView(R.id.tv_self_gzs)
    TextView tvSelfGzs;
    @BindView(R.id.tv_self_gz)
    TextView tvSelfGz;
    @BindView(R.id.tv_self_qd)
    TextView tvSelfQd;
    @BindView(self_top_linear2)
    LinearLayout selfTopLinear2;
    @BindView(R.id.pb_self_process)
    MyProgressBar pbSelfProcess;
    @BindView(R.id.self_top)
    RelativeLayout selfTop;

    private int draw[];

    @Override
    public int getLayout() {
        return R.layout.activity_self;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        draw = new int[]{R.drawable.level1,
                R.drawable.level2,
                R.drawable.level3,
                R.drawable.level4,
                R.drawable.level5,
                R.drawable.level6};
        setStatusBarColor(Color.WHITE, 30);
    }

    @Override
    public void initDatas() {

    }


    @Override
    public void loadData() {
        if (Tools.loginType() != LoginType.VISTOR) {
            mPresenter.getUserDetailData();
        }
    }

    private void setViewIsGone(int vs1, int vs2) {
        selfView.setVisibility(vs1);
        tvSelfLoginRegister.setVisibility(vs1);
        selfTopLinear1.setVisibility(vs2);
        selfTopLinear2.setVisibility(vs2);
        pbSelfProcess.setVisibility(vs2);
    }

    @Override
    public void getUserInfoDetail(UserInfoDetailEntity entity) {
        if (entity != null) {
            setViewIsGone(View.GONE, View.VISIBLE);
            Glide.with(this).load(entity.head_img).placeholder(R.drawable.personal_head_portrait).into(civSelfHead);
            tvSelfName.setText(entity.nickname);
            int fans = Integer.parseInt(entity.fans_num);
            if (fans == 0) {
                tvSelfGzs.setText("关注");
                tvSelfGz.setText("");
            } else {
                tvSelfGzs.setText("关注 : ");
                tvSelfGz.setText(fans + "");
            }
            tvSelfQd.setText(entity.sign_num + "天");
            int level = Integer.parseInt(entity.level);
            ivSelfLevel.setImageResource(draw[level]);
            pbSelfProcess.setValues(Integer.parseInt(entity.total_exp), 0, Integer.parseInt(entity.exp), entity.exp_percent_show_str, entity.exp_lack_show_str);
        } else {
            setViewIsGone(View.VISIBLE, View.GONE);
        }
    }

    @OnClick({R.id.civ_self_head, R.id.self_stv_tab1, R.id.self_stv_tab2, R.id.self_stv_tab3, R.id.self_stv_tab4, R.id.self_stv_tab5, R.id.self_stv_tab6, R.id.self_stv_tab7})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.civ_self_head:
                startNextActivity(AccountSettingActviity.class);
                break;
            case R.id.self_stv_tab1:
                Bundle bundle = new Bundle();
                bundle.putString("exchange_type", "2");
                IntentUtils.startActivity(this, ExchangeActivity.class, bundle);
                break;
            case R.id.self_stv_tab2:
                startNextActivity(MyTopicActivity.class);
                break;
            case R.id.self_stv_tab3:
                startNextActivity(MyCollectionActivity.class);
                break;
            case R.id.self_stv_tab4:
                startNextActivity(MyChoseActivity.class);
                break;
            case R.id.self_stv_tab5:
                IntentUtils.startActivity(this, SelectExchangeActivity.class);
                break;
            case R.id.self_stv_tab6:
                IntentUtils.startActivity(this, HelpFeedbackActivity.class);
                break;
            case R.id.self_stv_tab7:
                IntentUtils.startActivity(this, SettingActviity.class);
                break;
        }
    }

    private void startNextActivity(Class clas) {
        if (Tools.loginType() != LoginType.VISTOR) {
            IntentUtils.startActivity(this, clas);
        } else {
            IntentUtils.startActivity(this, LoginActivity.class);
        }
    }

    @Override
    protected SelfPresenter initPresenter() {
        return new SelfPresenter();
    }
}
