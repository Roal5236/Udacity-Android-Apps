package com.example.android.booksapi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String SEARCH= "bookSearchResult";
    BooksAdapter adapter;
    TextView noItems;
    EditText search;
    TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv2 = (TextView)findViewById(R.id.explain);


        search = (EditText) findViewById(R.id.search);
        Button b = (Button) findViewById(R.id.go);
        ListView listView = (ListView) findViewById(R.id.list);
        noItems = (TextView) findViewById(R.id.noItems);
        adapter = new BooksAdapter(this, -1);


        listView.setAdapter(adapter);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInternetConnectionLive()) {
                    BookAsyncTask task = new BookAsyncTask();
                    task.execute();
                } else {
                    Toast t = Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_SHORT);
                    tv2.setText("Check network Connectivity or Turn off Airplane mode");
                    t.show();
                }
            }
        });

        if (savedInstanceState != null) {
            Books[] books = (Books[]) savedInstanceState.getParcelableArray(SEARCH);
            adapter.addAll(books);
        }
    }


    private boolean isInternetConnectionLive() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }

        private void updateUi(List<Books> books){
            if (books.isEmpty()){
                // if no books found, show a message
                noItems.setText("Nothing Found, Try searching for something else...avoid numeric digits e.g Harry Potter");
                noItems.setVisibility(View.VISIBLE);
                Toast t = Toast.makeText(getApplicationContext(), "No Items", Toast.LENGTH_SHORT);
                t.show();
            }

            else {
                noItems.setVisibility(View.GONE);
            }
            adapter.clear();
            adapter.addAll(books);
        }

        private String makeUrl() {
            final String urlz = "https://www.googleapis.com/books/v1/volumes?q=search+";
            String SearchData = search.getText().toString().trim().replaceAll("\\s+","+");
            String url = urlz + SearchData;
            return url;
        }

        public class BookAsyncTask extends AsyncTask<URL, Void, List<Books>>{

            @Override
            protected List<Books> doInBackground(URL... urls) {
                URL url = null;
                try {
                    url = new URL(makeUrl());
                }
                catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                String JsonResponse="";
                List<Books> books = null;

                try {
                    JsonResponse = makeHttpRequest(url);
                    books = parseJson(JsonResponse);
                }

                catch (IOException e) {
                    e.printStackTrace();
                }

                catch (JSONException e) {
                    e.printStackTrace();
                }

                return books;
            }

            @Override
            protected void onPostExecute(List<Books> books) {
                if (books == null) {
                    return;
                }
                updateUi(books);
            }

            public String makeHttpRequest(URL url) throws IOException {
                String JsonResponse = "";

                if(url == null){
                    return JsonResponse;
                }

                HttpURLConnection urlConnection = null;
                InputStream inputStream = null;

                try {
                    urlConnection = (HttpURLConnection)url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setReadTimeout(10000);
                    urlConnection.setConnectTimeout(15000);
                    urlConnection.connect();

                    if (urlConnection.getResponseCode() == 200){
                        inputStream = urlConnection.getInputStream();
                        JsonResponse = readFromStream(inputStream);
                    }

                    else {
                        Log.e("mainActivity", "Error response code: " + urlConnection.getResponseCode());
                    }

                }

                catch (IOException e) {
                    e.printStackTrace();
                }

                finally {
                    if(urlConnection != null){
                        urlConnection.disconnect();
                    }
                    if(inputStream != null){
                        inputStream.close();
                    }
                }
                return JsonResponse;
            }

            private String readFromStream(InputStream inputStream) throws IOException {
                StringBuilder output = new StringBuilder();
                if(inputStream != null) {
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                    BufferedReader br = new BufferedReader(inputStreamReader);
                    String line = br.readLine();

                    while (line != null) {
                        output.append(line);
                        line = br.readLine();
                    }
                }
                return output.toString();
            }

            private List<Books> parseJson(String json) throws JSONException {

                if(json == null){
                    return null;
                }

                List<Books> books = QueryUtils.getBooks(json);
                return books;
            }
        }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Books[] books = new Books[adapter.getCount()];
        for (int i = 0; i < books.length; i++) {
            books[i] = adapter.getItem(i);
        }
        outState.putParcelableArray(SEARCH, (Parcelable[]) books);
    }
}

