package com.example.ahoreader;


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
public class IndNovel extends Fragment {

    public static int chapterind = -1;
    TextView textind;
    Button next2;
    Button previous2;

    Intent intent;
    int[] engNovels, indNovels;

    public IndNovel() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ind_novel, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textind = (TextView) getView().findViewById(R.id.novel2);
        previous2 = (Button) getView().findViewById(R.id.previous2);
        next2 = (Button) getView().findViewById(R.id.next2);

        intent = getActivity().getIntent();
        engNovels = intent.getIntArrayExtra("engNovels");
        indNovels = intent.getIntArrayExtra("indNovels");

        if (chapterind == -1) {
            chapterind = 0;
        }

        if (chapterind == 0) {
            previous2.setVisibility(View.GONE);
        }

        if (indNovels.length == chapterind + 1) {
            next2.setVisibility(View.GONE);
        }

        textind.setText(readTxt(indNovels[chapterind]));

        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chapterind++;
                Intent i = new Intent(getContext(), Reader.class);
                i.putExtra("engNovels", engNovels);
                i.putExtra("indNovels", indNovels);
                getActivity().finish();
                getActivity().startActivity(i);
            }
        });

        previous2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chapterind--;
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
