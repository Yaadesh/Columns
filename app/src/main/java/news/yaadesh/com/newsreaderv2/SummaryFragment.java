package news.yaadesh.com.newsreaderv2;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yaadesh on 06-01-2017.
 */
public class SummaryFragment extends Fragment {
    String url;
    String id;
    public SummaryFragment() {
        System.out.println("SUMMARY EMPTY CONSTRUCTOR");
        // Required empty public constructor

    }

    @SuppressLint("ValidFragment")
    public SummaryFragment(String url,String id){
        this.url= url;
        this.id =id;
         System.out.println("SUMMARY CONSTRUCTOR");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final String SERVER_URL="http://192.168.1.10:8000/"+id;
        final View rootView = inflater.inflate(R.layout.fragment_tab, container, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, SERVER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        TextView title=(TextView)rootView.findViewById(R.id.title);

                        Typeface helveticaNeue = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueLt.ttf");
                        Typeface helveticaNeueMed = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueMedium.ttf");
                        title.setTypeface(helveticaNeueMed);

                        try {
                            title.setText(getActivity().getIntent().getStringExtra("title"));


                        TextView source_name =(TextView)rootView.findViewById(R.id.source_name);
                        String source =getActivity().getIntent().getStringExtra("source").replace("-"," ");
                        source_name.setText("source:"+source);





                        TextView summary = (TextView)rootView.findViewById(R.id.paragraph);
                            summary.setTypeface(helveticaNeue);
                        System.out.println("");
                        System.out.println(response);
                        summary.setText(response);
                        rootView.findViewById(R.id.loadingPanel_in_article).setVisibility(View.GONE);
                        }
                        catch(NullPointerException e ){
                            Log.e("skbkdvh",e.toString());
                            System.out.println("NULL POINTER EXCEPTION<<<<<");
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        TextView summary = (TextView)rootView.findViewById(R.id.paragraph);
                        summary.setText("Network error.Check Network Connection.");

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("url", url);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                4,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);

        System.out.println("ONCREATEVIEW FRAGMENT");






        return rootView;
    }
}
