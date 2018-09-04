package com.framgia.nguyenvanducc.soundcloud33.data.source;

import android.os.AsyncTask;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseHttpTask<T> extends AsyncTask<String, Void, List<T>> {
    private static final String REQUEST_METHOD = "GET";
    private static final int CONNECTION_TIMEOUT = 15000;
    private static final int READ_TIME_OUT = 10000;
    private static final String BREAK_LINE = "\n";
    private Exception mException;
    private OnLoadDataCompleteListener<T> mOnLoadDataCompleteListener;

    public BaseHttpTask(OnLoadDataCompleteListener<T> onLoadDataCompleteListener) {
        mOnLoadDataCompleteListener = onLoadDataCompleteListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<T> doInBackground(String... strings) {
        String url = strings[0];
        List<T> data = new ArrayList<>();
        try {
            String string = getDataFromUrl(url);
            data = getJsonDataFromString(string);
        } catch (IOException | JSONException e) {
            this.mException = e;
        }
        return data;
    }

    @Override
    protected void onPostExecute(List<T> ts) {
        super.onPostExecute(ts);
        if (mException == null) mOnLoadDataCompleteListener.onSuccess(ts);
        else mOnLoadDataCompleteListener.onFailure(mException);
    }

    private String getDataFromUrl(String urlSource) throws IOException {
        URL url = new URL(urlSource);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(REQUEST_METHOD);
        connection.setConnectTimeout(CONNECTION_TIMEOUT);
        connection.setReadTimeout(READ_TIME_OUT);
        connection.setDoOutput(true);
        connection.connect();
        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(BREAK_LINE);
        }
        bufferedReader.close();
        connection.disconnect();
        return stringBuilder.toString();
    }

    public abstract List<T> getJsonDataFromString(String string) throws JSONException;

}
