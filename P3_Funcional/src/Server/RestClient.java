import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestClient extends AsyncTask<Void, Void, String> {

    private static final String SERVER_URL = "http://tu-direccion-ip:9999/"; // Reemplaza con la dirección IP del servidor

    @Override
    protected String doInBackground(Void... params) {
        try {
            // Conecta al servidor
            URL url = new URL(SERVER_URL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setDoOutput(true);

            // Construye el JSON para la solicitud
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("tipoConsulta", "login"); // Reemplaza con tus datos

            // Envía el JSON al servidor
            DataOutputStream outputStream = new DataOutputStream(urlConnection.getOutputStream());
            outputStream.writeBytes(jsonParam.toString());
            outputStream.flush();
            outputStream.close();

            // Lee la respuesta del servidor
            InputStream in = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            // Cierra la conexión
            in.close();
            urlConnection.disconnect();

            // Devuelve la respuesta del servidor
            return result.toString();

        } catch (IOException e) {
            Log.e("RestClient", "Error al conectarse al servidor", e);
            return null;
        } catch (Exception e) {
            Log.e("RestClient", "Error en la solicitud HTTP", e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        // Maneja la respuesta del servidor aquí
        if (result != null) {
            Log.d("RestClient", "Respuesta del servidor: " + result);
            // Puedes procesar la respuesta JSON aquí
        } else {
            Log.e("RestClient", "Error en la solicitud HTTP");
        }
    }
}
