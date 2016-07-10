package com.baidu.retrofit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.baidu.retrofit.Constant;
import com.baidu.retrofit.R;
import com.baidu.retrofit.bean.FamousInfo;
import com.baidu.retrofit.bean.FamousInfoReq;
import com.baidu.retrofit.bean.IpInfo;
import com.baidu.retrofit.model.IpInfoRxModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
public class ConverterActivity extends AppCompatActivity implements View.OnClickListener{

    public final String TAG= this.getClass().getName();
    private IpInfoRxModel infoModel;

    @BindView(R.id.edit_keyword)
    EditText mEditKeyWord;
    @BindView(R.id.button_search)
    Button mSerachBtn;
    @BindView(R.id.txt_content)
    TextView mTxtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();

        infoModel = IpInfoRxModel.getInstance(this);
    }

    /**
     * 初始化View
     */
    private void initView() {
        mEditKeyWord.setText("63.223.108.42");
    }

    @Override
    @OnClick({ R.id.button_search })
    public void onClick(View view) {

        Log.d(TAG,"onclick");
        if(view.getId() == R.id.button_search) {

            infoModel.queryIpInfo("63.223.108.42")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<IpInfo>() {
                        @Override
                        public void onCompleted() {

                            Log.e("lzx", "onCompleted");
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("lzx", "onError  " + e.toString());
                        }

                        @Override
                        public void onNext(IpInfo ipInfo) {
                            Log.e("lzx", "onNext " + ipInfo.toString());
                            mTxtContent.setText("county : " + ipInfo.data.country);
                            /*IpInfo.Info entity = ipInfo.data;
                            Log.d(TAG,"entity " + entity.toString());
                            mTxtContent.setText("county : " + entity.country);*/
                        }
                    });

            infoModel.queryLookUp(initParams())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<FamousInfo>() {
                        @Override
                        public void onCompleted() {

                            Log.e("lzx", "onCompleted");
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("lzx", "onError  " + e.toString());
                        }

                        @Override
                        public void onNext(FamousInfo famousInfo) {
                            Log.e("lzx", "onNext " + famousInfo.toString());

                            List<FamousInfo.ResultEntity> entity = famousInfo.result;
                            String text = "1、"+entity.get(0).famous_saying +"\n---"+entity.get(0).famous_name+"\n 2、"
                                    +entity.get(1).famous_saying +"\n---"+entity.get(1).famous_name;
                            Log.e("lzx", "onNext text " + text);
                        }
                    });

        }
    }

    private FamousInfoReq initParams() {
        FamousInfoReq mFamousInfoReq = null;
        mFamousInfoReq= new FamousInfoReq();
        mFamousInfoReq.apiKey= Constant.APIKEY;
        mFamousInfoReq.keyword = "人才";
        mFamousInfoReq.page=1;
        mFamousInfoReq.rows=20;
        return  mFamousInfoReq;

    }
}
