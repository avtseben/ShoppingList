package ru.alexandertsebenko.shoppinglist.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;
import ru.alexandertsebenko.shoppinglist.datamodel.Pack;
import ru.alexandertsebenko.shoppinglist.datamodel.ProductCategory;


public class PackSpinnerAdapter extends RealmBaseAdapter<Pack> implements ListAdapter{

    public PackSpinnerAdapter(Context context, OrderedRealmCollection<Pack> realmResults) {
        super(context, realmResults);

    }
    private static class ViewHolder {
        TextView textView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Pack item = adapterData.get(position);
        viewHolder.textView.setText(item.getName());
        return convertView;
    }
}
