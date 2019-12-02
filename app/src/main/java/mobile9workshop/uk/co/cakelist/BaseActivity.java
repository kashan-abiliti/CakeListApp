package mobile9workshop.uk.co.cakelist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import org.jetbrains.annotations.Nullable;

public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // call this method to restart app
    protected void restartApplication(){

    }

    @Override
    protected void onResume(){
        super.onResume();

    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onStart(){
        super.onStart();
    }

}
