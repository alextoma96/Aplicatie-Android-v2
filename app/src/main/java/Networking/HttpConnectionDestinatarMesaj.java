package Networking;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

import Commons.DestinatarMesaj;

/**
 * Created by intern on 9/22/2017.
 */

public class HttpConnectionDestinatarMesaj extends AsyncTask<String, Void, ArrayList<DestinatarMesaj>> {

    URL url;
    HttpURLConnection connection;

    @Override
    protected ArrayList<DestinatarMesaj> doInBackground(String... params) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            return parseHttpResponse(stringBuilder.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    private ArrayList<DestinatarMesaj> parseHttpResponse(String JSONString) throws JSONException, ParseException {
        ArrayList<DestinatarMesaj> listaDestinatarMesaj = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(JSONString);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonDestinatarMesaj = jsonArray.getJSONObject(i);
            JSONObject jsonMesaj = jsonDestinatarMesaj.getJSONObject("mesaj");
            Integer idMesaj = jsonMesaj.getInt("id");
            JSONObject jsonDestinatar = jsonDestinatarMesaj.getJSONObject("destinatar");
            Integer idDestinatar = jsonDestinatar.getInt("id");

            DestinatarMesaj destinatarMesaj = new DestinatarMesaj(idMesaj, idDestinatar);
            listaDestinatarMesaj.add(destinatarMesaj);
        }
         return listaDestinatarMesaj;
    }
}
