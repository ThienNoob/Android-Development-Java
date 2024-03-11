package com.example.broads;

import java.io.Serializable;
import java.util.ArrayList;

public class Broads implements Serializable {
    int image;
    String name;

    ArrayList<ListTag> arrayListTag;

    public Broads(int image, String name, ArrayList<ListTag> arrayListTag) {
        this.image = image;
        this.name = name;
        this.arrayListTag = arrayListTag;
    }
}
