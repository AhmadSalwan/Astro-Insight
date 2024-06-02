package com.example.finallabmobile2024.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finallabmobile2024.API.Api.ApiClient;
import com.example.finallabmobile2024.API.Api.ApiService;
import com.example.finallabmobile2024.API.Api.Response;
import com.example.finallabmobile2024.Models.APOD;
import com.example.finallabmobile2024.R;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.StyledPlayerView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class Main extends Fragment {
    private Handler handler = new Handler(Looper.getMainLooper());
    ExecutorService service= Executors.newFixedThreadPool(2);
    ExoPlayer player;
    TextView date,title,content;
    String url;
    View view;
    private ApiService apiService;
    APOD apod;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_main, container, false);
        date=view.findViewById(R.id.main_date);
        title=view.findViewById(R.id.main_title);
        content=view.findViewById(R.id.main_explanation);


        StyledPlayerView playerView=view.findViewById(R.id.videoPlayer);
        player=new ExoPlayer.Builder(requireContext()).build();
        playerView.setPlayer(player);
        Retrofit retrofit = ApiClient.getClient();
        apiService=retrofit.create(ApiService.class);

        fetchData();
        return view;

    }
    public void fetchData(){
        Call<Response> call=apiService.getData();
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful() && response.body() != null) {
                    APOD apod = response.body().getData();
                    if (apod != null) {
                        url = apod.getUrl();
                        title.setText(apod.getTitle());
                        date.setText(apod.getDate());
                        content.setText(apod.getExplanation());

                        MediaItem mediaItem = MediaItem.fromUri(url);
                        player.setMediaItem(mediaItem);
                        player.prepare();
                        player.setPlayWhenReady(true);
                    }
                } else {
                    Toast.makeText(requireContext(), "Failed to retrieve data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(requireContext(),"Failed",Toast.LENGTH_SHORT).show();
                date.setText("Mengulang");
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        if (player != null) {
            player.setPlayWhenReady(false);
            player.release();
            player = null;
        }
    }

}
