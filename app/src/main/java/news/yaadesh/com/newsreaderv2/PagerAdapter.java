package news.yaadesh.com.newsreaderv2;

/**
 * Created by Yaadesh on 03-08-2016.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TopStoriesFragment topStoriesFragment = new TopStoriesFragment();
                return topStoriesFragment;
            case 1:
                BusinessFragment businessFragment = new BusinessFragment();
                return  businessFragment;
            case 2:
                TechnologyFragment technologyFragment = new TechnologyFragment();
                return technologyFragment;
            case 3:
                EntertainmentFragment entertainmentFragment = new EntertainmentFragment();
                return entertainmentFragment;
            case 4:
                SportsFragment sportsFragment = new SportsFragment();
                return sportsFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}