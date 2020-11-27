package com.example.yinian.menkou.ping.beans;

import com.contrarywind.interfaces.IPickerViewData;

public class BiudBean implements IPickerViewData {
    public BiudBean() {
    }

    public BiudBean(String id, String name) {
        this.id = id;
        this.name = name;
    }

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPickerViewText() {
        return name;
    }
}
