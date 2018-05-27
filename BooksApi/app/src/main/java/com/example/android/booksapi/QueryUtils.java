package com.example.android.booksapi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rohaan on 17-Dec-17.
 */

public class QueryUtils {


    public static String formatList(JSONArray authorList) throws JSONException {

        String StringAuthorList = null;

        if (authorList.length() == 0) {
            return null;
        }

        for (int i = 0; i < authorList.length(); i++) {
            if (i == 0) {
                StringAuthorList = authorList.getString(0);
            } else {
                StringAuthorList += ", " + authorList.getString(i);
            }
        }

        return StringAuthorList;
    }

    public static List<Books> getBooks(String json) throws JSONException {

        List<Books> books = null;
        try {
            books = new ArrayList<>();

            JSONObject JsonResponse = new JSONObject(json);

            if (JsonResponse.getInt("totalItems") == 0) {
                return books;
            }

            JSONArray jsonArray = JsonResponse.getJSONArray("items");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                JSONObject volumeInfo = jsonObject.getJSONObject("volumeInfo");

                String title = volumeInfo.getString("title");

                JSONArray authorsArray = volumeInfo.getJSONArray("authors");
                String authors = formatList(authorsArray);

                Books books1 = new Books(authors, title);
                books.add(books1);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return books;
    }
}
