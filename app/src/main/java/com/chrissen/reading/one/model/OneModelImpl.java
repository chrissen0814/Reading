package com.chrissen.reading.one.model;

import com.chrissen.reading.one.bean.IdList;
import com.chrissen.reading.one.bean.OneList;
import com.chrissen.reading.one.presenter.OnOneListListener;
import com.chrissen.reading.util.PreferenceHelper;
import com.chrissen.reading.util.http.Api;
import com.chrissen.reading.util.http.RetrofitFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/8/12.
 */

public class OneModelImpl implements OneModel {
    private static final String TAG = "OneModelImpl";

    @Override
    public void loadOneList(final OnOneListListener listener) {

        new RetrofitFactory(Api.ONE_URL).getApiInterface()
                .getOneIdList(Api.ONE_CHANNEL,Api.ONE_VERSION,Api.ONE_UUID,Api.ONE_PLATFORM)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<IdList>() {
                    @Override
                    public void accept(IdList idList) throws Exception {
                        PreferenceHelper.putData(PreferenceHelper.ONE_DATA,Integer.valueOf(idList.getDatas().get(0)));
                    }
                });
        String data = String.valueOf(PreferenceHelper.getData(PreferenceHelper.ONE_DATA,1126));
        new RetrofitFactory(Api.ONE_URL).getApiInterface()
                .getOneList(data,Api.ONE_CHANNEL,Api.ONE_VERSION,Api.ONE_UUID,Api.ONE_PLATFORM)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<OneList>() {
                    @Override
                    public void accept(OneList oneList) throws Exception {
                        listener.loadOneListSuccess(oneList);
                    }
                });
    }

}
