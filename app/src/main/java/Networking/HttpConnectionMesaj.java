package Networking;

import android.os.AsyncTask;
import android.util.Log;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Commons.Angajat;
import Commons.Mesaj;
import Commons.Utilizator;
import Utils.Constant;

/**
 * Created by intern on 9/22/2017.
 */

public class HttpConnectionMesaj extends AsyncTask<String, Void, ArrayList<Mesaj>> implements Constant{

    URL url;
    HttpURLConnection connection;

    @Override
    protected ArrayList<Mesaj> doInBackground(String... params) {
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

    private ArrayList<Mesaj> parseHttpResponse(String JSONString) throws JSONException, ParseException {
        ArrayList<Mesaj> listaMesaje = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(JSONString);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonMesj = jsonArray.getJSONObject(i);
            Integer id = jsonMesj.getInt("id");
            Long data = jsonMesj.getLong("data");
            JSONObject jsonAngajat = jsonMesj.getJSONObject("angajat");
            Angajat expeditor = parseAngajat(jsonAngajat);
            String titlu = jsonMesj.getString("titlu");
            String continut = jsonMesj.getString("continut");
            String trimis = jsonMesj.getString("trimis");
            String citit = jsonMesj.getString("citit");

            Mesaj mesaj = new Mesaj(id, data, titlu, continut, trimis, citit, expeditor);
            listaMesaje.add(mesaj);

        }
        return listaMesaje;
    }

    private Angajat parseAngajat(JSONObject object) throws JSONException {
        Integer id = object.getInt("id");
        String email = object.getString("email");
        String cod = object.getString("cod");
        String telefon = object.getString("telefon");
        String nume = object.getString("nume");
        String memo = object.getString("memo");
        return new Angajat(id, cod, nume, memo, email, telefon);
    }

}
