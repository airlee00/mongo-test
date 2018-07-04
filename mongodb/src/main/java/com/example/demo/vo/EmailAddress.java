package com.example.demo.vo;

public class EmailAddress {
    private String id;
    private String value;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return "EmailAddress [id=" + id + ", value=" + value + "]";
    }
    
    
}
