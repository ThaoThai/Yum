package thaothai.example.com.recipefinder;

import android.preference.EditTextPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.os.AsyncTask;
import java.util.ArrayList;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.net.URLEncoder;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

public class RecipeMainActivity extends AppCompatActivity {
    String excludes, prep_time, includes;
    ProgressBar progressBar;
    TextView responseView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_main);
        EditText exclude = (EditText) findViewById(R.id.exclude);
        EditText preptime = (EditText) findViewById(R.id.include);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        responseView = (TextView) findViewById(R.id.responseView);
        final Button search = (Button) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSelect();
                new RetrieveFeedTask().execute();
            }
        });

    }

    protected void getSelect() {
        EditText exclude = (EditText) findViewById(R.id.exclude);
        EditText include = (EditText) findViewById(R.id.include);
        excludes = replace(exclude.getText().toString());
        includes = replace(include.getText().toString());
        Toast.makeText(getApplicationContext(), includes, Toast.LENGTH_LONG).show();
    }

    public String replace(String input) {
        String result;
        result = input.replace(",","%2C");
        result = result.replace(" ","+");
        return result;
    }

    protected class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        private Exception exception;

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            responseView.setText("");
        }

        @Override
        protected String doInBackground(Void... urls) {
            String url = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/searchComplex?";
            String charset = "UTF-8";
            String ex_ingredient = excludes;
            String in_ingredients = includes;
            String query = "number=100" + "&includeIngredients=" + in_ingredients + "&readyInMinutes=30" + "&instructionsRequired=true"
                    + "&excludeIngredients=" + ex_ingredient + "&ranking=1";

            try {
                HttpURLConnection urlConnection = (HttpURLConnection) new URL(url + query).openConnection();
                urlConnection.setDoInput(true);
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                urlConnection.setRequestProperty("X-Mashape-Key", "bO9COj191VmshVzI4cNTPoxIxXoMp1ZfMQtjsnsM1bEzjBrumx");
                urlConnection.setRequestProperty("Accept", "application/json");
                urlConnection.setRequestProperty("Accept-Charset", charset);
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                } finally {
                    urlConnection.disconnect();
                }

            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(String response) {
            ArrayList<String> ingredient_Title = new ArrayList<String>();
            String responses = "";
            if (response == null) {
                response = "THERE WAS AN ERROR";
                responses = "NO RESULT FOUND";
            }
            else {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject ingredient = jsonArray.getJSONObject(i);
                        ingredient_Title.add(ingredient.getString("title"));
                        responses += ingredient.getString("title") + "\n";
                    }
                } catch (JSONException e) {
                    Log.d("Test", "Couldn't successfully parse the JSON response!");
                }
            }
            progressBar.setVisibility(View.GONE);
            Log.i("INFO", response);
            responseView.setText(responses);
        }
    }
}


