package com.cutler.testapplication;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.cutler.template.util.base.AppUtil;
import com.cutler.template.util.view.VideoPlayer;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;


public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init(R.id.viewPlayer1, "http://app.eyitan.com/upload/product/2015/07/22/143755023166524.mp4","http://app.eyitan.com/upload/product/2015/07/22/14375503337903.png");
//        init(R.id.viewPlayer2,"http://app.eyitan.com/upload/product/2015/07/22/143754682685738.mp4","http://app.eyitan.com/upload/product/2015/07/22/143754687330179.png");
    }

    private void init(int viewId,String videoUrl, String imgUrl) {
        VideoPlayer videoPlayer = (VideoPlayer) findViewById(viewId);
        videoPlayer.setUrl(videoUrl);
        ImageView imgIV = videoPlayer.getPreviewImageView();
        // DisplayImageOptions对象用来表示本次加载图片时的参数信息。
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                // 执行加载时，imgIV所显示的图片。
                .cacheInMemory(true)
                        // 图片加载完成后是否将其缓存在磁盘（手机内存或SD卡）上。
                .cacheOnDisk(true)
                .build();
        ImageLoader.getInstance().displayImage(imgUrl, imgIV, options);
        videoPlayer.setOnFullScreenListener(new VideoPlayer.OnFullScreenListener() {
            @Override
            public void onChanged(boolean isFullScrren) {
                if(isFullScrren){
                    getSupportActionBar().hide();
                    AppUtil.openFullScreen(MainActivity.this);
                } else {
                    getSupportActionBar().show();
                    AppUtil.closeFullScreen(MainActivity.this);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
