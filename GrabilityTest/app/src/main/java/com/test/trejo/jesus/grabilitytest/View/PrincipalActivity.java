package com.test.trejo.jesus.grabilitytest.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.test.trejo.jesus.grabilitytest.Helper.OnFragmentSwap;
import com.test.trejo.jesus.grabilitytest.R;


public class PrincipalActivity extends AppCompatActivity implements OnFragmentSwap {

    private FragmentManager FM = getSupportFragmentManager();



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);//Menu Resource, Menu
        return true;
    }


    /**
     * Method type: @Override Void
     * Method Name: onOptionsItemSelected
     * Method @Params Menu menu, MenuInflater inflater
     * Method Description: this method drive the click on the menuIcon
     * **/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Bundle bundle = new Bundle();

        switch (id) {
            case R.id.action_popular:
                bundle.putString("type","popular");
                onSwap("Popular",bundle);
                return true;

            case R.id.action_top_rated:
                bundle.putString("type","top_rated");
                onSwap("TopRated",bundle);
                return true;

            case R.id.action_upcoming:
                bundle.putString("type","upcoming");
                onSwap("Upcoming",bundle);
                return true;

        }

        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        onSwap("Popular",null);

    }

    @Override
    public void onSwap(String FragmentName, Bundle bundle) {

        Fragment fragmentToSwap = null;
        FragmentTransaction fragmentTransaction = FM.beginTransaction();
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportActionBar().setTitle("");
        try {
            switch (FragmentName) {
                case "Popular":
                    fragmentToSwap = new MoviesListFragment();
                    fragmentToSwap.setArguments(bundle);
                    fragmentTransaction.replace(R.id.flContent, fragmentToSwap);
                    break;
                case "TopRated":
                    fragmentToSwap = new MoviesListFragment();
                    fragmentToSwap.setArguments(bundle);
                    fragmentTransaction.replace(R.id.flContent, fragmentToSwap);
                    break;
                case "Upcoming":
                    fragmentToSwap = new MoviesListFragment();
                    fragmentToSwap.setArguments(bundle);
                    fragmentTransaction.replace(R.id.flContent, fragmentToSwap);
                    break;
                case "Detail":
                    fragmentToSwap = new DetailMovieFragment();
                    fragmentToSwap.setArguments(bundle);
                    fragmentTransaction.replace(R.id.flContent, fragmentToSwap);
                    fragmentTransaction.addToBackStack("MoviesListFragment");

                    break;
            }
            fragmentTransaction.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }




}
