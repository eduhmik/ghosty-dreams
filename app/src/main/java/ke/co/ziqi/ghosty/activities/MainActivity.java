package ke.co.ziqi.ghosty.activities;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import ke.co.ziqi.ghosty.R;
import ke.co.ziqi.ghosty.adapters.ListAdapter;
import ke.co.ziqi.ghosty.database.GhostyDatabase;
import ke.co.ziqi.ghosty.models.Stories;

public class MainActivity extends AppCompatActivity {

    //global items
    FloatingActionButton fab;
    RecyclerView rvList;
    ListAdapter adapter;
    List<Stories> list = new ArrayList<>();
    GhostyDatabase db;
    Dialog dialog;
    PopulateDbAsync task;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //initialize items including onclick
        db = GhostyDatabase.getData(MainActivity.this);
        fab  = findViewById(R.id.fab);
        rvList = findViewById(R.id.rv_list);
        adapter = new ListAdapter(MainActivity.this,list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        rvList.setLayoutManager(layoutManager);
        rvList.setAdapter(adapter);
        dialog = new Dialog(MainActivity.this);
        task = new PopulateDbAsync(db);




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog.isShowing())
                    dialog.dismiss();


                dialog.setContentView(R.layout.insert_dialog);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                final EditText title, message;
                Button submit;

                title = dialog.findViewById(R.id.title);
                message = dialog.findViewById(R.id.message);
                submit = dialog.findViewById(R.id.bt_start);

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (title.getText().toString().length()!=0 && message.getText().toString().length()!=0) {
                            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

                            long time = calendar.getTimeInMillis();
                            if (list.size() > 0) {
                                list.clear();
                            }

                            //add data to db
                            list.add(new Stories(title.getText().toString(),message.getText().toString(),time));
                            db.dao().insertAll(list);
                            //reload db
                            task = new PopulateDbAsync(db);
                            dialog.dismiss();



                        }else {
                            Toast.makeText(MainActivity.this,"Both header and message are required",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();

            }
        });


        //load data to db on create
        task.execute();

    }

    private  class PopulateDbAsync extends AsyncTask<Void, Void,Void>{

        private  final GhostyDatabase db;

        PopulateDbAsync(GhostyDatabase db){
            this.db = db;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            list.addAll(db.dao().getAll());

           updateView();


            return null;
        }
    }
    private void updateView (){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }
}
