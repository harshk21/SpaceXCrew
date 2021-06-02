package com.hk210.spacexcrew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DeatilsViewActivity extends AppCompatActivity {

    private TextView name, stat, agen, weblink;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatils_view);

        name = findViewById(R.id.name_act);
        stat = findViewById(R.id.status_act);
        agen = findViewById(R.id.agency_act);
        weblink = findViewById(R.id.linl_act);
        image = findViewById(R.id.image_act);

        getIncomingIntent();
    }

    private void getIncomingIntent() {
        if(getIntent().hasExtra("image_url") && getIntent().hasExtra("name") && getIntent().hasExtra("agency") && getIntent().hasExtra("status") && getIntent().hasExtra("web")){
            String imageurl, full, ag, st, wb;
            imageurl = getIntent().getStringExtra("image_url");
            full = getIntent().getStringExtra("name");
            ag = getIntent().getStringExtra("status");
            st = getIntent().getStringExtra("agency");
            wb = getIntent().getStringExtra("web");

            Glide.with(this).load(imageurl).into(image);
            name.setText(full);
            agen.setText(ag);
            stat.setText(st);
            weblink.setText(wb);
            weblink.setOnClickListener(v -> {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(wb));
                startActivity(i);
            });

        }
    }
}