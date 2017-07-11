package com.mykotlinapplication.niit_content;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

/**
 * Created by sandeep.singh on 6/30/2017.
 */

public class MyNewClickListener  extends Activity{

    private final View.OnClickListener mClickListener =new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(MyNewClickListener.this, "toast "+view.getTag(), Toast.LENGTH_SHORT).show();
        }
    } ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





    }
}
