package com.aaradhyajaindevelopers.feppes.pythonbasicmobile;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class MainActivity extends AppCompatActivity {

    TextView output;
    EditText CodeArea;
    Button Run;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = (TextView)findViewById(R.id.output);
        CodeArea = (EditText)findViewById(R.id.codearea);
        Run = (Button)findViewById(R.id.run);

        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }


        //now on click on run button we will extract code from code area data and send that data to our python script..

        Run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Python py = Python.getInstance();

                //here we call our script with the name "myscirpt
                PyObject pyobj = py.getModule("myscript");  //give python script name

                //and call main method inside script...//pass data here
                PyObject obj = pyobj.callAttr("main", CodeArea.getText().toString());

                //here we will set returned value of our python script to our output textview
                output.setText(obj.toString());
            }
        });


    }
}