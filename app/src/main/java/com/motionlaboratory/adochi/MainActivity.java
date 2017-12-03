package com.motionlaboratory.adochi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    Intromanager intromanager;
    private int[] layout;
    private TextView[] dots;
    private LinearLayout dotsLayout;
    ViewPagerAdapter viewPagerAdapter;
    Button next,skip;
    Intent intent;

    DBHelperConfig myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myDb = new DBHelperConfig(this);

        intromanager = new Intromanager(this);
        if(!intromanager.chek()){
            intromanager.setFirst(false);

            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
        if(Build.VERSION.SDK_INT >= 21 ){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_FULLSCREEN );
        }
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.vw_pager);
        dotsLayout=(LinearLayout)findViewById(R.id.layoutDots);
        skip = (Button)findViewById(R.id.btn_skip);
        next = (Button)findViewById(R.id.btn_next);

        layout = new int[]{R.layout.activity_screen1,R.layout.activity_screen2,R.layout.activity_screen3};

        addBottom(0);
        changeStatusBarColor();
        viewPagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(viewListener);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int[] panti = new int[]{
                        R.drawable.by1,
                        R.drawable.by2,
                        R.drawable.by3,
                        R.drawable.by4,
                        R.drawable.by5,
                        R.drawable.by6,
                        R.drawable.by7,
                        R.drawable.by8,
                        R.drawable.by9
                };

                boolean a;

                a = myDb.insertDataAnak("Azmya Sabiya Nasira Raesha",6,"PEREMPUAN","BANDUNG",panti[0]);
                a = myDb.insertDataAnak("Assyfa Putri Aura Zaskia",4,"PEREMPUAN","YOGYAKARTA",panti[1]);
                a = myDb.insertDataAnak("Ashalina Yumnaa Naladhipa",5,"PEREMPUAN","YOGYAKARTA",panti[2]);
                a = myDb.insertDataAnak("Aqilla Fariza Mufia",2,"PEREMPUAN","MALANG",panti[3]);
                a = myDb.insertDataAnak("Fariza Yumnaa Aminullah",3,"PEREMPUAN","JAKARTA",panti[4]);
                a = myDb.insertDataAnak("Ainun Nur Latifa",5,"PEREMPUAN","JAKARTA",panti[1]);
                a = myDb.insertDataAnak("Resma Sonya Nur Aini",2,"PEREMPUAN","YOGYAKARTA",panti[0]);

                a = myDb.insertDataAnak("Afif Ahwal Said",3,"LAKI - LAKI","BANDUNG",panti[5]);
                a = myDb.insertDataAnak("Adib Alim Aminullah",4,"LAKI - LAKI","JAKARTA",panti[6]);
                a = myDb.insertDataAnak("Afnan Atma Purnama",5,"LAKI - LAKI","YOGYAKARTA",panti[7]);
                a = myDb.insertDataAnak("Abdiel Justin Gilbert",3,"LAKI - LAKI","JAKARTA",panti[8]);
                a = myDb.insertDataAnak("Mohamaad Fariz Alfurqon",6,"LAKI - LAKI","BANDUNG",panti[6]);
                a = myDb.insertDataAnak("Justin Ali Akbar",4,"LAKI - LAKI","JAKARTA",panti[7]);
                a = myDb.insertDataAnak("Fajar Trisna Gumelar",2,"LAKI - LAKI","YOGYAKARTA",panti[5]);

                    intent = new Intent(MainActivity.this,MenuActivity.class);
                    startActivity(intent);
                finish();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currnet = getItem(+1);
                if(currnet<layout.length){
                    viewPager.setCurrentItem(currnet);
                }else{


                    int[] panti = new int[]{
                            R.drawable.by1,
                            R.drawable.by2,
                            R.drawable.by3,
                            R.drawable.by4,
                            R.drawable.by5,
                            R.drawable.by6,
                            R.drawable.by7,
                            R.drawable.by8,
                            R.drawable.by9
                    };

                    boolean a;

                    a = myDb.insertDataAnak("Azmya Sabiya Nasira Raesha",6,"PEREMPUAN","BANDUNG",panti[0]);
                    a = myDb.insertDataAnak("Assyfa Putri Aura Zaskia",4,"PEREMPUAN","YOGYAKARTA",panti[1]);
                    a = myDb.insertDataAnak("Ashalina Yumnaa Naladhipa",5,"PEREMPUAN","YOGYAKARTA",panti[2]);
                    a = myDb.insertDataAnak("Aqilla Fariza Mufia",2,"PEREMPUAN","MALANG",panti[3]);
                    a = myDb.insertDataAnak("Fariza Yumnaa Aminullah",3,"PEREMPUAN","JAKARTA",panti[4]);
                    a = myDb.insertDataAnak("Ainun Nur Latifa",5,"PEREMPUAN","JAKARTA",panti[1]);
                    a = myDb.insertDataAnak("Resma Sonya Nur Aini",2,"PEREMPUAN","YOGYAKARTA",panti[0]);

                    a = myDb.insertDataAnak("Afif Ahwal Said",3,"LAKI - LAKI","BANDUNG",panti[5]);
                    a = myDb.insertDataAnak("Adib Alim Aminullah",4,"LAKI - LAKI","JAKARTA",panti[6]);
                    a = myDb.insertDataAnak("Afnan Atma Purnama",5,"LAKI - LAKI","YOGYAKARTA",panti[7]);
                    a = myDb.insertDataAnak("Abdiel Justin Gilbert",3,"LAKI - LAKI","JAKARTA",panti[8]);
                    a = myDb.insertDataAnak("Mohamaad Fariz Alfurqon",6,"LAKI - LAKI","BANDUNG",panti[6]);
                    a = myDb.insertDataAnak("Justin Ali Akbar",4,"LAKI - LAKI","JAKARTA",panti[7]);
                    a = myDb.insertDataAnak("Fajar Trisna Gumelar",2,"LAKI - LAKI","YOGYAKARTA",panti[5]);

                    intent = new Intent(MainActivity.this,MenuActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void addBottom(int position){
        dots = new TextView[layout.length];
        int[] colorActive = getResources().getIntArray(R.array.dot_active);
        int[] colorInactive = getResources().getIntArray(R.array.dot_inactives);
        dotsLayout.removeAllViews();

        for (int i=0;i<dots.length;i++){
            dots[i] = new TextView(this);
            dots[i].setTextColor(colorInactive[position]);
            dotsLayout.addView(dots[i]);
        }
        if(dots.length>0){
            dots[position].setTextColor(colorActive[position]);
        }
    }

    private int getItem(int i){
        return viewPager.getCurrentItem()+i;
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addBottom(position);
            if(position==layout.length-1){
                next.setText("Akhiri");
                skip.setVisibility(View.GONE);
            }else{
                next.setText("Selanjutnya");
                skip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    private void changeStatusBarColor(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public class ViewPagerAdapter extends PagerAdapter{

        private LayoutInflater layoutInflater;

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = layoutInflater.inflate(layout[position],container,false);
            container.addView(v);
            return v;
        }

        @Override
        public int getCount() {
            return layout.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View)object;
            container.removeView(view);
        }
    }

}
