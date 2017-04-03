package com.test.trejo.jesus.grabilitytest.Presentator;

import com.squareup.otto.Bus;

public class Transporter{
    private static Bus bus = new Bus();

    public static Bus getBus() {
        return bus;
    }
}

