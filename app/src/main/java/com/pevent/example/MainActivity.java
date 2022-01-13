package com.pevent.example;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.pevent.lib.PEvent;
import com.pevent.lib.manager.PServiceManager;
import com.pevent.lib.annotations.Route;
import com.pevent.lib.annotations.thread.MainThread;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.tv = this.findViewById(R.id.tv);

        this.findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PEvent pEvent = PEvent.newBuilder(MainActivity.this).setAuthority("com.pevent.example2").build();
                Bundle bundle = pEvent.route("/show/user").withString("userid", "Super-Man").post();
                if (bundle != null) {
                    Log.e(TAG, "bundle:" + bundle.toString());
                    tv.setText(bundle.getString("username"));
                } else {
                    Log.e(TAG, "bundle == null");
                }
            }
        });

        PServiceManager.getInstance().publish(this);
    }

    @MainThread
    @Route("/show/age")
    public void showAge(final Bundle in, Bundle out) {
        out.putString("age", "10");
        String name = in.getString("age");
        tv.setText(name);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PServiceManager.getInstance().unPublish(this);
    }

}
