package cn.an.a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.List;

import cn.an.bean.UpData;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class FlashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flash_activity);
        //初始化bmob
        Bmob.initialize(this,"4dcbf5e073fc99313244ea0f5ed03db9");

        BmobQuery<UpData> query=new BmobQuery<>();
        query.findObjects(new FindListener<UpData>() {
            @Override
            public void done(List<UpData> list, BmobException e) {

            }
        });

    }
}