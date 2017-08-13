package com.chrissen.reading.one.model;

import com.chrissen.reading.one.bean.Answer;
import com.chrissen.reading.one.bean.Essay;
import com.chrissen.reading.one.presenter.OnOneContentListener;
import com.chrissen.reading.one.presenter.OnOneQuestionListener;
import com.chrissen.reading.util.http.Api;
import com.chrissen.reading.util.http.RetrofitFactory;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/8/12.
 */

public class OneContentModelImpl implements OneContentModel {
    @Override
    public void loadOneContent(String itemId, final OnOneContentListener listener) {
        new RetrofitFactory(Api.ONE_URL).getApiInterface()
                .getOneEssay(itemId,Api.ONE_CHANNEL,Api.ONE_VERSION,Api.ONE_UUID,Api.ONE_PLATFORM)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Essay>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Essay essay) {
                        listener.loadContentSuccess(essay);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void loadOneQyestion(String itemId , final OnOneQuestionListener listener) {
        new RetrofitFactory(Api.ONE_URL).getApiInterface()
                .getOneQuestion(itemId,Api.ONE_CHANNEL,Api.ONE_VERSION,Api.ONE_UUID,Api.ONE_PLATFORM)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Answer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Answer answer) {
                        listener.onQuestionSuccess(answer);
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
