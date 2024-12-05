package com.techietech.ps5emulatorprank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.techietech.ps5emulatorprank.Activities.MainActivity;
import com.techietech.ps5emulatorprank.Adapters.OnBoardingAdapter;

public class OnBoarding extends AppCompatActivity {

    Button nextBtn;
    TextView skipBtn,ppLink;
    LinearLayout dotsLayout,privacyPolicyLayout;
    ViewPager viewPager2;
    TextView[] dots;
    int currentPosition;
    CheckBox checkBoxPp;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_on_boarding);

        // Checking for first time launch - before calling setContentView()
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        //StatusBarColor();
        statusBarTransparent();

        viewPager2 = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        nextBtn = findViewById(R.id.button_next);
        skipBtn = findViewById(R.id.text_skip);
        privacyPolicyLayout = findViewById(R.id.privacyPolicy_Layout);
        checkBoxPp = findViewById(R.id.checkBox_pp);
        ppLink = findViewById(R.id.text_pp_tc_link);

        String text = getString(R.string.privacy_policy);
        SpannableString ss = new SpannableString(text);


        //OnBoardingAdapter adapter = new OnBoardingAdapter(this);
        OnBoardingAdapter adapter = new OnBoardingAdapter(this);
        viewPager2.setAdapter(adapter);

        addDots(0);
        viewPager2.addOnPageChangeListener(changeListener);

        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager2.setCurrentItem(3);
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager2.setCurrentItem(currentPosition+1);
            }
        });

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                //Intent desIntent = new Intent(getApplicationContext(), DescriptionActivity.class);
                //desIntent.putExtra("titles",getString(R.string.privacy_policy_url));
                //startActivity(desIntent);
            }
        };
        ss.setSpan(clickableSpan1,35,49, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ppLink.setText(ss);
        ppLink.setMovementMethod(LinkMovementMethod.getInstance());




    }

    private void addDots(int position){
        dots = new TextView[4];
        dotsLayout.removeAllViews();

        for(int i =0; i < dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);

            dotsLayout.addView(dots[i]);
        }

        if (dots.length>0){
            dots[position].setTextColor(getResources().getColor(R.color.white));
        }

    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPosition = position;


            if(position == 0){
                visibleOnBoarding();

            } else if(position == 1){
                visibleOnBoarding();

            } else if(position == 2){
                visibleOnBoarding();

            }else{
                onCheckBoxTick();
                inVisibleOnBoarding();
                nextBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        launchHomeScreen();
                    }
                });
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void visibleOnBoarding(){
        RelativeLayout.LayoutParams ip = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        ip.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);

        nextBtn.setText(R.string.next);
        dotsLayout.setVisibility(View.VISIBLE);
        privacyPolicyLayout.setVisibility(View.INVISIBLE);
        nextBtn.setLayoutParams(ip);
        nextBtn.setEnabled(true);
        skipBtn.setVisibility(View.VISIBLE);

    }

    private void inVisibleOnBoarding(){
        RelativeLayout.LayoutParams ip = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        ip.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);

        nextBtn.setText(R.string.lets_start);
        dotsLayout.setVisibility(View.INVISIBLE);
        privacyPolicyLayout.setVisibility(View.VISIBLE);
        nextBtn.setLayoutParams(ip);
        nextBtn.setEnabled(false);
        skipBtn.setVisibility(View.INVISIBLE);
    }

    private void onCheckBoxTick() {

        checkBoxPp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextBtn.setEnabled(checkBoxPp.isChecked());

            }
        });

    }

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(OnBoarding.this, MainActivity.class));
        finish();
    }

    private void statusBarTransparent(){
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);

    }



}