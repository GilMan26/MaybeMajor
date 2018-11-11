package com.example.gilman.maybemajor;

public class QRCode {
    int id;
    String text;
    String[] fields, values;
    Boolean isCustom;

    public QRCode(int id, String text, Boolean isCustom) {
        this.id = id;
        this.text = text;
        this.isCustom = isCustom;
    }

    public QRCode(int id, String[] fields, String[] values, Boolean isCustom) {
        this.id = id;
        this.fields = fields;
        this.values = values;
        this.isCustom = isCustom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public Boolean getCustom() {
        return isCustom;
    }

    public void setCustom(Boolean custom) {
        isCustom = custom;
    }
}
