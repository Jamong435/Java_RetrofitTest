package com.kim9212.ex79retrofittest;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface RetrofitService {

    @GET("/Retrofit/board.json")
    Call<BoardItem> getBoardJson();

    @GET("/{folder}/board.json")//folder은  이름이아니라 폴더 가들어갈것이라는 뜻을 나타내는것
    Call<BoardItem> getBoardJsonByPath(@Path("folder")String folder);

    @GET("/Retrofit/getTest.php") //name과 msg값에 내가 쓴것을 보낸다는것이다.
    Call<BoardItem> getMethodTest(@Query("name") String name,@Query("msg") String msg);

    //4.GET방식으로 하되 값 전달하면서 경로파일명도 지정.
    @GET("/Retrofit/{filename}")
    Call<BoardItem> getMethodTest2
    (@Path("filename")String filename,@Query("name")String name,@Query("msg")String msg); //파일의네임을 스트링으로받는다

    //5.get방식으로 보낼값들을 한방에 Map collection으로 한방에 전달하기.@Querymap!! , 안드로이드 컬렉션종류 검색 MAP방식 LIST방식

    @GET("/Retrofit/getTest.php")
    Call<BoardItem> getMethodTest3(@QueryMap Map<String,String> datas); //앞에것은 키값 뒤는 벨류

    //6.Post방식으로 데이터 보내기[@body]-객체를 전달하면 자동으로 json문자열로 변환하여 body데이터로 넣어 서버로전달
    @POST("/Retrofit/postTest.php")//name이나 msg를 나누는게아니라 통으로전달한다는뜻
    Call<BoardItem> postMethodTest(@Body BoardItem item);

    //7.Post방식에서 멤버값을 get방식처럼 별도로 보내고싶을때
    //[@Field]-단,이 어노테이션은 반드시 @FormurlEncoded와 함께
    @FormUrlEncoded
    @POST("/Retrofit/postTest2.php")
    Call<BoardItem> postMethodTest2(@Field("name")String name,@Field("msg")String msg);

    //8.응답받을 데이터가 jsonarray일때
    @GET("/Retrofit/boardArray.json")
    Call<ArrayList<BoardItem>> getBoardArray();
    //9.baseurl을 무시하고 지정된 url로 연결[@Url]
    @GET
    Call<BoardItem> urlTest(@Url String url);

    //이제것 응답결과가 항상json이었다 그걸 gson을 이용하여 string으로받고싶다면
    @GET("/Retrofit/board.json")
    Call<String> getJsonString();



}
