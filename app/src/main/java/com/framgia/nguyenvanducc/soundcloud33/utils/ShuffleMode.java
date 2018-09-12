package com.framgia.nguyenvanducc.soundcloud33.utils;

import android.support.annotation.IntDef;

@IntDef({
        ShuffleMode.OFF,
        ShuffleMode.ON
})

public @interface ShuffleMode {
    int OFF = 0;
    int ON = 1;
}
