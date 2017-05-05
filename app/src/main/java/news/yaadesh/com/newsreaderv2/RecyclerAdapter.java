package news.yaadesh.com.newsreaderv2;

/**
 * Created by Yaadesh on 14-12-2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.BreakIterator;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Yaadesh on 09-07-2016.
 */
public class RecyclerAdapter  extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>  {

    Activity activity;
    CardObject[] cardObjects;
    int count;

    List<CardObject> list_cards = new ArrayList<CardObject>();



    public RecyclerAdapter(CardObject[] cardObjects,int count, Activity activity) {
        System.out.println("ADAPTER CONSTRUCTOR");

        this.cardObjects= cardObjects;
        this.activity=activity;
        this.count=count;
        activity.findViewById(R.id.loadingPanel).setVisibility(View.GONE);


        for(int i=0;i<cardObjects.length;i++)list_cards.add(this.cardObjects[i]);



        try {
            Collections.sort(list_cards, new Comparator<CardObject>() {
                @Override
                public int compare(CardObject lhs, CardObject rhs) {
                    DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                    try {
                        if (lhs.getTime() == null || rhs.getTime() == null) {
                            return 1;
                        }
                        //System.out.println(dateFormatter.parse(lhs.getTime())+" IN DATEformattwr");
                        //System.out.println(lhs.getSource()+"IN DATE FORMATTER");
                        else {
                            return (dateFormatter.parse(lhs.getTime())).compareTo(dateFormatter.parse(rhs.getTime()));
                        }


                    } catch (ParseException e) {
                        e.printStackTrace();
                        return 0;
                    }
                }
            });
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }


    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_child, parent, false));
    }



    @Override
    public int getItemCount() {

        return count-1;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView headline;
        ImageView image;
        TextView description;
        public ViewHolder(View itemView) {

            super(itemView);


            image =(ImageView)itemView.findViewById(R.id.image);
            headline =(TextView)itemView.findViewById(R.id.headline);
            description = (TextView)itemView.findViewById(R.id.description);

            Typeface helveticaBold = Typeface.createFromAsset(activity.getAssets(), "fonts/HelveticaBold.ttf");
            this.headline.setTypeface(helveticaBold);

            Typeface helvetica = Typeface.createFromAsset(activity.getAssets(), "fonts/HelveticaBold.ttf");
            this.description.setTypeface(helvetica);




        }

    }
    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {
    try{
        System.out.println("ONBINDVIEW HOLDER");
        System.out.println(list_cards.get(position).getSource());

        if(list_cards.get(position).getDescription()==null||list_cards.get(position).getTitle()==null||list_cards.get(position).getDescription()=="null"||list_cards.get(position).getTitle()=="null") {
            throw new Exception("NULL VALUE DETECTED IN TITLE OR DESCTIPTION AT"+position+list_cards.get(position).getSource());
        }
        holder.headline.setText(list_cards.get(position).getTitle());


        if (list_cards.get(position).getDescription().length() > 150) {
            String temp = truncateAfterWords(23, list_cards.get(position).getDescription());
            holder.description.setText(temp + "....");
        } else {
            holder.description.setText(list_cards.get(position).getDescription());
        }

        System.out.println("POSITION" + position);

        Picasso.with(activity).
                load(list_cards.get(position).getImage_url())
                .fit()
                .into(holder.image);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("CARD CLICKED");
                Intent intent = new Intent(activity,TabHeaderActivity.class);
                intent.putExtra("title",list_cards.get(position).getTitle());
                intent.putExtra("image_url",list_cards.get(position).getImage_url());
                intent.putExtra("article_url",list_cards.get(position).getArticle_url());
                intent.putExtra("category",list_cards.get(position).getCategory());
                intent.putExtra("source",list_cards.get(position).getSource());
                intent.putExtra("time",list_cards.get(position).getTime());
                activity.startActivity(intent);

            }
        }) ;
    }
    catch (Exception picasso){
        picasso.printStackTrace();
        System.out.println(position+ list_cards.get(position).getSource().toString()+"ERROR<<<<<<<");
    }



    }

    private static String truncateAfterWords(int n, String s) {
        if (s == null) return null;
        BreakIterator wb = BreakIterator.getWordInstance();
        wb.setText(s);
        int pos = 0;
        for (int i = 0; i < n && pos != BreakIterator.DONE && pos < s.length();) {
            if (Character.isLetter(s.codePointAt(pos))) i++;
            pos = wb.next();
        }
        if (pos == BreakIterator.DONE || pos >= s.length()) return s;
        return s.substring(0, pos);
    }
}
