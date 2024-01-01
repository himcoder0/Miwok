package com.example.miwok;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.sql.Array;
import java.util.List;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceId;
    // constructor
    public WordAdapter(@NonNull Context context, @NonNull List<Word> words, int mColorResourceId) {
        super(context, 0, words);
        this.mColorResourceId = mColorResourceId;
    }

    @NonNull
    @Override // overriding getView method
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent,false);
        }

        Word currentWord = getItem(position);

        // setting miwok TextView
        TextView miwokTextview = (TextView) listItemView.findViewById(R.id.miwok_text);
        miwokTextview.setText(currentWord.getMiwokTranslation());

        // setting default TextView
        TextView defaultTextview = (TextView) listItemView.findViewById(R.id.default_text);
        defaultTextview.setText(currentWord.getDefaultTranslation());

        ImageView miwokImageview = listItemView.findViewById(R.id.image_iv);

        if(currentWord.hasImage()){
            miwokImageview.setImageResource(currentWord.getImageResourceId());
            miwokImageview.setVisibility(View.VISIBLE);  // set image visibility to visible
                                                         // (as its constant value and might have been set to gone)
        }else{
            miwokImageview.setVisibility(View.GONE);
        }

        // setting background color over both text boxes in view group
        View textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), mColorResourceId); // extracting color associated with given colorID
        textContainer.setBackgroundColor(color);

        return listItemView;
    }
}
