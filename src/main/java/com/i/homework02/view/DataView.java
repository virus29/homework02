package com.i.homework02.view;

import java.util.Objects;

public class DataView<E> {

    private E data;

    public DataView() {}

    public DataView(E data) {
        this.data = data;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DataView)) return false;
        DataView<?> dataView = (DataView<?>) o;
        return Objects.equals(data, dataView.data);
    }

    @Override
    public int hashCode() {

        return Objects.hash(data);
    }
}
