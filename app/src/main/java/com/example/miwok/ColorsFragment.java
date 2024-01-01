package com.example.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsFragment extends Fragment {

    private MediaPlayer mMediaPlayer;

    // global variable for releasing media (to avoid reassigning of resources)
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    public ColorsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        final ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("red", "wetetti",R.drawable.color_red, R.raw.color_red));
        words.add(new Word("mustard yellow", "chiwiita", R.drawable.color_green, R.raw.color_mustard_yellow));
        words.add(new Word("dusty yellow", "topiisa",R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new Word("green", "chokokki",R.drawable.color_green, R.raw.color_green));
        words.add(new Word("brown", "takaakki",R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word("grey", "topoppi",R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word("black", "kululli",R.drawable.color_black, R.raw.color_black));
        words.add(new Word("white", "kelilli",R.drawable.color_white, R.raw.color_white));


        // ArrayAdapter parameters  ----->  context  ;    resource ID ; list of objects
        // We need resource because xml file needs to access list of objects from java file
        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_colors);

        ListView listView = rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);
                releaseMediaPlayer();
                mMediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResourceId());
                mMediaPlayer.start();

                // releasing media player on completion of song(music)
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });

        return rootView;
    }


    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    // method for releasing media after being played
    private void releaseMediaPlayer(){
        if(mMediaPlayer != null){
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

}