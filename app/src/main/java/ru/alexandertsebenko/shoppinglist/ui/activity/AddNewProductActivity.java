package ru.alexandertsebenko.shoppinglist.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Spinner;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import ru.alexandertsebenko.shoppinglist.R;
import ru.alexandertsebenko.shoppinglist.datamodel.Pack;
import ru.alexandertsebenko.shoppinglist.datamodel.ProductCategory;
import ru.alexandertsebenko.shoppinglist.ui.adapter.CategorySpinnerAdapter;
import ru.alexandertsebenko.shoppinglist.ui.adapter.MultiSpinner;
import ru.alexandertsebenko.shoppinglist.ui.adapter.PackSpinnerAdapter;

public class AddNewProductActivity extends AppCompatActivity {

    private Button mAddButton;
    private Spinner mCategorySpinner;
    private MultiSpinner mPackSpinner;
    private Realm mRealm;
    private RealmConfiguration mRealmConfig;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);

        mRealmConfig = new RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build();
        mRealm = Realm.getInstance(mRealmConfig);

        mCategorySpinner = (Spinner) findViewById(R.id.spin_new_product_category);
        mPackSpinner = (MultiSpinner) findViewById(R.id.spin_new_product_packs);
        mAddButton = (Button) findViewById(R.id.btn_new_product);

        //Category spinner
        RealmResults<ProductCategory> categoties = mRealm.where(ProductCategory.class).findAll();
        final CategorySpinnerAdapter categorySpinnerAdapter = new CategorySpinnerAdapter(this, categoties);
        mCategorySpinner.setAdapter(categorySpinnerAdapter);

        //Pack spinner
        RealmResults<Pack> packs = mRealm.where(Pack.class).findAll();
        final PackSpinnerAdapter packSpinnerAdapter = new PackSpinnerAdapter(this,packs);
        mPackSpinner.setAdapter(packSpinnerAdapter);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }
}
