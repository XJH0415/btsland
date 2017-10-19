package info.btsland.app.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import info.btsland.app.R;
import info.btsland.app.ui.fragment.HeadFragment;


public class SettingActivity extends BaseActivity {
    private HeadFragment headFragment;

    private TextView tvSetLanguage;
    private TextView tvSetTheme;
    private TextView tvSetGuide;
    private TextView tvSetWe;
    private TextView tvSetEdition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(info.btsland.app.R.layout.activity_setting);
        fillInHead();
        init();
    }

    /**
     * 初始化
     */
    private void init(){
        tvSetLanguage= (TextView) findViewById(R.id.tv_set_language);
        tvSetTheme= (TextView) findViewById(R.id.tv_set_theme);
        tvSetGuide= (TextView) findViewById(R.id.tv_set_guide);
        tvSetWe= (TextView) findViewById(R.id.tv_set_we);
        tvSetEdition= (TextView) findViewById(R.id.tv_set_edition);

        //绑定特效事件
        TextViewOnTouchListener OnTouchListener=new TextViewOnTouchListener();
        tvSetLanguage.setOnTouchListener(OnTouchListener);
        tvSetTheme.setOnTouchListener(OnTouchListener);
        tvSetGuide.setOnTouchListener(OnTouchListener);
        tvSetWe.setOnTouchListener(OnTouchListener);
        tvSetEdition.setOnTouchListener(OnTouchListener);

        //绑定点击事件
        TextViewOnClickListener OnClickListener=new TextViewOnClickListener();
        tvSetLanguage.setOnClickListener(OnClickListener);
    }

    /*
     *点击事件
     */
    class TextViewOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.tv_set_language:
                    showListDialog();
                    break;
                case R.id.tv_set_theme:
                    break;
                case R.id.tv_set_guide:
                    break;
                case R.id.tv_set_we:
                    break;
                case R.id.tv_set_edition:
                    break;
            }
        }
    }


    /**
     * 装载顶部导航
     */
    private void fillInHead(){
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (headFragment==null){
            headFragment=new HeadFragment(HeadFragment.HeadType.BACK_NULL);
            headFragment.setTitleName(getString(R.string.set));
            transaction.add(R.id.fra_set_head,headFragment);
        }
        transaction.commit();
    }

    /**
     * 跳出Dialog窗口
     */
    private void showListDialog() {
        final String[] items = { "中文","英文"};
        AlertDialog.Builder listDialog = new AlertDialog.Builder(SettingActivity.this);

        listDialog.setTitle("请选择语言");
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(items[which]=="中文"){
                    switchLanguage("zh");
                }else if(items[which]=="英文"){
                    switchLanguage("en");
                }
                finish();

                Intent intent=new Intent(SettingActivity.this,MainActivity.class);
                //开始新的activity同时移除之前所有的activity
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        listDialog.show();
    }


    /**
     * 单击特效
     * @param textView 被单击的tv
     * @param motionEvent 当前状态
     */
    protected void touchColor(TextView textView,MotionEvent motionEvent){
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            textView.setBackground(getResources().getDrawable(R.drawable.tv_row_touch,null));
        }
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            textView.setBackground(getResources().getDrawable(R.drawable.tv_row,null));
        }
    }
    class TextViewOnTouchListener implements View.OnTouchListener{
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (view.getId()) {
                case R.id.tv_set_language:
                    touchColor(tvSetLanguage,motionEvent);
                    break;
                case R.id.tv_set_theme:
                    touchColor(tvSetTheme,motionEvent);
                    break;
                case R.id.tv_set_guide:
                    touchColor(tvSetGuide,motionEvent);
                    break;
                case R.id.tv_set_we:
                    touchColor(tvSetWe,motionEvent);
                    break;
                case R.id.tv_set_edition:
                    touchColor(tvSetEdition,motionEvent);
                    break;
            }
            return false;
        }
    }
}