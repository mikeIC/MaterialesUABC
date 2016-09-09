package com.example.android.materialesuabc;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.net.URISyntaxException;

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
        int id = (int) intent.getLongExtra("id",0);
        int materiaID = intent.getIntExtra("materiaId",0);
        if(materiaID==1){
            id = id+4;
            Log.d("VideosActivity","materiaId = 1, id = id+4, id= "+id);
        }

        if(materiaID == 0){
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.Green)));
        }
        if(materiaID == 1){
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.Blue)));
        }
        if(materiaID == 2){
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.Orange)));
        }
        if(id ==0){
            //Ceramica
            optionsName = getResources().getStringArray(R.array.videos_ceramica_list);
            adapter = new ArrayAdapter<String>(VideosActivity.this,android.R.layout.simple_list_item_1,optionsName);
            videosURLs = new String[]{
                    "https://youtu.be/ObzyEC5hdAg","https://youtu.be/6JvA56qo_hU",
                    "https://youtu.be/Ze2Nd7wA10w"
            };

        }
        if(id ==1){
            //Metal
            optionsName = getResources().getStringArray(R.array.videos_metal_list);
            adapter = new ArrayAdapter<String>(VideosActivity.this,android.R.layout.simple_list_item_1,optionsName);
            videosURLs = new String[]{
                    "https://youtu.be/AJnmK_9F3bs",
                    "https://youtu.be/En1_Q5A554w",
                    "https://youtu.be/gWTAlyA7fWA",
                    "https://youtu.be/MQegHeKAEdM",
                    "https://youtu.be/tlIgoX_vRwQ",
                    "https://youtu.be/9H6rZQpMD8s",
                    "https://youtu.be/JahWcyfClBY",
                    "https://youtu.be/t0nVhryZVBw",
                    "https://youtu.be/bTuTE-dGU9M"
            };
        }
        if(id ==2){
            //Plastico
            optionsName = getResources().getStringArray(R.array.videos_plastico_list);
            adapter = new ArrayAdapter<String>(VideosActivity.this,android.R.layout.simple_list_item_1,optionsName);
            videosURLs = new String[]{
            };
        }
        if(id ==3){
            //Vidrio
            optionsName = getResources().getStringArray(R.array.videos_vidrio_list);
            adapter = new ArrayAdapter<String>(VideosActivity.this,android.R.layout.simple_list_item_1,optionsName);
            videosURLs = new String[]{
            };

        }
        if(id ==4){
            optionsName = getResources().getStringArray(R.array.materiales_ceramicos_videos_list_ceramica);
            adapter = new ArrayAdapter<String>(VideosActivity.this,android.R.layout.simple_list_item_1,optionsName);
            videosURLs = new String[]{
                    //materiales ceramicos: ceramica
                    "https://youtu.be/pdlVaa8nAr8","https://youtu.be/mR9jnEY2gWo",
                    "https://youtu.be/cXbiS345MBg",
                    "https://youtu.be/f8Ovkf8Lp9Q","https://youtu.be/8ToNYtDgXtI"
            };
        }
        if(id ==5) {
            optionsName = getResources().getStringArray(R.array.materiales_ceramicos_videos_list_lba);
            adapter = new ArrayAdapter<String>(VideosActivity.this, android.R.layout.simple_list_item_1, optionsName);
            videosURLs = new String[]{
                    //materiales ceramicos: ladrillos, baldosas, azulejos
                    "https://youtu.be/dnr_sj63o3Q","https://youtu.be/e5pwPZyU9YE",
                    "https://youtu.be/jJLkYvoGXFc"
            };
        }
        if(id ==6) {
            optionsName = getResources().getStringArray(R.array.materiales_ceramicos_videos_list_vidrio);
            adapter = new ArrayAdapter<String>(VideosActivity.this, android.R.layout.simple_list_item_1, optionsName);
            videosURLs = new String[]{
                    //materiales ceramicos: Vidrio
                    "https://youtu.be/8dZMfpAz7jo","https://youtu.be/dvBvN1tHm2s",
                    "https://youtu.be/nefJPw9_YE0","https://youtu.be/Ax_wJyHImHU",
                    "https://youtu.be/AI4uZec8jZE","https://youtu.be/q1bsP9ZnvNs",
                    "https://youtu.be/AmISHKjyVz4","https://youtu.be/1Va0rdl68uU",
                    "https://youtu.be/Zf460WKs8Jw"
            };
        }
        if(id ==7) {
            optionsName = getResources().getStringArray(R.array.materiales_ceramicos_videos_list_botellas);
            adapter = new ArrayAdapter<String>(VideosActivity.this, android.R.layout.simple_list_item_1, optionsName);
            videosURLs = new String[]{
                    //materiales ceramicos: Botellas
                    "https://youtu.be/qWWdCSt2zDU",
                    "https://youtu.be/x14ab-4khjQ"
            };
        }
        if(id==8) {
            optionsName = getResources().getStringArray(R.array.materiales_ceramicos_videos_list_canicas);
            adapter = new ArrayAdapter<String>(VideosActivity.this, android.R.layout.simple_list_item_1, optionsName);
            videosURLs = new String[]{
                    //materiales ceramicos: Canicas
                    "https://youtu.be/suz0JxkDLhQ",
                    "https://youtu.be/-rC2nljv8PE"
            };
        }
        if(id ==9) {
            optionsName = getResources().getStringArray(R.array.materiales_ceramicos_videos_list_fibra);
            adapter = new ArrayAdapter<String>(VideosActivity.this, android.R.layout.simple_list_item_1, optionsName);
            videosURLs = new String[]{
                    //materiales ceramicos: Fibra
                    "https://youtu.be/WtLtOfd8Nxo",
                    "https://youtu.be/UcNlRRLowuU"
            };
        }


        listview.setAdapter(adapter);

        final String[] finalVideosURLs = videosURLs;
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try{
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(finalVideosURLs[i])));

                }catch (NullPointerException e){
                    Log.d("VideosActivity","Error: "+e);
                }

            }
        });

    }
}
