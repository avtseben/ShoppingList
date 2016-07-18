package ru.alexandertsebenko.shoppinglist.ui.activity;

import android.content.ContentProvider;
import android.content.ContentProviderClient;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;
import ru.alexandertsebenko.shoppinglist.R;
import ru.alexandertsebenko.shoppinglist.datamodel.Pack;
import ru.alexandertsebenko.shoppinglist.datamodel.ProductCategory;

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

        basicCRUD(mRealm);

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
        /** Temporary commented. Need more develop
/*        Intent intent = new Intent(this, AddNewProductActivity.class);
        startActivity(intent);*/

/*        Cursor c = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
        while (c.moveToNext()) {
            String name = c.getString(c.getColumnIndex("name_for_primary_account"));
            String phoneNumber = c.getString(c.getColumnIndex("data1"));

            Log.d("contactCursor",name + " : "+ phoneNumber);
        }*/
        Uri requestUri = Uri.parse("content://ru.alexandertsebenko.yr_mind_fixer.provider.notes/text_notes");
        ContentProviderClient client = getContentResolver().acquireContentProviderClient(requestUri);

        Cursor c = null;
        try {
            c = client.query(requestUri,
                    new String[]{"text_note"},
                    "note_title LIKE ?",
                    new String[] {"%купить%"},null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        while (c.moveToNext()) {
            String text = c.getString(0);

            Log.d("MyPro",text);
        }
    }
    private void showStatus(String txt) {
        Log.i(TAG, txt);
    }

    private void basicCRUD(Realm realm){
        // Delete all persons
/*        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(Product.class);
                realm.delete(Pack.class);
                realm.delete(ProductCategory.class);
            }
        });*/
        //Add new
        try {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    // Add a pack
                    Pack pack1 = realm.createObject(Pack.class);
                    pack1.setName("пачка 800г");
                    pack1.setWeight(800);
                    Pack pack2 = realm.createObject(Pack.class);
                    pack2.setName("пакет 900г");
                    pack2.setWeight(900);
                    Pack pack3 = realm.createObject(Pack.class);
                    pack3.setName("кг");
                    pack3.setWeight(1000);

                    ProductCategory category1 = realm.createObject(ProductCategory.class);
                    category1.setName("Крупы");
                    category1.setStorage("сухое прохладное место");
                    ProductCategory category2 = realm.createObject(ProductCategory.class);
                    category2.setName("Кисломолочные продукты");
                    category2.setStorage("Холодильник");
                    ProductCategory category3 = realm.createObject(ProductCategory.class);
                    category3.setName("Овощи");
                    category3.setStorage("Холодильник");
                    ProductCategory category4 = realm.createObject(ProductCategory.class);
                    category4.setName("Мясо");
                    category4.setStorage("Морозильная камера");

                }
            });
        } catch (RealmPrimaryKeyConstraintException e) {
            showStatus("Данные уже есть в таблице");
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }
}
