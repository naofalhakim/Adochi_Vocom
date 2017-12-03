package com.motionlaboratory.adochi;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import customfont.MyTextView;

public class AdopsiFragment2_PilAnak extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AnakAdapter adapter;
    private List<Anak> anakList;
    private MyTextView txt_tanda;

    SQLiteDatabase db;
    Cursor c;

    private String SELECT_ANAK, asal, gender, uname;
    private int umur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopsi_fragment2__pil_anak);
        OpenDatabase();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_anak);
        txt_tanda = (MyTextView) findViewById(R.id.txt_tanda);

        anakList = new ArrayList<>();
        adapter = new AnakAdapter(this, anakList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new AdopsiFragment2_PilAnak.GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();
    }

    private void prepareAlbums() {

        Intent i = getIntent();
        asal = i.getStringExtra("asal");
        gender = i.getStringExtra("gender");
        uname = i.getStringExtra("namauser");
        umur = Integer.parseInt(i.getStringExtra("umur"));
        int min,max;
        max = umur +2;
        min = umur - 2;

        SELECT_ANAK = "SELECT * FROM Anak_table where GENDER='"+gender+"' and ( UMUR='"+umur+"' or UMUR='"+min+"' or UMUR='"+max+"' ) and ASAL='"+asal+"' ;";
        c = db.rawQuery(SELECT_ANAK, null);
        Anak a;
        if(c!=null) {
            if (c.moveToFirst()) {
                do {
                    a = new Anak(c.getString(1), c.getString(3), c.getString(4), c.getString(2), Integer.parseInt(c.getString(5)));
                    anakList.add(a);
                } while (c.moveToNext());
            }

            adapter.notifyDataSetChanged();
        }else if(c == null) {
            txt_tanda.setText("Kriteria Anak Tidak Ditemukan");
            adapter.notifyDataSetChanged();
        }
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    protected void OpenDatabase(){
        db = openOrCreateDatabase("Adochi.db",MODE_PRIVATE,null);
    }
}
