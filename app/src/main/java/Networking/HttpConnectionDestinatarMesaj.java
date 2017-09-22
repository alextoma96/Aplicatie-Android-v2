package Networking;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by intern on 9/22/2017.
 */

public class HttpConnectionDestinatarMesaj  {

    URL url;
    HttpURLConnection connection;
   // SimpleDateFormat dateFormat = SIMPLE_DATE_FORMAT;

   /* @Override
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
    */
   /* private ArrayList<DestinatarMesaj> parseHttpResponse(String JSONString) throws JSONException, ParseException {
        ArrayList<Mesaj> listaDestinatarMesaj = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(JSONString);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonDestinatarMesaj = jsonArray.getJSONObject(i);
            String nume = jsonDestinatarMesaj.getString("nume");
            String email = jsonDestinatarMesaj.getString("email");
            String telefon = jsonDestinatarMesaj.getString("telefon");
            Integer id = jsonDestinatarMesaj.getInt("id");

            Mesaj mesaj = null;
            if(!jsonDestinatarMesaj.isNull("mesj")) {
                JSONObject jsonMesaj = jsonArray.getJSONObject("mesaj");
                mesaj = parseMesaj(jsonMesaj);
            }
           // DestinatarMesaj destinatar = new DestinatarMesaj(nume, email, telefon, mesaj);
           // listaDestinatarMesaj.add(destinatar);
        }
        for(DestinatarMesaj u : listaDestinatarMesaj) {
            Log.i("destinar", u.getNume());
        }
        return listaDestinatarMesaj;
    }

    private Mesaj parseMesaj(JSONObject object) throws JSONException {
        String titlu = object.getString("titlu");
        String continut = object.getString("continut");
        String trimis = object.getString("trimis");
        String citit = object.getString("citit");
        return new Mesaj(titlu, continut, trimis, citit);
    }
    */
}
