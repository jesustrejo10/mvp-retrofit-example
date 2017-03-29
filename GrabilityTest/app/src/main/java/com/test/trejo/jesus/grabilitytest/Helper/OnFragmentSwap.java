package com.test.trejo.jesus.grabilitytest.Helper;

import android.os.Bundle;

/**
 * Created by jesus on 28/03/17.
 */
public interface OnFragmentSwap {
    /**
     * Metodo encargado de realizar la llamada en el Main activity encargado
     * de realizar un cambio de Fragment
     * @param FragmentName Nombre del fragment al que se desea acceder
     * @param bundle bundle que contiene la informacion que se le desea
     *               suministrar al Fragment
     */
    void onSwap(String FragmentName, Bundle bundle);
}
