package com.example.nottypaddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class content_notty extends AppCompatActivity {

    EditText ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_notty);

        ed=findViewById(R.id.mt);

        Intent intent=getIntent();
        final int notesId=intent.getIntExtra("notesID",-1);
        ed.setText(MainActivity.notesdescript.get(notesId));
        if(notesId!=-1)
        {
            ed.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    MainActivity.notesdescript.set(notesId,String.valueOf(charSequence));
            //        MainActivity.adapter.notifyDataSetChanged();
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });


        }

    }
}
