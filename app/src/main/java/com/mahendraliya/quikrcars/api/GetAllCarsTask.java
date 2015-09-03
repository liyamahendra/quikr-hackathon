package com.mahendraliya.quikrcars.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.mahendraliya.quikrcars.R;
import com.mahendraliya.quikrcars.utils.Constants;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * Created by mahendraliya on 21/08/15.
 */
public class GetAllCarsTask extends AsyncTask<Void, Void, Void> {
    private Context mContext;
    private ProgressDialog mProgress;
    private ITaskResponse mCallback;
    private int responseCode;
    protected StringBuffer response = new StringBuffer();

    public GetAllCarsTask(Context context, ITaskResponse callback) {
        this.mContext = context;
        this.mCallback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgress = ProgressDialog.show(mContext, "", mContext.getString(R.string.dialog_loading_cars));
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            URL obj = new URL(Constants.URL_GET_ALL_CARS);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setReadTimeout(Constants.HTTP_TIMEOUT);
            con.setConnectTimeout(Constants.HTTP_TIMEOUT);

            con.setRequestMethod("GET");

            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");

            responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if(mProgress != null && mProgress.isShowing()) {
            mProgress.dismiss();
        }

        if(mCallback != null) {
            if(responseCode == 200) {
                mCallback.onSuccess(response.toString());
            } else {
                mCallback.onFailure(response.toString());
            }
        }
    }
}
