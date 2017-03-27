package com.test.trejo.jesus.grabilitytest.View;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.test.trejo.jesus.grabilitytest.Presentator.IPresentator.IPopularPresentator;
import com.test.trejo.jesus.grabilitytest.Presentator.PopularPresentator;
import com.test.trejo.jesus.grabilitytest.R;
import com.test.trejo.jesus.grabilitytest.View.IView.IPopularFragment;
import com.test.trejo.jesus.grabilitytest.View.IView.IView;

/**
 * A placeholder fragment containing a simple view.
 */
public class PopularFragment extends Fragment implements IPopularFragment, IView {

    public PopularFragment() {
    }

    IPopularPresentator presentator;
    private Button mButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_principal, container, false);
        mButton = (Button) v.findViewById(R.id.click_here);
        presentator = new PopularPresentator(this);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presentator.getName();
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void showToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }
}
