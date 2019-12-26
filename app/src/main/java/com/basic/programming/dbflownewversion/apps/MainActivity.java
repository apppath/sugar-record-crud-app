package com.basic.programming.dbflownewversion.apps;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.basic.programming.dbflownewversion.R;
import com.basic.programming.dbflownewversion.adapters.VersionAdapter;
import com.basic.programming.dbflownewversion.entity.Versions;
import com.google.android.material.navigation.NavigationView;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,
        AdapterView.OnItemLongClickListener,
        NavigationView.OnNavigationItemSelectedListener {

    private ListView listView;
    private VersionAdapter versionAdapter;
    private Versions versions;
    private ArrayList<Versions> versionsArrayList;
    private Dialog dialog;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_bar_draw_layout);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.main_nar_bar);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar, R.string.open_nar_bar, R.string.close_nav_bar);

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        dialog = new Dialog(MainActivity.this, R.style.appDialog);

        listView = findViewById(R.id.version_list_view);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);

        selectData();
    }

    public void selectData() {

        List<Versions> versionsList = Versions.listAll(Versions.class);
        versionsArrayList = new ArrayList<>();
        versions = new Versions();
        for (int i = 0; i < versionsList.size(); i++) {
            versions = versionsList.get(i);
            versionsArrayList.add(versions);
        }
        versionAdapter = new VersionAdapter(MainActivity.this, versionsArrayList);
        listView.setAdapter(versionAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        versions = versionsArrayList.get(position);

        dialog.setTitle("Edit Version");
        dialog.setContentView(R.layout.item_edit_version_layout);
        dialog.setCanceledOnTouchOutside(true);

        final EditText code_name = dialog.findViewById(R.id.edit_code_name_edit_text);
        final EditText version_number = dialog.findViewById(R.id.edit_version_number_edit_text);
        final EditText release_date = dialog.findViewById(R.id.edit_release_date_edit_text);
        final EditText api_level = dialog.findViewById(R.id.edit_api_level_edit_text);
        Button editBtn = dialog.findViewById(R.id.editButton);

        code_name.setText(versions.getCodeName());
        version_number.setText(versions.getVersionNumbers());
        release_date.setText(versions.getReleaseDate());
        api_level.setText(versions.getApiLevel());

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = code_name.getText().toString();
                String numbers = version_number.getText().toString();
                String release = release_date.getText().toString();
                String level = api_level.getText().toString();

                versions.setCodeName(name);
                versions.setVersionNumbers(numbers);
                versions.setReleaseDate(release);
                versions.setApiLevel(level);

                versions.save();
                versionsArrayList.remove(versions);
                versionsArrayList.add(versions);

                FancyToast.makeText(MainActivity.this, "Version Edit Success",
                        FancyToast.LENGTH_LONG, FancyToast.SUCCESS,
                        R.drawable.ic_pan_tool_black_24dp,
                        false).show();

                dialog.dismiss();
                listView.setAdapter(versionAdapter);
                versionAdapter.notifyDataSetChanged();
            }
        });
        dialog.show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        Versions versions = versionsArrayList.get(position);

        versions.delete();
        versionsArrayList.remove(position);

        FancyToast.makeText(MainActivity.this, "Delete Successfully Items",
                FancyToast.LENGTH_LONG, FancyToast.ERROR,
                R.drawable.ic_pan_tool_black_24dp,
                false).show();

        listView.setAdapter(versionAdapter);
        versionAdapter.notifyDataSetChanged();

        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_setting:

                FancyToast.makeText(MainActivity.this, "Setting Menu",
                        FancyToast.LENGTH_LONG,
                        FancyToast.WARNING,
                        android.R.drawable.ic_dialog_info,
                        false).show();
                break;
            case R.id.menu_insert:
                startActivity(new Intent(MainActivity.this, InsertVersion.class));
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
