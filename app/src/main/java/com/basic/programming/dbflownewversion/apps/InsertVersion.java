package com.basic.programming.dbflownewversion.apps;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.basic.programming.dbflownewversion.R;
import com.basic.programming.dbflownewversion.entity.Versions;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.shashank.sony.fancytoastlib.FancyToast;

public class InsertVersion extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    private TextInputEditText codeName, versionNumber, releaseDate, apiLevel;
    private Versions versions;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_nar_drawer_layout);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.main_nar_bar);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar, R.string.open_nar_bar, R.string.close_nav_bar);

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        codeName = findViewById(R.id.code_name_edit_text);
        versionNumber = findViewById(R.id.version_number_edit_text);
        releaseDate = findViewById(R.id.release_date_edit_text);
        apiLevel = findViewById(R.id.api_level_edit_text);

    }

    private void insertVersion() {

        String code_name = codeName.getText().toString();
        String version_numbers = versionNumber.getText().toString();
        String release_date = releaseDate.getText().toString();
        String api_level = apiLevel.getText().toString();

        if (code_name.isEmpty()) {
            FancyToast.makeText(this, "Please full code name android",
                    FancyToast.LENGTH_LONG, FancyToast.WARNING,
                    R.drawable.ic_help_black_24dp, false).show();
            return;
        }

        if (version_numbers.isEmpty()) {
            FancyToast.makeText(this, "Please full version number android",
                    FancyToast.LENGTH_LONG, FancyToast.WARNING,
                    R.drawable.ic_help_black_24dp, false).show();
            return;
        }

        if (release_date.isEmpty()) {
            FancyToast.makeText(this, "Please full release date android",
                    FancyToast.LENGTH_LONG, FancyToast.WARNING,
                    R.drawable.ic_help_black_24dp, false).show();
            return;
        }

        if (api_level.isEmpty()) {
            FancyToast.makeText(this, "Please full api level android",
                    FancyToast.LENGTH_LONG, FancyToast.WARNING,
                    R.drawable.ic_help_black_24dp, false).show();
            return;
        }

        versions = new Versions();
        versions.setCodeName(code_name);
        versions.setVersionNumbers(version_numbers);
        versions.setReleaseDate(release_date);
        versions.setApiLevel(api_level);
        versions.save();

        setResult(RESULT_OK);

        FancyToast.makeText(this, "Successfully saved Records",
                FancyToast.LENGTH_LONG,
                FancyToast.SUCCESS,
                R.drawable.ic_assignment_turned_in_black_24dp, false).show();

        codeName.setText("");
        versionNumber.setText("");
        releaseDate.setText("");
        apiLevel.setText("");
    }

    public void addVersions(View view) {
        insertVersion();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_info:

                FancyToast.makeText(this, "Info Menu ",
                        FancyToast.LENGTH_LONG,
                        FancyToast.INFO,
                        android.R.drawable.ic_dialog_info,
                        false).show();

                break;

            case R.id.menu_setting:

                FancyToast.makeText(this, "Setting Menu ",
                        FancyToast.LENGTH_LONG,
                        FancyToast.INFO,
                        android.R.drawable.ic_menu_info_details,
                        false).show();

                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
