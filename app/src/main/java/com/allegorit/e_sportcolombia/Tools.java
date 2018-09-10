package com.allegorit.e_sportcolombia;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static android.content.Context.LOCATION_SERVICE;

public class Tools {

    public static boolean isConnected(Context context) throws IOException, InterruptedException {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    public static boolean gpsEnable(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            return true;
        } else {
            return false;
        }
    }

    public static String Sha256(String input){
        MessageDigest mDigest = null;
        try {
            mDigest = MessageDigest.getInstance("SHA256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    public static String Md5(String input){
        MessageDigest mDigest = null;
        try {
            mDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    public static String toPostData(ArrayList<String> data, ArrayList<String> variable){
        String post_data = "";
        try {
            for (int i = 0; i < data.size(); i++) {
                post_data += URLEncoder.encode(variable.get(i), "UTF-8")
                        + "=" + URLEncoder.encode(data.get(i), "UTF-8");
                if (i != data.size() - 1) {
                    post_data += "&";
                }
            }

            post_data += "&" +URLEncoder.encode("Token", "UTF-8") + "=" + URLEncoder.encode("TRUE", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return post_data;
    }

    public static String senderRespond(String post_package, String func, Context context) {
        String result = "";
        try {
            if (Tools.isConnected(context)) {
                BackgroundWorker backgroundWorker = new BackgroundWorker(context);
                result = backgroundWorker.execute(post_package, func).get().toString();

                if(!result.equals("NULL")){
                    return  result;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, R.string.ERROR_404,Toast.LENGTH_LONG).show();
            return "IOException";//

        } catch (InterruptedException e){
            e.printStackTrace();
            Toast.makeText(context, R.string.ERROR_200,Toast.LENGTH_LONG).show();
            return "InterruptedException";
        } catch (ExecutionException e){
            Toast.makeText(context, R.string.ERROR_300,Toast.LENGTH_LONG).show();
            e.printStackTrace();
            return "ExecutionException";
        }
        return "NULL";
    }

    /*
    public static boolean syncActions(String post_package, Context context) {
        DBHelper db;
        db = new DBHelper(context);
        boolean isSended = false;
        try {
            if (Tools.isConnected(context)) {
                BackgroundWorker backgroundWorker = new BackgroundWorker(context);
                String result = backgroundWorker.execute(post_package, "").get().toString();
                if(!result.equals("NULL")){
                    if (result.equals("true"))return  true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, R.string.ERROR_404,Toast.LENGTH_LONG).show();
            return false;//

        } catch (InterruptedException e){
            e.printStackTrace();
            Toast.makeText(context, R.string.ERROR_200,Toast.LENGTH_LONG).show();
            return false;
        } catch (ExecutionException e){
            Toast.makeText(context, R.string.ERROR_300,Toast.LENGTH_LONG).show();
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public static void uploadMedia(Context context,ArrayList<String> sign, ArrayList<String>photos){

        for (String signature : sign) {
            UploadMedia uploadMedia =new UploadMedia();
            try {
                String result =  uploadMedia.execute(signature).get().toString();
                if(result!="Executed"){
                    DBHelper dbHelper = new DBHelper(context);
                    dbHelper.insertMedia(signature);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        for (String photo : photos) {

            UploadMedia uploadMedia =new UploadMedia();
            try {
                String result =  uploadMedia.execute(photo).get().toString();
                if(result!="Executed"){
                    DBHelper dbHelper = new DBHelper(context);
                    dbHelper.insertMedia(photo);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean syncMedia(Context context, String path){
        UploadMedia uploadMedia =new UploadMedia();
        String result = null;
        try {
            result = uploadMedia.execute(path).get().toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if(result!="Executed"){
            return false;
        }
        else return true;
    }

    */

}
