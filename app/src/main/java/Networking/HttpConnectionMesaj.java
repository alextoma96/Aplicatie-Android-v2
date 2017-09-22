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

public class HttpConnectionMesaj extends AsyncTask<String, void, ArrayList<Mesaj>> implements Constant{

    URL url;
    HttpURLConnection connection;
    SimpleDateFormat dateFormat = SIMPLE_DATE_FORMAT;

    @Override
    protected ArrayList<Mesaj> doInBackground(String... params) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            url = new URL(params[0]);
            Log.i("url", url.toString());
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
            JSONObject jsonUtilizator = jsonArray.getJSONObject(i);
            String nume = jsonUtilizator.getString("nume");
            String username = jsonUtilizator.getString("username");
            String parola = jsonUtilizator.getString("parola");

            Angajat angajat = null;
            if(!jsonUtilizator.isNull("angajat")) {
                JSONObject jsonAngajat = jsonUtilizator.getJSONObject("angajat");
                angajat = parseAngajat(jsonAngajat);
            }
            Utilizator utilizator = new Utilizator(nume, username, parola, angajat);
            listaUtilizatori.add(utilizator);
        }
        for(Utilizator u : listaUtilizatori) {
            Log.i("utilizator", u.getNume());
        }
        return listaUtilizatori;
    }

    private Angajat parseAngajat(JSONObject object) throws JSONException {
        String email = object.getString("email");
        String cod = object.getString("cod");
        String telefon = object.getString("telefon");
        String nume = object.getString("nume");
        String memo = object.getString("memo");
        return new Angajat(cod, nume, memo, email, telefon);
    }
}
