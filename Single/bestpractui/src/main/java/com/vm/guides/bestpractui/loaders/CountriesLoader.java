package com.vm.guides.bestpractui.loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Pair;

import com.vm.guides.bestpractui.errors.NetworkNotAvailableException;
import com.vm.guides.bestpractui.model.Country;

import java.util.List;

public class CountriesLoader extends AsyncTaskLoader<Pair<List<Country>, Exception>> {

    public CountriesLoader(Context context) {

        super(context);
    }

    @Override
    protected void onStartLoading() {

        super.onStartLoading();

        ConnectivityManager connMgr = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            forceLoad();
        } else {
            deliverResult(new Pair<List<Country>, Exception>(null, new NetworkNotAvailableException()));
        }
    }

    @Override
    public Pair<List<Country>, Exception> loadInBackground() {

        try {
            //            // Set up HTTP post
            //
            //            // HttpClient is more then less deprecated. Need to change to
            // URLConnection
            //            HttpClient httpClient = new DefaultHttpClient();
            //
            //            HttpPost httpPost = new HttpPost(url_select);
            //            httpPost.setEntity(new UrlEncodedFormEntity(param));
            //            HttpResponse httpResponse = httpClient.execute(httpPost);
            //            HttpEntity httpEntity = httpResponse.getEntity();
            //
            //            // Read content & Log
            //            inputStream = httpEntity.getContent();
        } catch (Exception e) {

        }

        // Convert response to string using String Builder
        //        try {
        //            BufferedReader bReader = new BufferedReader(new InputStreamReader
        // (inputStream, "utf-8"), 8);
        //            StringBuilder sBuilder = new StringBuilder();
        //
        //            String line = null;
        //            while ((line = bReader.readLine()) != null) {
        //                sBuilder.append(line + "\n");
        //            }
        //
        //            inputStream.close();
        //            result = sBuilder.toString();
        //
        //        } catch (Exception e) {
        //            Log.e("StringBuilding & BufferedReader", "Error converting result " + e
        // .toString());
        //        }

        //parse JSON data
        //        try {
        //            JSONArray jArray = new JSONArray(result);
        //            for(i=0; i < jArray.length(); i++) {
        //
        //                JSONObject jObject = jArray.getJSONObject(i);
        //
        //                String name = jObject.getString("name");
        //                String tab1_text = jObject.getString("tab1_text");
        //                int active = jObject.getInt("active");
        //
        //            } // End Loop
        //            this.progressDialog.dismiss();
        //        } catch (JSONException e) {
        //            Log.e("JSONException", "Error: " + e.toString());
        //        } // catch (JSONException e)
        //    } // protected void onPostExecute(Void v)
        return null;
    }
}
