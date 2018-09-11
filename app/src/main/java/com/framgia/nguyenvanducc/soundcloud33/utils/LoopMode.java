package com.framgia.nguyenvanducc.soundcloud33.utils;

import android.support.annotation.IntDef;

@IntDef({
        LoopMode.NO_LOOP,
        LoopMode.LOOP_ONE,
        LoopMode.LOOP_ALL
})

public @interface LoopMode {
    int NO_LOOP = 0;
    int LOOP_ONE = 1;
    int LOOP_ALL = 2;
}
