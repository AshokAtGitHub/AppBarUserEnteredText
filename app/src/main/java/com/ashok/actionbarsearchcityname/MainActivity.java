package com.ashok.actionbarsearchcityname;
/* This app allow user to enter text in App-bar after click on search-icon.
   This app read user entered text and simply display it on screen.
Refs: (1)Android material design
 (2)http://stackoverflow.com/questions/34825104/add-search-icon-on-action-bar-android
 */
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
//import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
//------------------------------------------------------------------------
public class MainActivity extends AppCompatActivity {
    Context mContext;
    SearchView mAppBarSearchView;
    TextView mUserInput;
    //--------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContext = getApplicationContext();
        mUserInput = (TextView) findViewById(R.id.txt_userInputDisp);
    }
    //-------------------------------------------------------------
    @Override//auto
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final MenuItem searchItem = menu.findItem(R.id.appBarSearchIcon);
        if (searchItem != null) {
            mAppBarSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
            EditText userInputSearchBox = (EditText) mAppBarSearchView.findViewById
                                        (android.support.v7.appcompat.R.id.search_src_text);
            userInputSearchBox.setHint("Hint:Enter any text here");
            userInputSearchBox.setHintTextColor(getResources().getColor(R.color.grey));
            userInputSearchBox.setTextColor(getResources().getColor(R.color.black));

            String userInput = userInputSearchBox.getText().toString();//ak - Jun9,2016

            View searchPlateView = mAppBarSearchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
            searchPlateView.setBackgroundColor(getResources().getColor(R.color.yellowLight));

            //Display user-entered search text
            mAppBarSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String userInputCityName) {
                    mUserInput.setText("User entered: "+userInputCityName);
                    mUserInput.setTextColor(getResources().getColor(R.color.blue));
                    return false;
                }
                //--------------------------------
                @Override
                public boolean onQueryTextChange(String newText) {
                    // use this method for auto complete search process
                    return false;
                }
            });
            SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
            mAppBarSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
        return super.onCreateOptionsMenu(menu);
        //return true;
    }
    //-------------------------------------------------------------
    @Override
    public void onBackPressed() {
        if (!mAppBarSearchView.isIconified()) {
            mAppBarSearchView.setIconified(true);
            findViewById(R.id.appBarSearchIcon).setVisibility(View.VISIBLE);
        } else {
            super.onBackPressed();
        }
    }
    //-------------------------------------------------------------
    @Override//auto
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //-----------------------------------------------------------
}