package cn.an.a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import java.util.List;

import cn.an.bean.T;
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

        //获取软件更新数据
        BmobQuery<UpData> query=new BmobQuery<>();
        query.findObjects(new FindListener<UpData>() {
            @Override
            public void done(List<UpData> list, BmobException e) {
                String ver="20220207";

                //判断是否篡改了版本号
                if (T.packageName(FlashActivity.this).equals(ver)) {

                    //判断获取最新版本是不是成功了
                    if (e==null){

                        //判断是不是最新版本
                        if (list.get(0).getVersion().equals(ver)){

                            //确定是最新版本了，跳转到主要界面

                            Intent intent = new Intent(FlashActivity.this,MainActivity.class);
                            String notice = list.get(0).getNotice();
                            intent.putExtra("notice",notice);

                            //跳转
                            startActivity(intent);
                        }else {

                            //不是最新版本,需要更新操作
                            Intent intent = new Intent();

                            //获取更新链接
                            String url = list.get(0).getUrl();
                            intent.setAction("android.intent.action.VIEW");
                            Uri content_url = Uri.parse(url);
                            intent.setData(content_url);

                            startActivity(intent);
                        }
                    }else {

                        //访问Bmob数据库失败
                        T.toast(FlashActivity.this,"版本获取失败："+e.toString());
                    }
                }else {
                    T.toast(FlashActivity.this,"版本错误！");
                }
            }
        });

    }
}