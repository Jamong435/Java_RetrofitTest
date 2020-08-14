package com.kim9212.ex79retrofittest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
    }

    public void clickbtn(View view) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://toki666.dothome.co.kr");
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<BoardItem> call = retrofitService.getBoardJson();

        call.enqueue(new Callback<BoardItem>() {
            @Override
            public void onResponse(Call<BoardItem> call, Response<BoardItem> response) {

                BoardItem item = response.body();
                tv.setText(item.name + "," + item.msg);
            }

            @Override
            public void onFailure(Call<BoardItem> call, Throwable t) {

            }
        });

    }

    public void clickbtn2(View view) {
        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<BoardItem> call = retrofitService.getBoardJsonByPath("Retrofit");

        call.enqueue(new Callback<BoardItem>() {
            @Override
            public void onResponse(Call<BoardItem> call, Response<BoardItem> response) {
                BoardItem item = response.body();
                tv.setText(item.name + "," + item.msg);
            }

            @Override
            public void onFailure(Call<BoardItem> call, Throwable t) {

            }
        });


    }

    public void clickbtn3(View view) {
        //1.
        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();

        //2. getMethodTest()
        //3.
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        //4.
        Call<BoardItem> call = retrofitService.getMethodTest("robin", "Nice to meet you.");
        //5.
        call.enqueue(new Callback<BoardItem>() {
            @Override
            public void onResponse(Call<BoardItem> call, Response<BoardItem> response) {
                if (response.isSuccessful()) {
                    BoardItem item = response.body();
                    tv.setText(item.name + ", " + item.msg);
                }
            }

            @Override
            public void onFailure(Call<BoardItem> call, Throwable t) {

            }
        });
    }

    public void clickbtn4(View view) {
        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<BoardItem> call = retrofitService.getMethodTest2("getTest.php", "hong", "good mornig");
        call.enqueue(new Callback<BoardItem>() {
            @Override
            public void onResponse(Call<BoardItem> call, Response<BoardItem> response) {
                if (response.isSuccessful()) {
                    BoardItem item = response.body();
                    tv.setText(item.name + ", " + item.msg);

                }
            }

            @Override
            public void onFailure(Call<BoardItem> call, Throwable t) {

            }
        });


    }

    public void clickbtn5(View view) {
        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        //서버에서 전달할 데이터들을 map collection에 저장
        Map<String, String> datas = new HashMap<>();
        datas.put("name", "park");
        datas.put("msg", "goood friday");

        Call<BoardItem> call = retrofitService.getMethodTest3(datas);
        call.enqueue(new Callback<BoardItem>() {
            @Override
            public void onResponse(Call<BoardItem> call, Response<BoardItem> response) {
                if (response.isSuccessful()) {
                    BoardItem item = response.body();
                    tv.setText(item.name + "," + item.msg);

                }
            }

            @Override
            public void onFailure(Call<BoardItem> call, Throwable t) {

            }
        });
    }

    public void clickbtn6(View view) {
        RetrofitService retrofitService = RetrofitHelper.getRetrofitInstance().create(RetrofitService.class); //엄청줄인상태 이게 현업.
        //보낼데이터를 가진 객체
        BoardItem boardItem = new BoardItem("LEE", "GOOD EVENIG");

        Call<BoardItem> call = retrofitService.postMethodTest(boardItem);
        call.enqueue(new Callback<BoardItem>() {
            @Override
            public void onResponse(Call<BoardItem> call, Response<BoardItem> response) {
                if (response.isSuccessful()) {
                    BoardItem item = response.body();
                    tv.setText(item.name + "," + item.msg);
                }
            }

            @Override
            public void onFailure(Call<BoardItem> call, Throwable t) {

            }
        });
    }

    public void clickbtn7(View view) {
        RetrofitService retrofitService = RetrofitHelper.getRetrofitInstance().create(RetrofitService.class);

        Call<BoardItem> call = retrofitService.postMethodTest2("rosa", "good night");
        call.enqueue(new Callback<BoardItem>() {
            @Override
            public void onResponse(Call<BoardItem> call, Response<BoardItem> response) {
                if (response.isSuccessful()) {
                    BoardItem item = response.body();
                    tv.setText(item.name + "," + item.msg);
                }
            }

            @Override
            public void onFailure(Call<BoardItem> call, Throwable t) {

            }
        });
    }

    public void clickbtn8(View view) {
        RetrofitService retrofitService = RetrofitHelper.getRetrofitInstance().create(RetrofitService.class);
        Call<ArrayList<BoardItem>> call = retrofitService.getBoardArray();
        call.enqueue(new Callback<ArrayList<BoardItem>>() {
            @Override
            public void onResponse(Call<ArrayList<BoardItem>> call, Response<ArrayList<BoardItem>> response) {
                if (response.isSuccessful()) {
                    ArrayList<BoardItem> items = response.body();
//                    tv.setText(items.size());
                    for (BoardItem item : items) {
                        tv.append("\n" + item.name + "," + item.msg + "\n");
                    }

                }
            }

            @Override
            public void onFailure(Call<ArrayList<BoardItem>> call, Throwable t) {

            }
        });

    }

    public void clickbtn9(View view) {
        RetrofitService retrofitService= RetrofitHelper.getRetrofitInstance().create(RetrofitService.class);
        Call<BoardItem> call= retrofitService.urlTest("http://toki666.dothome.co.kr/Retrofit/board.json");
        call.enqueue(new Callback<BoardItem>() {
            @Override
            public void onResponse(Call<BoardItem> call, Response<BoardItem> response) {
                BoardItem item = response.body();
                tv.setText(item.name + "," + item.msg);
            }

            @Override
            public void onFailure(Call<BoardItem> call, Throwable t) {

            }
        });

    }

    public void clickbtn10(View view) {
        Retrofit.Builder builder= new Retrofit.Builder();
        builder.baseUrl("http://toki666.dothome.co.kr");
        builder.addConverterFactory(ScalarsConverterFactory.create());
        //결과를 string으로 받으려면 scalarscoverter를 사용해야함.
       // implementation 'com.squareup.retrofit2:converter-scalars:2.9.0' 이거 추가해야함
        Retrofit retrofit= builder.build();

        RetrofitService retrofitService= retrofit.create(RetrofitService.class);
        Call<String> call= retrofitService.getJsonString();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String s= response.body();
                tv.setText(s);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
