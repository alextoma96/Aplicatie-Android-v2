package Networking;

import android.util.Log;

import com.example.intern.myapplication.StatusActivity;

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
import Commons.CotaTVA;
import Commons.DestinatarMesaj;
import Commons.Mesaj;
import Commons.Moneda;

import static Utils.Constant.SIMPLE_DATE_FORMAT;

/**
 * Created by intern on 9/22/2017.
 */

public class HttpConnectionDestinatarMesaj  {

    URL url;
    HttpURLConnection connection;
    SimpleDateFormat dateFormat = SIMPLE_DATE_FORMAT;

    @Override
    protected ArrayList<DestinatarMesaj> doInBackground(String... params) {
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

    private ArrayList<DestinatarMesaj> parseHttpResponse(String JSONString) throws JSONException, ParseException {
        ArrayList<DestinatarMesaj> listaDestinatarMesaj = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(JSONString);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonDestinatarMesaj = jsonArray.getJSONObject(i);
            Integer id = jsonDestinatarMesaj.getInt("id");


            JSONObject jsonMesaj = jsonMesaj.getJSONObject("mesaj");
            Mesaj mesaj = parseMesaj(jsonMesaj);

            JSONObject jsonAngajat = jsonAngajat.getJSONObject("angajat");
            Angajat angajat = parseAngajat(jsonAngajat);

        }

        }
        // return listaDestinatarMesaj;
    }

    private Mesaj parseMesaj(JSONObject object) throws  JSONException {
        String titlu = object.getString("titlu");
        String continut = object.getString("continut");
        String trimis = object.getString("trimis");
        String citit = object.getString("citit");
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
