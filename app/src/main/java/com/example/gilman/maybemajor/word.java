package com.example.gilman.maybemajor;

public class word {
    private String labelWord;

    public word(String labelWord, String dataWord) {
        this.labelWord = labelWord;
        this.dataWord = dataWord;
    }

    public String getLabelWord() {
        return labelWord;
    }

    public String getDataWord() {
        return dataWord;
    }

    private String dataWord;

}
