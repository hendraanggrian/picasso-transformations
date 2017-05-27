package com.example.picassocommons;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.hendraanggrian.picasso.commons.target.Targets;
import com.hendraanggrian.support.utils.widget.Toasts;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

public class TargetActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.toolbar_target) Toolbar toolbar;
    @BindView(R.id.edittext_target) EditText editText;
    @BindView(R.id.button_target_clear) Button buttonClear;
    @BindView(R.id.button_target_go) Button buttonGo;
    @BindView(R.id.imageview_target) ImageView imageView;

    @Override
    protected int getContentView() {
        return R.layout.activity_target;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        buttonClear.setOnClickListener(this);
        buttonGo.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_target_clear:
                editText.setText("");
                break;
            case R.id.button_target_go:
                View focusedView = getCurrentFocus();
                if (focusedView != null)
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(focusedView.getWindowToken(), 0);

                Picasso.with(this)
                        .load(editText.getText().toString())
                        .into(Targets.placeholder(imageView).callback(new Targets.OnSuccess() {
                            @Override
                            public void onSuccess() {
                                Toasts.showShort(TargetActivity.this, "Image successfully loaded.");
                            }
                        }, new Targets.OnError() {
                            @Override
                            public void onError() {
                                Toasts.showShort(TargetActivity.this, "Image failed to load.");
                            }
                        }));
                break;
        }
    }
}