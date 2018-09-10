package com.allegorit.e_sportcolombia;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;


public class BackgroundWorker extends AsyncTask<String,Void,String> {

    private final String SERVER = "http://esportscol.javilaortiz.com/controlador/api.php";
    String login_url = "";
    Context context;
    String post_data = "";

    BackgroundWorker(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String data = params[0];
        String result = "";
        try {
            post_data =  data;

            URL url = new URL(SERVER);
            final HttpURLConnection connection = prepareConnection(url);
            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));


            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            connection.disconnect();
        }catch (MalformedURLException e) {
            e.printStackTrace();
            //Toast.makeText(context,R.string.ERROR_400,Toast.LENGTH_SHORT).show();
            result = "NULL";
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            //Toast.makeText(context,R.string.ERROR_500,Toast.LENGTH_SHORT).show();
            result = "NULL";
        }catch (IOException e) {
            e.printStackTrace();
            //Toast.makeText(context,R.string.ERROR_404,Toast.LENGTH_SHORT).show();
            result = "NULL";
        }catch (KeyManagementException e) {
            e.printStackTrace();
            //Toast.makeText(context,R.string.ERROR_600,Toast.LENGTH_SHORT).show();
            result ="NULL";
        }
        return result;
    }


    private HttpURLConnection prepareConnection(final URL verifierURL)
            throws IOException, NoSuchAlgorithmException, KeyManagementException {
       /* TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }
        };


        SSLContext sslcontext = SSLContext.getInstance("SSL");
        sslcontext.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext.getSocketFactory());

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);


       /*SSLContext sslcontext = SSLContext.getInstance("TLSv1");

        try {
            sslcontext.init(null,
                    null,
                    null);
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        SSLSocketFactory NoSSLv3Factory = new NoSSLv3SocketFactory(sslcontext.getSocketFactory());
        HttpsURLConnection.setDefaultSSLSocketFactory(NoSSLv3Factory); */

        final HttpURLConnection connection = (HttpURLConnection) verifierURL
                .openConnection();
        //final HttpsURLConnection connection = (HttpsURLConnection) verifierURL
        // .openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        return connection;
    }
}
