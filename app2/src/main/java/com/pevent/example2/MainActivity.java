package com.pevent.example2;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.pevent.lib.PEvent;
import com.pevent.lib.manager.PServiceManager;
import com.pevent.lib.annotations.Route;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = this.findViewById(R.id.tv);

        PServiceManager.getInstance().publish(this);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PEvent pEvent = PEvent.newBuilder(MainActivity.this).setAuthority("com.pevent.example").build();
                Bundle bundle = pEvent.route("/show/age").withString("age", "20").post();
                if (bundle != null) {
                    tv.setText(bundle.getString("age"));
                }
            }
        });
    }

    @Route("/show/user")
    public void showUser(final Bundle in, final Bundle out) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv.setText("received " + in.getString("userid"));
            }
        });
        out.putString("username", "Spider-Man");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PServiceManager.getInstance().unPublish(this);
    }

}
