package news.yaadesh.com.newsreaderv2;

/**
 * Created by Yaadesh on 06-01-2017.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ActivityPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    String article_url;

    public ActivityPagerAdapter(FragmentManager fm, int NumOfTabs,String article_url) {
        super(fm);
        System.out.println("ADAPTER CONSTRUCTOR");

        this.mNumOfTabs = NumOfTabs;
        this.article_url = article_url;
        System.out.println(this.article_url);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                System.out.println("SUMMARY BEFORE CREATION");
                SummaryFragment summary = new SummaryFragment(article_url,"summary");
                System.out.println("SUMMARY AFTER CREATION");
                return summary;
            case 1:
                SummaryFragment summary2 = new SummaryFragment(article_url,"article");
                return summary2;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

