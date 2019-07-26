package com.softdesign.enumerable;

public enum ExternalApiEnum {

    VALIDATECPFURI("https://user-info.herokuapp.com/users/");

    private String value;

    ExternalApiEnum(String s) {
        this.value = s;
    }

    public String getValue() {
        return  this.value;
    }

}
