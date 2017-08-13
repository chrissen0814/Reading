package com.chrissen.reading.util.http;

import com.chrissen.reading.news.bean.News;
import com.chrissen.reading.one.bean.Answer;
import com.chrissen.reading.one.bean.Essay;
import com.chrissen.reading.one.bean.IdList;
import com.chrissen.reading.one.bean.OneList;
import com.chrissen.reading.picture.bean.Unsplash;
import com.chrissen.reading.rss.bean.Rsses;
import com.chrissen.reading.rss.bean.Stream;
import com.chrissen.reading.weibo.bean.HomeLine;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/8/1.
 */

public interface ApiInterface {

    @Headers("Authorization: Client-ID 064a334daec89ab755cac3198a51f7e68b93dcd26f013722dac21d16c4f0ae03")
    @GET("photos")
    Observable<List<Unsplash>> getUnsplash(@Query("page") int page , @Query("per_page")int per_page);

    @GET("get")
    Observable<News> getNews(@Query("channel") String channel  , @Query("start") int start, @Query("num") int count , @Query("appkey") String appkey);

    @GET("home_timeline.json")
    Observable<HomeLine> getWeiboHomeLine(@Query("access_token") String accessToken , @Query("page") int page);

    @GET("search/feeds")
    Observable<Rsses> searchFeeds(@Query("query")String name);

    @GET("streams/contents")
    Observable<Stream> getStreams(@Query("streamId") String feedId);

    @GET("onelist/idlist/")
    Observable<IdList> getOneIdList(@Query("channel") String channel,@Query("version")String version , @Query("uuid")String uuid ,@Query("platform")String platform);

    @GET("onelist/{data}/0")
    Observable<OneList> getOneList(@Path("data") String data , @Query("channel") String channel,@Query("version")String version , @Query("uuid")String uuid ,@Query("platform")String platform);

    @GET("essay/{itemId}")
    Observable<Essay> getOneEssay(@Path("itemId")String itemId , @Query("channel") String channel,@Query("version")String version , @Query("uuid")String uuid ,@Query("platform")String platform);

    @GET("question/{itemId}")
    Observable<Answer> getOneQuestion(@Path("itemId")String itemId,@Query("channel") String channel,@Query("version")String version , @Query("uuid")String uuid ,@Query("platform")String platform);

}
