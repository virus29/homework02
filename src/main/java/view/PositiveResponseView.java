package view;

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
}

