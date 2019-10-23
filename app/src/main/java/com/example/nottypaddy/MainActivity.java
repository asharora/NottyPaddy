package com.example.nottypaddy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ListView lv;
   static ArrayAdapter<String> adapter;
static ArrayList<String> notes=new ArrayList<>();
static ArrayList<String> notesdescript=new ArrayList<>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_add:
                LayoutInflater inflater=this.getLayoutInflater();
                View v=inflater.inflate(R.layout.content_titles,null);
                final EditText titname=v.findViewById(R.id.tit_name);
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Add new note");
                builder.setView(v);
                builder.setMessage("please enter the note title:");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        notesdescript.add("Notes: "+(notes.size()+1));
                        notes.add(titname.getText().toString().trim());
                        adapter.notifyDataSetChanged();

                    }
                });
                builder.setNegativeButton("No",null);
                AlertDialog dialog=builder.create();
                dialog.show();



                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv=findViewById(R.id.lv);

        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,notes);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(MainActivity.this, content_notty.class);
                intent.putExtra("notesID", i);
                startActivity(intent);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int j, long l) {

                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Delete the List");
                builder.setMessage("Do you want to delete the element");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        notes.remove(j);
                        notesdescript.remove(j);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("No",null);
                AlertDialog dialog=builder.create();
                dialog.show();

                return true;
            }
        });
    }
}
