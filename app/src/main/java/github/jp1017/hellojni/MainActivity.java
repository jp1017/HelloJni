package github.jp1017.hellojni;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /**********************************   jni start *********************************/
    /**
     * 静态代码块加载库
     */
    static {
        System.loadLibrary("hello_jni");
    }

    public native String staticRegFromJni();      //静态方法注册
    public native String dynamicRegFromJni();     //动态方法注册

    /**********************************   jni end  *********************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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

    /**
     * Button 点击事件
     * @param view
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_static:
                Log.d("loglog", staticRegFromJni());
                Toast.makeText(this, staticRegFromJni(), Toast.LENGTH_SHORT).show();
                break;

            case R.id.bt_dynamic:
                Log.d("loglog", dynamicRegFromJni());
                Toast.makeText(this, dynamicRegFromJni(), Toast.LENGTH_SHORT).show();
        }
    }
}
