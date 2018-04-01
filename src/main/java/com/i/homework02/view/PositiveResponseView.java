package com.i.homework02.view;

import java.util.HashMap;
import java.util.Map;

public class PositiveResponseView {
    Object data;

    public PositiveResponseView() {
        Map<String, String> map = new HashMap<>();
        map.put("result", "success");
        data = map;
    }

    public Object getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PositiveResponseView that = (PositiveResponseView) o;

        return data != null ? data.equals(that.data) : that.data == null;
    }

    @Override
    public int hashCode() {
        return data != null ? data.hashCode() : 0;
    }
}

