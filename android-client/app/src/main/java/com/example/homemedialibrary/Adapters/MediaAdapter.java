package com.example.homemedialibrary.Adapters;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homemedialibrary.DTO.MediaLibDTO;
import com.example.homemedialibrary.R;

import java.util.List;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.MediaViewHolder> {

    private List<MediaLibDTO> mediaList;
    private OnItemClickListener listener;

    public MediaAdapter(List<MediaLibDTO> items, OnItemClickListener listener){
        this.mediaList = items;
        this.listener = listener;

    }

    @NonNull
    @Override
    public MediaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_media,parent,false);
        return  new MediaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MediaViewHolder holder, int position){
        holder.bind(mediaList.get(position),listener);
    }

    @Override
    public int getItemCount(){
        return mediaList.size();
    }

    public static class MediaViewHolder extends RecyclerView.ViewHolder {
        private final TextView name, type, description, date;
        private final ImageView icon;

        public MediaViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text_name);
            type = itemView.findViewById(R.id.text_type);
            description = itemView.findViewById(R.id.text_description);
            date = itemView.findViewById(R.id.text_date);
            icon = itemView.findViewById(R.id.image_type);
        }

        public void bind(MediaLibDTO media, OnItemClickListener listener) {
            name.setText(media.getName());
            type.setText("Тип: " + media.getMediaType());
            description.setText(media.getDescription());
            date.setText("Дата: " + media.getDate());


            String mediaType = media.getMediaType();
            if ("Book".equalsIgnoreCase(mediaType)) {
                icon.setImageResource(R.drawable.new_book);
            } else if ("Movie".equalsIgnoreCase(mediaType)) {
                icon.setImageResource(R.drawable.new_movie);
            } else if ("Music".equalsIgnoreCase(mediaType)) {
                icon.setImageResource(R.drawable.new_music);
            } else {
                icon.setImageResource(android.R.drawable.ic_menu_help);
            }

            itemView.setOnClickListener(v -> listener.onItemClick(media));
        }
    }
}
