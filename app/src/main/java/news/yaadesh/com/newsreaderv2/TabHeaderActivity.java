package news.yaadesh.com.newsreaderv2;

/**
 * Created by Yaadesh on 01-01-2017.
 */

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabHeaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.htab_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getIntent().getStringExtra("category"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        TabLayout tabLayout = (TabLayout) findViewById(R.id.htab_tabs);
        assert tabLayout != null;
        tabLayout.addTab(tabLayout.newTab().setText("SUMMARY"));
        tabLayout.addTab(tabLayout.newTab().setText("FULL ARTICLE"));


        final ViewPager viewPager2 = (ViewPager) findViewById(R.id.htab_viewpager);

        //tabLayout.setupWithViewPager(viewPager2);
        final ActivityPagerAdapter adapter = new ActivityPagerAdapter (getSupportFragmentManager(), tabLayout.getTabCount(),getIntent().getStringExtra("article_url"));
        viewPager2.setAdapter(adapter);
        viewPager2.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.htab_collapse_toolbar);
        collapsingToolbarLayout.setTitleEnabled(false);

        //collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimaryText));
        //collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.colorSecondaryText));

        //ImageView header = (ImageView) findViewById(R.id.header);

        ImageView img = (ImageView)findViewById(R.id.htab_header);

        Picasso.with(getBaseContext()).
                load(getIntent().getStringExtra("image_url"))
                .fit()
                .into(img);


        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize=4;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.header,options);


        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @SuppressWarnings("ResourceType")
            @Override
            public void onGenerated(Palette palette) {

                int vibrantColor = palette.getVibrantColor(R.color.primary_500);
                int vibrantDarkColor = palette.getDarkVibrantColor(R.color.primary_700);
                collapsingToolbarLayout.setContentScrimColor(vibrantColor);
                collapsingToolbarLayout.setStatusBarScrimColor(vibrantDarkColor);
            }
        });





    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);


    }





}

