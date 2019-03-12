package edu.purdue.bartleyt.complex_counter;

import android.app.ListActivity;
import android.database.DataSetObserver;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends ListActivity {
    ArrayList<Counters> counters = new ArrayList<Counters>();
    Button addNewBtn;
    Button autoBtn;
    ListView listView;
    ArrayAdapter<Counters> adapter;
    public static int lastCounterClicked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Bundle bundle = new Bundle();

        addNewBtn = (Button) findViewById(R.id.addNewBtn);
        listView = getListView();
        autoBtn = (Button) findViewById(R.id.autoBtn);

        /**
         * AutoButton function
         */
        autoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i<counters.size(); i++){
                    counters.get(i).setCounterValue(counters.get(i).getCounterValue() + 1);
                }

                adapter.notifyDataSetChanged();
            }
        });

        /**
         * AddNew Button Function
         */
        addNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addNew = new Intent(getApplicationContext(), AddNew.class);
                startActivityForResult(addNew, 0);
            }
        });


        if(counters.size() == 0) {
            counters.add(new Counters("New counter", 0));
        }

        adapter = new ArrayAdapter<>(getListView().getContext(), android.R.layout.simple_list_item_1, counters);

        listView.setAdapter(adapter);

        /**
         * List View Function
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), Counter.class);
                i.putExtra("COUNTER_VALUE", counters.get(position).getCounterValue());
                startActivityForResult(i, 1);
                lastCounterClicked = position;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 0){
            if(resultCode == RESULT_OK){
                counters.add(new Counters(data.getData().toString(), 0));
                adapter.notifyDataSetChanged();
            }
        }

        if (requestCode == 1){
            if(resultCode == RESULT_OK){
                counters.get(lastCounterClicked).setCounterValue(Integer.parseInt(data.getData().toString()));
                adapter.notifyDataSetChanged();
            }
        }
    }
}
