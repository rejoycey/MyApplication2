package com.edu.swufe.yaoyiyao;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.wifi.aware.DiscoverySession;
import android.nfc.Tag;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
//定义俩个传感器
    private SensorManager sensorManager;
    //震动
    private Vibrator vibrator;
    private static String str[]={"石头","剪子","布"};
    private static int pics[]={R.mipmap.st,R.mipmap.jz,R.mipmap.bu};
    private static final int SENSOR_SHAKE=10;

    private TextView text;
    private ImageView img;
    private  static final String TAG ="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        text=(TextView) findViewById( R.id.txtlabel );
        img=(ImageView)findViewById( R.id.imageView );
        //获得什么样传感器
        sensorManager=(SensorManager)getSystemService( SENSOR_SERVICE );
        vibrator=(Vibrator)getSystemService( VIBRATOR_SERVICE );
    }
    //注册监听器
    protected void onResume() {
        super.onResume();
        if (sensorManager!=null){
            sensorManager.registerListener( sensorEventListener,sensorManager.getDefaultSensor( Sensor.TYPE_ACCELEROMETER ),sensorManager.SENSOR_DELAY_NORMAL );
        }
    }
    //取消监听器
    protected void onStop(){
        super.onStop();
        if (sensorManager!=null){
            sensorManager.unregisterListener(sensorEventListener  );
        }
    }

    private SensorEventListener sensorEventListener=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
        //传感器信息改变时执行的方法
            float[] values = event.values;
            float x = values[0];
            float y = values[1];
            float z = values[2];
            Log.i( TAG,"x["+x+"]y["+y+"]z["+z+"]" );
            int medumValue =10;
            if (
                    Math.abs( x )>medumValue||Math.abs( y )>medumValue||Math.abs( z )>medumValue
            ){
                vibrator.vibrate( 200 );
                Message msg =new Message();
                msg.what = SENSOR_SHAKE;
                handler.sendMessage(msg);

            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
    Handler handler=new Handler(  ){
@Override
        public void handleMessage(Message msg){
            super.handleMessage( msg );
            switch (msg.what){
                case 10:
                    Log.i( TAG,"检测到摇晃，执行操作" );
                    java.util.Random r =new java.util.Random(  );
                    int num =Math.abs( r.nextInt() )%3;
                    text.setText( str[num] );
                    img.setImageResource( pics[num] );
                    break;

            }
        }
    };
}
