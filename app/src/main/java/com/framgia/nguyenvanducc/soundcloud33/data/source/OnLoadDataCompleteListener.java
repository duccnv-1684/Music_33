package com.framgia.nguyenvanducc.soundcloud33.data.source;

import java.util.List;

public interface OnLoadDataCompleteListener<T> {
    void onSuccess(List<T> data);

    void onFailure(Exception e);
}
