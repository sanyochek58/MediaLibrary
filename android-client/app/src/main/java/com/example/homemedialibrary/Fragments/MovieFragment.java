package com.example.homemedialibrary.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.homemedialibrary.API.ApiClient;
import com.example.homemedialibrary.API.ApiService;
import com.example.homemedialibrary.Adapters.MediaAdapter;
import com.example.homemedialibrary.DTO.MediaLibDTO;
import com.example.homemedialibrary.R;
import com.example.homemedialibrary.VideoPlayerActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieFragment extends Fragment {

    private RecyclerView recyclerView;
    private MediaAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        recyclerView = view.findViewById(R.id.recycler_movies);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loadMovies();

        return view;
    }

    private void loadMovies(){
        ApiService service = ApiClient.getApiService();
        service.getAllMediaByTypes("Movie").enqueue(new Callback<List<MediaLibDTO>>() {
            @Override
            public void onResponse(Call<List<MediaLibDTO>> call, Response<List<MediaLibDTO>> response) {
                if(response.isSuccessful() && response.body() != null){
                    adapter = new MediaAdapter(response.body(), item -> {
                        Intent intent = new Intent(getContext(), VideoPlayerActivity.class);
                        intent.putExtra("filename", item.getFilename());
                        startActivity(intent);
                    });
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<MediaLibDTO>> call, Throwable t) {
                Toast.makeText(getContext(), "Ошибка сервера", Toast.LENGTH_SHORT).show();
            }
        });
    }
}