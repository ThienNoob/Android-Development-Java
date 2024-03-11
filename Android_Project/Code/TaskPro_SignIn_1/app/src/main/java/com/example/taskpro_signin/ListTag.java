package com.example.taskpro_signin;

import java.io.Serializable;
import java.util.ArrayList;

public class ListTag implements Serializable {
    public String name;
    ArrayList<String> tag;

    public ListTag(String name, ArrayList<String> tag) {
        this.name = name;
        this.tag = tag;
    }
}
