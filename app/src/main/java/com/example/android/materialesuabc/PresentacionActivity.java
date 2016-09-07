package com.example.android.materialesuabc;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.LinePageIndicator;
import com.viewpagerindicator.PageIndicator;

public class PresentacionActivity extends AppCompatActivity {
    private LinePageIndicator linePageIndicator;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private int num_pages;
    private long id;
    private int imagesPresentacion1[];
    private String imagesPresentacion2[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentacion);
        Intent intent = getIntent();
        id = intent.getLongExtra("id",0);

        if(id == 0){
            //presentacion Introduccion a Materiales 1
            imagesPresentacion2 = new String[]{
                    "http://i.imgur.com/CtjQSNP.png","http://i.imgur.com/0NZsGT6.png",
                    "http://i.imgur.com/xwvmOHi.png","http://i.imgur.com/iv6SrvJ.png",
                    "http://i.imgur.com/JkzAzEN.png","http://i.imgur.com/He2xXOH.png",
                    "http://i.imgur.com/Fp8tnDn.png","http://i.imgur.com/NqhpilL.jpg",
                    "http://i.imgur.com/FeW079i.png","http://i.imgur.com/6IlDMQ5.png",
                    "http://i.imgur.com/P8c1ezA.png","http://i.imgur.com/KZBtb76.png",
                    "http://i.imgur.com/bCPKcuy.png","http://i.imgur.com/reXAJQj.png",
                    "http://i.imgur.com/PEJGCxt.png","http://i.imgur.com/iSDFOx4.png",
                    "http://i.imgur.com/1ET6zdm.png","http://i.imgur.com/ew9n6xj.png",
                    "http://i.imgur.com/0PCrnX6.png","http://i.imgur.com/wf5ApjB.png",
                    "http://i.imgur.com/1QEJ7az.png","http://i.imgur.com/ja3JnAk.png",
                    "http://i.imgur.com/2Ca3qh4.png","http://i.imgur.com/C0bVN2q.png",
                    "http://i.imgur.com/hQM2vAG.png","http://i.imgur.com/VwwyaBh.png",
                    "http://i.imgur.com/V4oXIcC.png","http://i.imgur.com/BJcR8gI.png",
                    "http://i.imgur.com/9zAhAcr.png","http://i.imgur.com/OAerv0v.png",
                    "http://i.imgur.com/OldM5QJ.png","http://i.imgur.com/pg2I093.png",
                    "http://i.imgur.com/VqN8qhO.png","http://i.imgur.com/ObYYAx0.png",
                    "http://i.imgur.com/13TC6wo.png","http://i.imgur.com/Ui2ltvH.png",
                    };
            num_pages = imagesPresentacion2.length;

        }
        if(id == 1){
            //presentacion Introduccion a Materiales 2
            imagesPresentacion2 = new String[]{
                        "http://i.imgur.com/D1RT354.png","http://i.imgur.com/jNYWHjK.png",
                    "http://i.imgur.com/Oh2tTFh.png","http://i.imgur.com/mJVCwbU.png",
                    "http://i.imgur.com/bwXAJEL.png","http://i.imgur.com/9O6uhLm.png",
                    "http://i.imgur.com/Tjknzha.png","http://i.imgur.com/lAkM0Bh.png",
                    "http://i.imgur.com/f47addG.png","http://i.imgur.com/1n99iDw.png",
                    "http://i.imgur.com/nCJB6he.png","http://i.imgur.com/COnEa75.png",
                    "http://i.imgur.com/ZKWpQkL.png","http://i.imgur.com/zejltyt.png",
                    "http://i.imgur.com/PMxiwEM.png","http://i.imgur.com/9mVJzZh.png",
                    "http://i.imgur.com/O1L9oFX.png","http://i.imgur.com/WQn8KIq.png",
                    "http://i.imgur.com/PqJ2Rd7.png","http://i.imgur.com/8bixEp9.png",
                    "http://i.imgur.com/ckg9iLs.png","http://i.imgur.com/GEjgsvN.png",
                    "http://i.imgur.com/vFIlpgl.png","http://i.imgur.com/AomziHw.png",
                    "http://i.imgur.com/hlRaO7h.png"
                    };
            num_pages = imagesPresentacion2.length;
        }
        if(id == 2){
            //presentacion Introduccion a Materiales 3
            imagesPresentacion2 = new String[]{
                   "http://i.imgur.com/xslItqP.png","http://i.imgur.com/bMjTvgZ.png",
                    "http://i.imgur.com/UvKWXIv.png","http://i.imgur.com/W9tCTXx.png",
                    "http://i.imgur.com/JmXdJOa.png", "http://i.imgur.com/DzarIx5.png",
                    "http://i.imgur.com/j96Iha5.png", "http://i.imgur.com/gvw98nR.png",
            };
            num_pages = imagesPresentacion2.length;
        }
        if(id == 3){
        //Conservacion de derechos de Patentes
            imagesPresentacion2 = new String[]{
                    "http://i.imgur.com/zA4R1oB.png","http://i.imgur.com/DU6A5pU.png",
                    "http://i.imgur.com/VW29s70.png","http://i.imgur.com/Ovd1CiO.png",
                    "http://i.imgur.com/d7gTi1M.png","http://i.imgur.com/y0K9ybN.png",
                    "http://i.imgur.com/w5bh3eT.png","http://i.imgur.com/vbIQC0o.png",
                    "http://i.imgur.com/bGGpyvV.png","http://i.imgur.com/mAolLKj.png",
                    "http://i.imgur.com/lQOutJ8.png","http://i.imgur.com/cekAgVhg.png",
                    "http://i.imgur.com/E4wtID1.jpg","http://i.imgur.com/EPfvL3C.png",
                    "http://i.imgur.com/Stv8x1O.png","http://i.imgur.com/GCUUxrB.png",
                    "http://i.imgur.com/0j51mt8.png","http://i.imgur.com/wcxwpx0.png",
                    "http://i.imgur.com/6TlI3Nx.png","http://i.imgur.com/jJP5N44.png",
                    "http://i.imgur.com/F48t8Cl.png","http://i.imgur.com/VUWIVmx.png",
                    "http://i.imgur.com/8PijIgy.png","http://i.imgur.com/DjSijXu.png",
                    "http://i.imgur.com/OuGNQsN.png","http://i.imgur.com/uEfJHlQ.png",
                    "http://i.imgur.com/xNdIWI0.png","http://i.imgur.com/EBmtIJY.png",
                    "http://i.imgur.com/yNhRkAY.png","http://i.imgur.com/NqQCold.png",
                    "http://i.imgur.com/P3P9ipN.png","http://i.imgur.com/3vfdBWE.png",
                    "http://i.imgur.com/rOgApg4.png","http://i.imgur.com/MOjESqQ.png",
                    "http://i.imgur.com/sM8oyoQ.png","http://i.imgur.com/roc7YiR.png",
                    "http://i.imgur.com/2eKrfNw.png"};
                num_pages = imagesPresentacion2.length;
        }
        if(id == 4){
            //Redaccion de Patentes
            imagesPresentacion2 = new String[]{
                    "http://i.imgur.com/tBehQkS.png","http://i.imgur.com/RLHuefl.png",
                    "http://i.imgur.com/jHE1EFv.png","http://i.imgur.com/u6z9V0o.png",
                    "http://i.imgur.com/KNx29Dl.png","http://i.imgur.com/G1ExYYu.png",
                    "http://i.imgur.com/ulIYkJw.png","http://i.imgur.com/Czu0plp.png",
                    "http://i.imgur.com/VbjqRYc.png","http://i.imgur.com/ObXQLOI.png",
                    "http://i.imgur.com/L9BCvV5.png","http://i.imgur.com/YU1Pipg.png",
                    "http://i.imgur.com/Cx3m50y.png","http://i.imgur.com/X5Xw2yh.png",
                    "http://i.imgur.com/0Hcj8OB.png","http://i.imgur.com/T09Gdi3.png",
                    "http://i.imgur.com/V0TKXss.png","http://i.imgur.com/u0nwehn.png",
                    "http://i.imgur.com/jbxE5gh.png","http://i.imgur.com/abKcuNm.png",
                    "http://i.imgur.com/1ZvTQhx.png","http://i.imgur.com/R5myxaA.png",
                    "http://i.imgur.com/YcPrA0F.png","http://i.imgur.com/tMoUhw9.png",
                    "http://i.imgur.com/FNRSuNt.png","http://i.imgur.com/LnuhUKk.png",
                    "http://i.imgur.com/Oj4tIxn.png","http://i.imgur.com/yTDTr4R.png",
                    "http://i.imgur.com/mzui0lv.png","http://i.imgur.com/1ElF2Nx.png",
                    "http://i.imgur.com/4IeOQYK.png","http://i.imgur.com/FmY2OLF.png",
                    "http://i.imgur.com/l3p6hdi.png","http://i.imgur.com/Ixi6asR.png",
                    "http://i.imgur.com/MokfKO8.png","http://i.imgur.com/EHi4uxZ.png",
                    "http://i.imgur.com/jkijUW2.png","http://i.imgur.com/VPMvHnd.png",
                    "http://i.imgur.com/XqcPo4q.png","http://i.imgur.com/LF1q5wg.png",
                    "http://i.imgur.com/59KGkql.png","http://i.imgur.com/QqaMfNm.png",
                    "http://i.imgur.com/vpjU3Xx.png","http://i.imgur.com/dYB4TWZ.png",
                    "http://i.imgur.com/NH8JCD8.png","http://i.imgur.com/LISpRyh.png",
                    "http://i.imgur.com/yNOLWhb.png","http://i.imgur.com/fDFYwXm.png",
                    "http://i.imgur.com/VuxHwKJ.png","http://i.imgur.com/0EbSuND.png",
                    "http://i.imgur.com/X6y8NJ6.png","http://i.imgur.com/q5vkhkC.png",
                    "http://i.imgur.com/yePnVdb.png","http://i.imgur.com/LeIvJZM.png",
                    "http://i.imgur.com/Mdlf3ni.png","http://i.imgur.com/nwF1cAK.png",
                    "http://i.imgur.com/SkEigrQ.png","http://i.imgur.com/s74AN3f.png",
                    "http://i.imgur.com/nlBIcK2.png","http://i.imgur.com/cbzGs6D.png",
                    "http://i.imgur.com/RDSCX3E.png","http://i.imgur.com/FCMBgl2.png",
                    "http://i.imgur.com/OmW9rJ2.png","http://i.imgur.com/byX2nV7.png",
                    "http://i.imgur.com/BKbtEe9.png","http://i.imgur.com/6AuaXLO.png",
                    "http://i.imgur.com/OD7dWt8.png","http://i.imgur.com/wKOuMzt.png",
                    "http://i.imgur.com/Dj1nViS.png","http://i.imgur.com/OvH2t0X.png",
                    "http://i.imgur.com/cb89Mr9.png","http://i.imgur.com/0X6fL5B.png",
                    "http://i.imgur.com/C6Tgpys.png","http://i.imgur.com/G25HyfG.png",
                    "http://i.imgur.com/NqxJcF1.png","http://i.imgur.com/v6fPRVX.png",
                    "http://i.imgur.com/i7roSle.png","http://i.imgur.com/jtcNhDJ.png",
                    "http://i.imgur.com/thAOMFa.png","http://i.imgur.com/m0ooWHW.png",
                    "http://i.imgur.com/N0rTurr.png","http://i.imgur.com/mVgQlTy.png",
                    "http://i.imgur.com/hmC16n6.png","http://i.imgur.com/Qazl7SL.png",
                    "http://i.imgur.com/T9alfQT.png","http://i.imgur.com/vUhSL8Z.png",
                    "http://i.imgur.com/Blcm0lT.png","http://i.imgur.com/wUBeEvm.png",
                    "http://i.imgur.com/HK9BpOk.png","http://i.imgur.com/Y7rQJtB.png"
            };
            num_pages = imagesPresentacion2.length;
        }
        if(id == 4){
            //Los patentes como fuente de información
            imagesPresentacion2 = new String [] {
                    "http://i.imgur.com/g8rQmhA.jpg","http://i.imgur.com/dfO4agS.jpg",
                    "http://i.imgur.com/msEavlw.jpg","http://i.imgur.com/Do3QJ6h.jpg",
                    "http://i.imgur.com/oSLHvTi.jpg","http://i.imgur.com/KB03IJt.jpg",
                    "http://i.imgur.com/C7hu77x.jpg","http://i.imgur.com/sL5wsxU.jpg",
                    "http://i.imgur.com/gaY5Wxl.jpg","http://i.imgur.com/zvDhtOJ.jpg",
                    "http://i.imgur.com/NrkOe5t.jpg","http://i.imgur.com/t9poqCv.jpg",
                    "http://i.imgur.com/8aCcnZF.jpg","http://i.imgur.com/jwZ42CS.jpg",
                    "http://i.imgur.com/LjHFVjc.jpg","http://i.imgur.com/uW2Wesb.jpg",
                    "http://i.imgur.com/L28glvS.jpg","http://i.imgur.com/4Ixkex3.jpg",
                    "http://i.imgur.com/nJaoMLk.jpg","http://i.imgur.com/h0deMjW.jpg",
                    "http://i.imgur.com/rH7AR5C.jpg","http://i.imgur.com/tpRlRF2.jpg",
                    "http://i.imgur.com/y6CeVct.jpg","http://i.imgur.com/4rvnXWF.jpg",
                    "http://i.imgur.com/TMYW3e9.jpg","http://i.imgur.com/PGHyQG4.jpg",
                    "http://i.imgur.com/ANGcLxJ.jpg","http://i.imgur.com/Nf9WB27.jpg",
                    "http://i.imgur.com/6dvRscw.jpg","http://i.imgur.com/VWHpa1K.jpg",
                    "http://i.imgur.com/crcWxsx.jpg","http://i.imgur.com/2cyZNSB.jpg",
                    "http://i.imgur.com/aKUDyAo.jpg","http://i.imgur.com/dRnAAIh.jpg",
                    "http://i.imgur.com/IrE7IOk.jpg","http://i.imgur.com/DP7JTSA.jpg",
                    "http://i.imgur.com/FFMydKl.jpg","http://i.imgur.com/1M3Q7eY.jpg",
                    "http://i.imgur.com/ika9WVe.jpg","http://i.imgur.com/cGYZ0lR.jpg",
                    "http://i.imgur.com/O4eNNrE.jpg","http://i.imgur.com/nbi1yl9.jpg",
                    "http://i.imgur.com/qnRlu7Y.jpg","http://i.imgur.com/XbkqlRB.jpg",
                    "http://i.imgur.com/vtowYRC.jpg","http://i.imgur.com/lNqIGPL.jpg",
                    "http://i.imgur.com/aQVc9A2.jpg","http://i.imgur.com/1iAI1DV.jpg",
                    "http://i.imgur.com/T0ktHMZ.jpg","http://i.imgur.com/7hdq8n4.jpg",
                    "http://i.imgur.com/vVsSL16.jpg","http://i.imgur.com/ngIKfgu.jpg",
                    "http://i.imgur.com/81a6s62.jpg","http://i.imgur.com/vP7xnEM.jpg",
                    "http://i.imgur.com/kHdPnk3.jpg","http://i.imgur.com/XFjcYbx.jpg",
                    "http://i.imgur.com/FTUdgcH.jpg","http://i.imgur.com/wAF2xoy.jpg",
                    "http://i.imgur.com/Fw52HNJ.jpg","http://i.imgur.com/ipciwM4.jpg",
                    "http://i.imgur.com/vbHVtfO.jpg","http://i.imgur.com/wSeEiQe.jpg",
                    "http://i.imgur.com/JdVo5Le.jpg","http://i.imgur.com/8Z7w3WJ.jpg",
                    "http://i.imgur.com/87FegYn.jpg","http://i.imgur.com/UWXqL6c.jpg",
                    "http://i.imgur.com/9HCqJWh.jpg","http://i.imgur.com/0f6Cwfu.jpg",
                    "http://i.imgur.com/AaeiAF8.jpg","http://i.imgur.com/RBacOo5.jpg",
                    "http://i.imgur.com/9oHYLJk.jpg","http://i.imgur.com/LBbNx0Q.jpg",
                    "http://i.imgur.com/dA3Odx9.jpg","http://i.imgur.com/v70bGId.jpg",
                    "http://i.imgur.com/PzbHCO4.jpg","http://i.imgur.com/VPJHiYu.jpg",
                    "http://i.imgur.com/qcTmebA.jpg","http://i.imgur.com/hP0z5ge.jpg",
                    "http://i.imgur.com/E60sypI.jpg","http://i.imgur.com/NLMrm0K.jpg",
                    "http://i.imgur.com/hZCOhmN.jpg","http://i.imgur.com/ow46AaE.jpg",
                    "http://i.imgur.com/ArUQu8D.jpg","http://i.imgur.com/kqQ2KiG.jpg",
                    "http://i.imgur.com/ijGpcYO.jpg","http://i.imgur.com/u8KONLx.jpg",
                    "http://i.imgur.com/vAtiepj.jpg","http://i.imgur.com/4A1kSAi.jpg",
                    "http://i.imgur.com/jWjMnGP.jpg","http://i.imgur.com/pYmrFhe.jpg",
                    "http://i.imgur.com/qDXPOl7.jpg","http://i.imgur.com/plgnS0B.jpg",
                    "http://i.imgur.com/WcQ9YX4.jpg","http://i.imgur.com/H1Gr46S.jpg",
                    "http://i.imgur.com/LReZ8P9.jpg","http://i.imgur.com/x2IGeh6.jpg",
                    "http://i.imgur.com/WBWjONv.jpg","http://i.imgur.com/As25fA8.jpg",
                    "http://i.imgur.com/8Ypmnmc.jpg","http://i.imgur.com/dzE9C1f.jpg",
                    "http://i.imgur.com/3ya5hQc.jpg","http://i.imgur.com/AbwuAKT.jpg",
                    "http://i.imgur.com/gAVzDLA.jpg","http://i.imgur.com/f9YfmDI.jpg",
                    "http://i.imgur.com/X0xXYVy.jpg","http://i.imgur.com/iS4B0lT.jpg",
                    "http://i.imgur.com/qkLjSkG.jpg","http://i.imgur.com/n0NrE0L.jpg",
                    "http://i.imgur.com/yCpYQJg.jpg","http://i.imgur.com/fIUPLw3.jpg",
                    "http://i.imgur.com/W2it4gi.jpg","http://i.imgur.com/98aoTPO.jpg",
                    "http://i.imgur.com/nI1BX6Z.jpg","http://i.imgur.com/92U2Q10.jpg",
                    "http://i.imgur.com/iLkZfhE.jpg",
            };
            num_pages = imagesPresentacion2.length;
        }
        linePageIndicator = (LinePageIndicator) findViewById(R.id.indicador_presentacion);
        viewPager = (ViewPager) findViewById(R.id.viewpagerPresentacion);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        linePageIndicator.setViewPager(viewPager);

    }
    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            DiapositivaFragment fragment = new DiapositivaFragment();
            Bundle bundle = new Bundle();
            bundle.putString("string",imagesPresentacion2[position]);
            bundle.putInt("posicion",position);
            fragment.setArguments(bundle);
            return fragment;
        }
        @Override
        public int getCount() {
            return num_pages;
        }
    }

}
