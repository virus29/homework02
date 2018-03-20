package view;

public class DataView<ResEntity> {
    private ResEntity data;

    public DataView() {
    }

    public DataView(ResEntity data) {
        this.data = data;
    }

    public ResEntity getData() {
        return data;
    }

    public void setData(ResEntity data) {
        this.data = data;
    }
}
