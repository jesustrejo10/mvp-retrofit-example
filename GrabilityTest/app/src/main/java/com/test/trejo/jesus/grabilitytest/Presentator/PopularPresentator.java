package com.test.trejo.jesus.grabilitytest.Presentator;

import com.test.trejo.jesus.grabilitytest.Model.IModel.IMovie;
import com.test.trejo.jesus.grabilitytest.Presentator.IPresentator.IPopularPresentator;
import com.test.trejo.jesus.grabilitytest.View.IView.IPopularFragment;

/**
 * Created by jesus on 26/03/17.
 */

public class PopularPresentator extends Presentator implements IPopularPresentator{

    private IMovie movie;
    private IPopularFragment mFragment;

    public PopularPresentator(IPopularFragment v){

        this.mFragment = v;
    }


    @Override
    public String getName() {
        mFragment.showToast("jojojo");
        return "hey como estas";
    }
}
