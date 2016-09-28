package softwarecenter.wt.com.softwarecenter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import softwarecenter.wt.com.softwarecenter.bean.Alarm;

/**
 * Created by kouzeping on 2016/9/26.
 * email：kouzeping@shmingjiang.org.cn
 */

public class alarmActivity extends AppCompatActivity {

    private  AlarmAdatper mAdatper;
    private RecyclerView AlarmRecyclerview;
    private List<Alarm> datas = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_layout);
        findview();
        initData();
        mAdatper = new AlarmAdatper(datas);

        AlarmRecyclerview.setAdapter(mAdatper);
        AlarmRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        AlarmRecyclerview.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        AlarmRecyclerview.setItemAnimator(new DefaultItemAnimator());

    }

    private void findview() {

        AlarmRecyclerview = (RecyclerView) findViewById(R.id.alarmrecyview);
    }
    protected void initData()
    {

        Alarm alarm = new Alarm("a", 1,"a","a","a");
        datas.add(alarm);
    }

}
