package news.yaadesh.com.newsreaderv2;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Yaadesh on 17-12-2016.
 */
public class CardObject{
    private String source;
    private String category;
    private String title;
    private String description;
    private String image_url;
    private String article_url;
    private String time;

    CardObject() {
        this.setSource("");
        this.setArticle_url("");
        this.setTitle("");
        this.setDescription("");
        this.setImage_url("");
        this.setTime("");
        this.setCategory("Technology");
    }

    //GETTERS


    public String getSource() {
        return source;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getArticle_url() {
        return article_url;
    }

    public String getTime() {
        return time;
    }

    public String getCategory() {
        return category;
    }

    //SETTERS

    public void setSource(String source) {
        this.source = source;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setArticle_url(String article_url) {
        this.article_url = article_url;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCategory(String category) {this.category = category; }



}



