package com.kymjs.frame;

import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;

import com.kymjs.frame.model.IModel;
import com.kymjs.frame.presenter.ActivityPresenter;
import com.kymjs.frame.view.IDelegate;

/**
 * 集成数据-视图绑定的Activity基类(Presenter层)
 *
 * @param <T> View层代理类
 * @param <D> Model层数据类
 * @author kymjs (http://www.kymjs.com/) on 10/23/15.
 */
public abstract class DataBindActivity<T extends IDelegate, D extends IModel> extends
        ActivityPresenter<T> implements LoaderCallbacks<D> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportLoaderManager().initLoader(getLoaderId(), null, this);
    }

    protected abstract int getLoaderId();

    protected abstract Loader<D> getDataLoader(Bundle args);

    public void notifyDataSetChange() {
        getSupportLoaderManager().restartLoader(getLoaderId(), null, this);
    }

    public void viewBindModel(D data) {
    }

    @Override
    public Loader<D> onCreateLoader(int id, Bundle args) {
        return getDataLoader(args);
    }

    @Override
    public void onLoadFinished(Loader<D> loader, D data) {
        viewBindModel(data);
    }

    @Override
    public void onLoaderReset(Loader<D> loader) {
    }
}