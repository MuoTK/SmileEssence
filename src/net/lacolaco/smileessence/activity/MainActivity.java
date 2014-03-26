/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2012-2014 lacolaco.net
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package net.lacolaco.smileessence.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.Menu;
import net.lacolaco.smileessence.R;
import net.lacolaco.smileessence.entity.Account;
import net.lacolaco.smileessence.preference.AppPreferenceHelper;
import net.lacolaco.smileessence.preference.UserPreferenceHelper;
import net.lacolaco.smileessence.resource.ResourceHelper;
import net.lacolaco.smileessence.twitter.OAuthSession;
import net.lacolaco.smileessence.view.TextFragment;
import net.lacolaco.smileessence.view.adapter.PageListAdapter;
import net.lacolaco.smileessence.viewmodel.menu.MainActivityMenuFactory;
import twitter4j.auth.AccessToken;

import java.io.IOException;

public class MainActivity extends Activity
{

    public static final int REQUEST_OAUTH = 10;
    private ResourceHelper resourceHelper;
    private UserPreferenceHelper userPref;
    private AppPreferenceHelper appPref;
    private ViewPager viewPager;
    private PageListAdapter pagerAdapter;
    private OAuthSession oauthSession;
    private Account currentAccount;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        try
        {
            setupHelpers();
        }
        catch(IOException e)
        {
            e.printStackTrace();
            finish();
        }
        initializeView();
    }

    private void initializeView()
    {
        ActionBar bar = getActionBar();
        bar.setDisplayShowHomeEnabled(false);
        bar.setDisplayShowTitleEnabled(false);
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        pagerAdapter = new PageListAdapter(this, viewPager);
        Bundle args = new Bundle();
        args.putString(TextFragment.ARG_TEXT, "test");
        pagerAdapter.addPageWithoutNotify(resourceHelper.getString(R.string.page_name_post), TextFragment.class, args);
        pagerAdapter.addPageWithoutNotify(resourceHelper.getString(R.string.page_name_home), TextFragment.class, args);
        pagerAdapter.addPageWithoutNotify(resourceHelper.getString(R.string.page_name_mentions), TextFragment.class, args);
        pagerAdapter.addPageWithoutNotify(resourceHelper.getString(R.string.page_name_message), TextFragment.class, args);
        pagerAdapter.addPageWithoutNotify(resourceHelper.getString(R.string.page_name_history), TextFragment.class, args);
        pagerAdapter.notifyDataSetChanged();
        getActionBar().setSelectedNavigationItem(1); //Home
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        //        //account check
        //        long id = getLastUsedAccountID();
        //        if(id < 0)
        //        {
        //            oauthSession = new OAuthSession();
        //            String url = oauthSession.getAuthorizationURL();
        //            if(!TextUtils.isEmpty(url))
        //            {
        //                Intent intent = new Intent(this, WebViewActivity.class);
        //                intent.setData(Uri.parse(url));
        //                startActivityForResult(intent, REQUEST_OAUTH);
        //            }
        //            else
        //            {
        //                //TODO Notify auth error
        //            }
        //        }
        //        else
        //        {
        //            //Login and initialize
        //            Account account = Account.load(Account.class, id);
        //            setCurrentAccount(account);
        //            if(isFirstLaunchThisVersion())
        //            {
        //                //TODO Show change log
        //            }
        //            initializeTimelines();
        //        }
    }

    private void initializeTimelines()
    {
    }

    public long getLastUsedAccountID()
    {
        String id = appPref.getValue("lastUsedAccountID", "");
        if(TextUtils.isEmpty(id))
        {
            return -1;
        }
        else
        {
            return Long.getLong(id);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch(requestCode)
        {
            case REQUEST_OAUTH:
            {
                if(resultCode != RESULT_OK)
                {
                    //TODO Notify error
                    finish();
                }
                else
                {
                    AccessToken token = oauthSession.getAccessToken(data.getData());
                    Account account = new Account(token.getToken(), token.getTokenSecret());
                    account.save();
                    setCurrentAccount(account);
                    initializeTimelines();
                }
            }
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
    }

    @Override
    protected void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MainActivityMenuFactory factory = new MainActivityMenuFactory(resourceHelper);
        factory.addItemsToMenu(menu);
        return true;
    }

    private String getVersion()
    {
        return resourceHelper.getString(R.string.app_version);
    }

    private String getLastLaunchVersion()
    {
        return appPref.getValue("app.version", "");
    }

    private boolean isFirstLaunchThisVersion()
    {
        return !getVersion().contentEquals(getLastLaunchVersion());
    }

    private void setupHelpers() throws IOException
    {
        resourceHelper = new ResourceHelper(this);
        userPref = new UserPreferenceHelper(this);
        appPref = new AppPreferenceHelper(this);
    }

    public ResourceHelper getResourceHelper()
    {
        return resourceHelper;
    }

    public UserPreferenceHelper getUserPref()
    {
        return userPref;
    }

    public AppPreferenceHelper getAppPref()
    {
        return appPref;
    }

    public ViewPager getViewPager()
    {
        return viewPager;
    }

    public PageListAdapter getPagerAdapter()
    {
        return pagerAdapter;
    }

    public Account getCurrentAccount()
    {
        return currentAccount;
    }

    public void setCurrentAccount(Account currentAccount)
    {
        this.currentAccount = currentAccount;
    }

    /**
     * Add page.
     *
     * @see PageListAdapter#addPage(String, Class, android.os.Bundle)
     */
    public boolean addPage(String name, Class<? extends Fragment> fragmentClass, Bundle args)
    {
        return this.pagerAdapter.addPage(name, fragmentClass, args);
    }

    /**
     * Remove page.
     *
     * @see PageListAdapter#removePage(int)
     */
    public boolean removePage(int position)
    {
        return this.pagerAdapter.removePage(position);
    }

    /**
     * Remove current page.
     *
     * @return successfully
     */
    public boolean removeCurrentPage()
    {
        return this.pagerAdapter.removePage(getCurrentPageIndex());
    }

    /**
     * Get current page index.
     *
     * @return page index
     */
    public int getCurrentPageIndex()
    {
        return this.viewPager.getCurrentItem();
    }
}
