package com.chrissen.reading.rss.model;

import com.chrissen.reading.rss.bean.Article;
import com.chrissen.reading.rss.bean.Entry;
import com.chrissen.reading.rss.bean.Stream;
import com.chrissen.reading.rss.presenter.OnStreamListener;
import com.chrissen.reading.util.http.Api;
import com.chrissen.reading.util.http.RetrofitFactory;

import org.litepal.crud.DataSupport;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/8/10.
 */

public class StreamModelImpl implements StreamModel {

    @Override
    public void getStream(String feedId , final OnStreamListener listener) {
        new RetrofitFactory(Api.FEEDLY_URL).getApiInterface()
                .getStreams(feedId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Stream>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Stream stream) {
                        listener.onStreamSuccess();
                        DataSupport.deleteAll(Entry.class,"source = ?",stream.getTitle());
                        String streamTitle = stream.getTitle();
                        for(Article article : stream.getArticles()){
                            Entry entry = new Entry();
                            entry.setFeedId(article.getArticleId());
                            entry.setOriginId(article.getOriginId());
                            entry.setSource(streamTitle);
                            entry.setAuthor(article.getAuthor());
                            if(article.getContent() != null){
                                entry.setContent(article.getContent().getContent());
                            }
                            if(article.getSummary() != null){
                                entry.setSummary(article.getSummary().getContent());
                            }
                            entry.setUnread(article.isUnread());
                            entry.setPublishedTime(article.getPublished());
                            entry.setTitle(article.getTitle());
                            entry.save();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
