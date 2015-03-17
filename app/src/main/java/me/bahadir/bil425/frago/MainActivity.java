package me.bahadir.bil425.frago;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import me.bahadir.android.RealSimpleAdapter;


public class MainActivity extends FragmentActivity implements RealSimpleAdapter.OnItemSelectListener {

    private boolean smallScreen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListFragment listFragment = new ListFragment();
        listFragment.setArguments(getIntent().getExtras());

        this.smallScreen = findViewById(R.id.fragment_container) != null;

        if(smallScreen) {
            //small screen. Use replacement method.

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }



            getSupportFragmentManager()
                    .beginTransaction()
                        .add(R.id.fragment_container, listFragment)
                        .addToBackStack(null)
                    .commit();


        } else {

            ContentFragment contentFragment = new ContentFragment();
            contentFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_list, listFragment)
                    .add(R.id.frame_content, contentFragment)
                    .commit();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
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

    @Override
    public void onItemSelect(int index, String data) {

        ContentFragment contentFragment = (ContentFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_content);

        if(contentFragment == null) {

            contentFragment = new ContentFragment();

            Bundle args = new Bundle();
            args.putString("url", data);
            contentFragment.setArguments(args);

            int container = smallScreen ? R.id.fragment_container : R.id.fragment_content;
            getSupportFragmentManager().beginTransaction()
                    .replace(container, contentFragment)
                    .addToBackStack(null)
                    .commit();
        } else {
            contentFragment.showURL(this, data);
        }

    }
}
