package com.sunzn.matisse.sample;

import com.sunzn.matisse.library.ui.MatisseActivity;
import com.sunzn.tinker.library.Tinker;

public class TargetActivity extends MatisseActivity {

    @Override
    public void onContentViewSet() {
        Tinker tinker = new Tinker(this);
        tinker.setTinkerResource(R.color.bars);
        Tinker.setBarLightMode(this);
    }

}
