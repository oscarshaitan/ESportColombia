package com.allegorit.e_sportcolombia;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SyncThread {
    Context context;

    public SyncThread(Context context){
        this.context = context;
    }

    public ArrayList<PcObj> getStatus(){
        ArrayList<PcObj> arrayList = new ArrayList<>();
        //[{"id":"01","ping":"0"},{"id":"02","ping":"1"}]
        String result = Tools.senderRespond("","API",context);
        //String result = "[{\"id\":\"01\",\"ping\":\"0\"},{\"id\":\"02\",\"ping\":\"1\"}]";
        try {
            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String ping = jsonObject.getString("ping");
                PcObj pcObj = new PcObj(id,ping);
                arrayList.add(pcObj);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

}
