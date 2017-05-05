package news.yaadesh.com.newsreaderv2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopStoriesFragment extends Fragment {

    RecyclerView recyclerView;
    CardObject[] cardObject= new CardObject[100];

    int count=0;







    public TopStoriesFragment() {
        // Required empty public constructor
        System.out.println("CONSTRUCTOR");
        System.out.println(cardObject.length);

        for(int i=0; i<cardObject.length; i++){
            cardObject[i] = new CardObject();
        }




    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        System.out.println("ONCREATEVIEW FRAGMENT");

        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager((getActivity()),1, LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());



        int i;
        String keyword=null;
        for(i=0;i<5;i++){
            if(i==0){keyword="bbc-news";}
            if(i==1){keyword="cnn";}
            if(i==2){keyword="the-hindu";}
            if(i==3){keyword="the-times-of-india";}
            if(i==4){keyword="time";}


            final int finalI = i;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://newsapi.org/v1/articles?source="+keyword+"&sortBy=top&apiKey=92f30cb6874c4b29bb9bd9e330aa16b4",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            JSONArray articles = new JSONArray(jsonResponse.getString("articles"));
                            System.out.println(articles.length());



                            for (int j = 0; j < articles.length(); j++) {


                                    cardObject[count].setSource(jsonResponse.getString("source"));
                                    cardObject[count].setTitle(articles.getJSONObject(j).getString("title"));
                                    cardObject[count].setImage_url(articles.getJSONObject(j).getString("urlToImage"));
                                    cardObject[count].setDescription(articles.getJSONObject(j).getString("description"));
                                    cardObject[count].setArticle_url(articles.getJSONObject(j).getString("url"));
                                    cardObject[count].setTime(articles.getJSONObject(j).getString("publishedAt"));
                                    cardObject[count].setCategory("Top Stories");

                                    count++;
                            }
                            RecyclerAdapter mAdapter = new RecyclerAdapter(cardObject,count,getActivity());
                            recyclerView.setAdapter(mAdapter);
                            System.out.println("SET ADAPTER"+ finalI);
                            rootView.findViewById(R.id.loadingPanel).setVisibility(View.GONE);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();


                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                //params.put(CONST_ID,id);

                return params;
            }
        };


        requestQueue.add(stringRequest);


    }


        return rootView;
    }


}
