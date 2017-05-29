package com.example.ahoreader;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.nlopez.smartadapters.SmartAdapter;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button bt;
    EditText et;
    ProgressBar pb;
    Thread MyThread;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.ListView);
        bt = (Button) findViewById(R.id.search_button);
        et = (EditText) findViewById(R.id.editText);
        pb = (ProgressBar) findViewById(R.id.progressBar3);

        searchAdapter("");

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyThread == null) {
                    MyThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                for (int i = 0; i <= 50; i++) {

                                    final int value = i;

                                    Thread.sleep(30);
                                    pb.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            pb.setProgress(value);
                                        }
                                    });
                                }
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        searchAdapter(et.getText().toString().trim());
                                    }
                                });

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    MyThread.start();
                    MyThread = null;
                }

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Novel selected = (Novel) listView.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, selected.getJudul(), Toast.LENGTH_SHORT).show();

                Intent i = new Intent(MainActivity.this, Reader.class);
                i.putExtra("title", selected.getJudul());
                i.putExtra("engNovels", selected.getEngNovels());
                i.putExtra("indNovels", selected.getIndNovels());
                startActivity(i);

            }
        });

    }

    public void searchAdapter(String search) {
        List<Novel> novels = new ArrayList<>();
        List<Novel> cari = new ArrayList<>();

        novels.add(new Novel("Yahari Ore no Seishun Rabukome wa Machigatteiru", new int[]{R.raw.oregairuch0eng, R.raw.oregairuch1eng, R.raw.oregairuch2eng}, new int[]{R.raw.oregairuch0ind, R.raw.oregairuch1ind, R.raw.oregairuch2ind}));
        novels.add(new Novel("Kono Subarashii Sekai ni Shukufuku wo!", new int[]{R.raw.konosubach0eng, R.raw.konosubach1eng, R.raw.konosubach2eng}, new int[]{R.raw.konosubach0ind, R.raw.konosubach1ind, R.raw.konosubach2ind}));
        novels.add(new Novel("Kokoro Connect", new int[]{R.raw.kokoroch0eng, R.raw.kokoroch1eng, R.raw.kokoroch2eng}, new int[]{R.raw.kokoroch0ind, R.raw.kokoroch1ind, R.raw.kokoroch2ind}));
        novels.add(new Novel("Gekkou", new int[]{R.raw.gekkouch0eng, R.raw.gekkouch1eng, R.raw.gekkouch2eng}, new int[]{R.raw.gekkouch0ind, R.raw.gekkouch1ind, R.raw.gekkouch2ind}));
        novels.add(new Novel("Tabi ni Deyou, Horobiyuku Sekai no Hate made", new int[]{R.raw.tabich0eng, R.raw.tabich1eng, R.raw.tabich2eng}, new int[]{R.raw.tabich0ind, R.raw.tabich1ind, R.raw.tabich2ind}));
        novels.add(new Novel("Kamisu Reina", new int[]{R.raw.kamisuch0eng, R.raw.kamisuch1eng, R.raw.kamisuch2eng}, new int[]{R.raw.kamisuch0ind, R.raw.kamisuch1ind, R.raw.kamisuch2ind}));
        novels.add(new Novel("Hyouka", new int[]{R.raw.hyoukach0eng, R.raw.hyoukach1eng, R.raw.hyoukach2eng}, new int[]{R.raw.hyoukach0ind, R.raw.hyoukach1ind, R.raw.hyoukach2ind}));
        novels.add(new Novel("Nekomonogatari(Shiro)", new int[]{R.raw.monogatarich0eng, R.raw.monogatarich1eng, R.raw.monogatarich2eng}, new int[]{R.raw.monogatarich0ind, R.raw.monogatarich1ind, R.raw.monogatarich2ind}));
        novels.add(new Novel("Toaru Majutsu no Index: New Testament", new int[]{R.raw.indexch0eng, R.raw.indexch1eng, R.raw.indexch2eng}, new int[]{R.raw.indexch0ind, R.raw.indexch1ind, R.raw.indexch2ind}));
        novels.add(new Novel("Mushoku Tensei", new int[]{R.raw.mushokuch0eng, R.raw.mushokuch1eng, R.raw.mushokuch2eng}, new int[]{R.raw.mushokuch0ind, R.raw.mushokuch1ind, R.raw.mushokuch2ind}));


        for (int i = 0; i < novels.size(); i++) {
            if (novels.get(i).getJudul().toLowerCase().contains(search.toLowerCase())) {
                cari.add(novels.get(i));
            }
        }
        SmartAdapter.items(cari).map(Novel.class, NovelView.class).into(listView);
    }

}
