package org.ieee.icce.app;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import com.crashlytics.android.Crashlytics;
import org.ieee.drupal.DrupalClient;
import org.ieee.icce2017.R;
import org.ieee.icce2017.model.AppDatabaseInfo;
import org.ieee.icce2017.model.Model;
import org.ieee.icce2017.model.PreferencesManager;
import org.ieee.icce2017.model.database.LAPIDBRegister;
import org.ieee.http.base.BaseRequest;
import org.ieee.util.image.DrupalImageView;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import io.fabric.sdk.android.Fabric;

public class App extends MultiDexApplication {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
//        if (!BuildConfig.DEBUG) {
            TwitterAuthConfig authConfig = new TwitterAuthConfig(
                    getString(R.string.api_value_twitter_api_key),
                    getString(R.string.api_value_twitter_secret));
//        }

        mContext = getApplicationContext();

        LAPIDBRegister.getInstance().register(mContext, new AppDatabaseInfo(mContext));
        PreferencesManager.initializeInstance(mContext);
        Model.instance(mContext);
        DrupalImageView.setupSharedClient(new DrupalClient(null, Model.instance().createNewQueue(getApplicationContext()), BaseRequest.RequestFormat.JSON, null));
        Fabric.with(this, new Crashlytics(), new Twitter(authConfig));
    }

    public static Context getContext() {
        return mContext;
    }

    public synchronized Tracker getTracker() {
        GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
        return analytics.newTracker(R.xml.global_tracker);
    }
}
