package com.basic.programming.dbflownewversion.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.basic.programming.dbflownewversion.R;
import com.basic.programming.dbflownewversion.entity.Versions;

import java.util.ArrayList;
import java.util.List;

public class VersionAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Versions> versionsList;

    public VersionAdapter(Context context, ArrayList<Versions> versionsList) {
        this.context = context;
        this.versionsList = versionsList;
    }

    @Override
    public int getCount() {
        return versionsList.size();
    }

    @Override
    public Object getItem(int position) {
        return versionsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.card_view_version_item, null);
        }

        TextView codeName = convertView.findViewById(R.id.code_name_text_view);
        TextView versionNumber = convertView.findViewById(R.id.version_number_text_view);
        TextView releaseDate = convertView.findViewById(R.id.release_date_text_view);
        TextView apiLevel = convertView.findViewById(R.id.api_level_text_view);

        Versions versions = versionsList.get(position);
        codeName.setText(versions.getCodeName());
        versionNumber.setText(versions.getVersionNumbers());
        releaseDate.setText(versions.getReleaseDate());
        apiLevel.setText(versions.getApiLevel());


        return convertView;
    }
}
