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

/**
 * RestClient class represents an asynchronous task for making HTTP POST requests to a server.
 *
 * <p>This class extends AsyncTask and is used to communicate with a server and handle its response.</p>
 *
 * <p>Author: Alejandro Solis</p>
 */
public class RestClient extends AsyncTask<Void, Void, String> {

    private static final String SERVER_URL = "http://your-ip-address:9999/"; // Replace with the server's IP address

    /**
     * Performs the background task of making a POST request to the server.
     *
     * @param params No parameters are passed to this method.
     * @return A string representing the server's response.
     */
    @Override
    protected String doInBackground(Void... params) {
        try {
            // Connect to the server
            URL url = new URL(SERVER_URL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setDoOutput(true);

            // Build the JSON for the request
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("tipoConsulta", "login"); // Replace with your data

            // Send the JSON to the server
            DataOutputStream outputStream = new DataOutputStream(urlConnection.getOutputStream());
            outputStream.writeBytes(jsonParam.toString());
            outputStream.flush();
            outputStream.close();

            // Read the server's response
            InputStream in = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            // Close the connection
            in.close();
            urlConnection.disconnect();

            // Return the server's response
            return result.toString();

        } catch (IOException e) {
            Log.e("RestClient", "Error connecting to the server", e);
            return null;
        } catch (Exception e) {
            Log.e("RestClient", "Error in HTTP request", e);
            return null;
        }
    }

    /**
     * Handles the server's response after the background task is completed.
     *
     * @param result The string representing the server's response.
     */
    @Override
    protected void onPostExecute(String result) {
        // Handle the server's response here
        if (result != null) {
            Log.d("RestClient", "Server response: " + result);
            // Process the JSON response here
        } else {
            Log.e("RestClient", "Error in HTTP request");
        }
    }
}