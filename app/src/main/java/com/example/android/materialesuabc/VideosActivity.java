package com.example.android.materialesuabc;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class VideosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        ListView listview = (ListView) findViewById(R.id.listview_videos);
        Intent intent = getIntent();
        String [] optionsName;
        ArrayAdapter<String> adapter = null;
        String[] videosURLs = null;
        if(intent.getIntExtra("id",0) ==0){
            //Ceramica
            optionsName = getResources().getStringArray(R.array.videos_ceramica_list);
            adapter = new ArrayAdapter<String>(VideosActivity.this,android.R.layout.simple_list_item_1,optionsName);
            videosURLs = new String[]{
                    "https://youtu.be/ObzyEC5hdAg","https://youtu.be/6JvA56qo_hU",
                    "https://youtu.be/Ze2Nd7wA10w"
            };

        }
        if(intent.getIntExtra("id",0) ==1){
            //Metal
            optionsName = getResources().getStringArray(R.array.videos_metal_list);
            adapter = new ArrayAdapter<String>(VideosActivity.this,android.R.layout.simple_list_item_1,optionsName);
            videosURLs = new String[]{
                    "https://youtu.be/AJnmK_9F3bs","https://youtu.be/En1_Q5A554w",
                    "https://youtu.be/gWTAlyA7fWA",
            };
        }
        if(intent.getIntExtra("id",0) ==2){
            //Plastico
            optionsName = getResources().getStringArray(R.array.videos_plastico_list);
            adapter = new ArrayAdapter<String>(VideosActivity.this,android.R.layout.simple_list_item_1,optionsName);
            videosURLs = new String[]{
                    "https://youtu.be/ObzyEC5hdAg","https://youtu.be/6JvA56qo_hU",
                    "https://youtu.be/Ze2Nd7wA10w"
            };
        }
        if(intent.getIntExtra("id",0) ==3){
            //Vidrio
            optionsName = getResources().getStringArray(R.array.videos_vidrio_list);
            adapter = new ArrayAdapter<String>(VideosActivity.this,android.R.layout.simple_list_item_1,optionsName);
            videosURLs = new String[]{
                    "https://youtu.be/ObzyEC5hdAg","https://youtu.be/6JvA56qo_hU",
                    "https://youtu.be/Ze2Nd7wA10w"
            };

        }
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=Hxy8BZGQ5Jo")));
                Log.d("VideosActiviy","i: "+i);
            }
        });

    }
}
