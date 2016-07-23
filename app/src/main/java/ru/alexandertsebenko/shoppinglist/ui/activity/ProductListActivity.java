package ru.alexandertsebenko.shoppinglist.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;
import ru.alexandertsebenko.shoppinglist.R;
import ru.alexandertsebenko.shoppinglist.datamodel.simplemodel.DataVersion;
import ru.alexandertsebenko.shoppinglist.datamodel.simplemodel.Product;

public class ProductListActivity extends AppCompatActivity {

    private Realm mRealm;
    private RealmConfiguration mRealmConfig;
    public static final String TAG = ProductListActivity.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the Realm configuration
        mRealmConfig = new RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build();
        // Open the Realm for the UI thread.
        mRealm = Realm.getInstance(mRealmConfig);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabClicked();
                Snackbar.make(view, "Добавим новый продукт", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product_list, menu);
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
    private void fabClicked() {
        String jsonStr;
        try {
            jsonStr = readFileFromResToSring(R.raw.data);
            fromJsonToRealm(jsonStr,mRealm);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
       //Debug
        RealmResults<Product> allData = mRealm.where(Product.class).findAll();
        RealmResults<DataVersion> dvr = mRealm.where(DataVersion.class).findAll();
        DataVersion dv = mRealm.where(DataVersion.class).findFirst();
        Log.d("MuLog", "dv " + dv.version);
        for (Product p : allData) {
            Log.d("MyLog", p.getName());
        }

    }
    private void showStatus(String txt) {
        Log.i(TAG, txt);
    }
    private String readFileFromResToSring(int resId) throws IOException {
        Context context = getApplicationContext();
        InputStream inputStream = getApplicationContext()
                .getResources().openRawResource(resId);
        BufferedReader br = new BufferedReader(
                new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        inputStream.close();
        br.close();
        return sb.toString();
    }
    private boolean updateNeeded(int parsedVersion){
        DataVersion currentVersion = mRealm.where(DataVersion.class).findFirst();
        return currentVersion.version < parsedVersion;
    }
    private void fromJsonToRealm(final String jsonStr, Realm realm) throws JSONException,RealmException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        JSONObject jsonObject = new JSONObject(jsonStr);
        final int ver = jsonObject.getInt("version");
        Log.d("MyLog","ver" + ver);
        if(updateNeeded(ver)) {
//            if(true) {

            JSONArray jsonArray = jsonObject.getJSONArray("Product");

            //AllProducts to ArrayList
            final ArrayList<Product> allProducts = gson.fromJson(jsonArray.toString(),
                    new TypeToken<ArrayList<Product>>(){}.getType());

            //To Realm
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    try {
//                        mRealm.copyToRealm(allProducts);
                        DataVersion v = mRealm.createObject(DataVersion.class);
                        v.version = ver;
                    } catch (RealmException e) {
                        e.printStackTrace();
                        Log.d("Realm log", "ошибка при перекачке");
                    }
                }
            });
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }
}
