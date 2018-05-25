package com.example.ha_hai.animationinandroid;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.ha_hai.animationinandroid.fragment.GridFragment;
import com.example.ha_hai.animationinandroid.fragment.ListFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnGrid, btnList;
    LinearLayout buttonContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGrid = findViewById(R.id.btn_grid);
        btnList = findViewById(R.id.btn_list);
        buttonContainer = findViewById(R.id.button_container);

        btnGrid.setOnClickListener(this);
        btnList.setOnClickListener(this);

        if (savedInstanceState != null) {
            buttonContainer.setAlpha(getSupportFragmentManager().getBackStackEntryCount() > 0 ? 0 : 1);
        }
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.main_root, fragment).addToBackStack(null);
        transaction.commit();
        buttonContainer.animate().alpha(0f).start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_grid:
                showFragment(new GridFragment());
                break;
            case R.id.btn_list:
                showFragment(new ListFragment());
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            buttonContainer.animate().alpha(1f).start();
        }
    }
}
