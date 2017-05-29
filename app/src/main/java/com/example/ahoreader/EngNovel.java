package com.example.ahoreader;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.example.ahoreader.Reader.novel;


/**
 * A simple {@link Fragment} subclass.
 */
public class EngNovel extends Fragment {

    Button next1;
    Button previous1;
    TextView text;
    public static int chaptereng = -1;

    Intent intent;
    int[] engNovels, indNovels;

    public EngNovel() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_eng_novel, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        text = (TextView) getView().findViewById(R.id.novel1);
        previous1 = (Button) getView().findViewById(R.id.previous1);
        next1 = (Button) getView().findViewById(R.id.next1);

        intent = getActivity().getIntent();
        engNovels = intent.getIntArrayExtra("engNovels");
        indNovels = intent.getIntArrayExtra("indNovels");

        if (chaptereng == -1) {
            chaptereng = 0;
        }

        if (chaptereng == 0) {
            previous1.setVisibility(View.GONE);
        }

        if (engNovels.length == chaptereng + 1) {
            next1.setVisibility(View.GONE);
        }

        text.setText(readTxt(engNovels[chaptereng]));

        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chaptereng++;
                Intent i = new Intent(getContext(), Reader.class);
                i.putExtra("engNovels", engNovels);
                i.putExtra("indNovels", indNovels);
                getActivity().finish();
                getActivity().startActivity(i);
            }
        });

        previous1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chaptereng--;
                Intent i = new Intent(getContext(), Reader.class);
                i.putExtra("engNovels", engNovels);
                i.putExtra("indNovels", indNovels);
                getActivity().finish();
                getActivity().startActivity(i);
            }
        });
    }

    private String readTxt(int file) {
        InputStream is = this.getResources().openRawResource(file);
//          InputStream inputStream = getResources().openRawResource(R.raw.internals);
        System.out.println(is);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;
        try {
            i = is.read();
            while (i != -1) {
                byteArrayOutputStream.write(i);
                i = is.read();
            }
            is.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return byteArrayOutputStream.toString();
    }

}
