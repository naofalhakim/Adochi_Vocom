package com.motionlaboratory.adochi;

import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by naofal on 2/20/2017.
 */

public class BerandaFragment extends Fragment {
    public BerandaFragment() {

    }

    private RecyclerView recyclerView;
    private BeritaAdapter adapter;
    private List<Berita> beritaList;

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.activity_beranda, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        beritaList = new ArrayList<>();
        adapter = new BeritaAdapter(this.getActivity(), beritaList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this.getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(20), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();
        return rootView;
    }

    private void prepareAlbums() {
        int[] panti = new int[]{
                R.drawable.panti1,
                R.drawable.panti2,
                R.drawable.pantimalang,
                R.drawable.panti5,
                R.drawable.panti4,
                R.drawable.panti6,
                R.drawable.panti7,
                R.drawable.pantisby,

                };

        Berita a = new Berita("5 Anak Terken Demam Berdarah","Panti Asuhan Undaan",panti[0]);
        beritaList.add(a);

        a = new Berita("Butuh Dana Tambahan Pendidikan","Panti Asuhan Muhammadiyah",panti[1]);
        beritaList.add(a);

        a = new Berita("Dana Habis Pembangunan Berhenti","Panti Asuhan Sunan Giri",panti[2]);
        beritaList.add(a);

        a = new Berita("Pasca Kebakaran Butuh Bantuan","Panti Asuhan Darussalam",panti[5]);
        beritaList.add(a);

        a = new Berita("Butuh Bantuan Sarana Dan Prasarana","Panti Asuhan ELIM ANUGRAH",panti[6]);
        beritaList.add(a);

        a = new Berita("Panti Asuhan RPA Anugrah Membutuhkan Bantuan Kita","Panti Asuhan Rumah Peduli Anak",panti[3]);
        beritaList.add(a);

        adapter.notifyDataSetChanged();
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
}