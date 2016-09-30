package softwarecenter.wt.com.softwarecenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import softwarecenter.wt.com.softwarecenter.service.ScanService;


/**
 * Created by duanshiyao on 2016/9/22.
 * email：
 */

public class WelcomeActivity extends Activity {
    private RelativeLayout  welcomeRelativelayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);
        welcomeRelativelayout = (RelativeLayout) findViewById(R.id.welcome_rlayout);
        Intent intent=new Intent(this,ScanService.class);
        startService(intent);
        onclick();
    }

    private void onclick() {
        welcomeRelativelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
