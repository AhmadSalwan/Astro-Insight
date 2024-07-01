package com.example.finallabmobile2024.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.finallabmobile2024.API.Api.ApiClient;
import com.example.finallabmobile2024.API.Api.ApiService;
import com.example.finallabmobile2024.R;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main extends Fragment {
    private Handler handler = new Handler(Looper.getMainLooper());
    ExecutorService service= Executors.newFixedThreadPool(2);

    TextView date,title,content;
    ImageView imageView;
    ProgressBar progressBar;
    String url;
    View view;
    YouTubePlayerView player;
    private ApiService apiService;
    com.example.finallabmobile2024.Models.Response apod;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_main, container, false);
        date=view.findViewById(R.id.main_date);
        title=view.findViewById(R.id.main_title);
        content=view.findViewById(R.id.main_explanation);
        content.setMovementMethod(new ScrollingMovementMethod());
        imageView=view.findViewById(R.id.imageView);
        player =view.findViewById(R.id.videoPlayer);
        progressBar=view.findViewById(R.id.progress_bar);
//        Set Gone
        date.setVisibility(View.GONE);
        title.setVisibility(View.GONE);
        content.setVisibility(View.GONE);
        imageView.setVisibility(View.GONE);
        player.setVisibility(View.GONE);

        getLifecycle().addObserver(player);
        fetchData();
        return view;

    }

    public void fetchData(){
        apiService=ApiClient.getClient();
        Call<com.example.finallabmobile2024.Models.Response> client=apiService.getData();

        client.enqueue(new Callback<com.example.finallabmobile2024.Models.Response>() {

            @Override
            public void onResponse(Call<com.example.finallabmobile2024.Models.Response> call, Response<com.example.finallabmobile2024.Models.Response> response) {
                if (response.isSuccessful() && response.body() != null) {
                    apod = response.body();
                    Log.d("API Response", "Date: " + apod.getDate());
                    Log.d("API Response", "Title: " + apod.getTitle());
                    Log.d("API Response", "Explanation: " + apod.getExplanation());
                    Log.d("API Response", "MediaType: " + apod.getMediaType());
                    Log.d("API Response", "URL: " + apod.getUrl());
                    if (apod != null) {
                        Observer observer=new Observer(Main.this);
                        getLifecycle().addObserver(observer);
                        progressBar.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if(isAdded()){
                                    getLifecycle().removeObserver(observer);
                                    progressBar.setVisibility(View.GONE);
                                    date.setVisibility(View.VISIBLE);
                                    title.setVisibility(View.VISIBLE);
                                    content.setVisibility(View.VISIBLE);
                                    imageView.setVisibility(View.VISIBLE);
                                    player.setVisibility(View.VISIBLE);
                                    date.setText(apod.getDate());
                                    title.setText(apod.getTitle());
                                    content.setText(apod.getExplanation());

                                    if ("video".equals(apod.getMediaType())) {
                                        imageView.setVisibility(View.GONE);
                                        player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                                            @Override
                                            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                                                String videoId;
                                                String pattern = "/embed/([^/?]+)";
                                                Pattern compiledPattern = Pattern.compile(pattern);
                                                Matcher matcher = compiledPattern.matcher(apod.getUrl());
                                                if (matcher.find()) {
                                                    videoId = matcher.group(1);
                                                    youTubePlayer.loadVideo(videoId, 0);
                                                }
                                            }
                                        });
                                        Log.d("ExoPlayer", "Setting media item to player");
                                    } else {
                                        player.setVisibility(View.INVISIBLE);
                                        Glide.with(requireContext()).load(apod.getUrl()).into(imageView);
                                    }
                                }

                            }
                        }, 2000); // 2000 milliseconds delay
                    }
                }
            }
            @Override
            public void onFailure(Call<com.example.finallabmobile2024.Models.Response> call, Throwable t) {

            }
        });
    }

}
