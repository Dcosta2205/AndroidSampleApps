package com.xyz.multiplerecyclerviewtypes;

import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AudioViewHolder extends RecyclerView.ViewHolder {
    private ItemClickListener itemClickListener;
    private TextView mTvTitle;
    private Button mBtnPlay;
    private Button mBtnPause;

    public AudioViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
        super(itemView);
        this.itemClickListener = itemClickListener;
        initViews(itemView);
    }

    private void initViews(View itemView) {
        mTvTitle = itemView.findViewById(R.id.tvSongTitle);
        mBtnPause = itemView.findViewById(R.id.btnPauseAudio);
        mBtnPlay = itemView.findViewById(R.id.btnPlayAudio);
    }

    public void setData(Model model) {
        mTvTitle.setText(model.getContent());
        final MediaPlayer mediaPlayer = MediaPlayer.create(mTvTitle.getContext(),model.getResId());
        mBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                }
            }
        });

        mBtnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
            }
        });
    }
}
