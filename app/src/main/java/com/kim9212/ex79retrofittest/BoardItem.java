package com.kim9212.ex79retrofittest;

import com.google.gson.annotations.SerializedName;

public class BoardItem {


    String name;
    String msg;

    public BoardItem(){
    }

    public BoardItem(String name, String msg) {

        //만약 json으로받고 다른변수명을 사용하고싶다면
      //  @SerializedName("msg")

        this.name = name;
        this.msg = msg;
    }
}
