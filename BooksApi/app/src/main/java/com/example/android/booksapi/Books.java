package com.example.android.booksapi;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rohaan on 17-Dec-17.
 */

public class Books implements Parcelable {

    String author;
    String title;

    public Books(String author, String title) {
        this.author = author;
        this.title = title;
    }

    protected Books(Parcel in) {
        author = in.readString();
        title = in.readString();
    }

    public static final Creator<Books> CREATOR = new Creator<Books>() {
        @Override
        public Books createFromParcel(Parcel in) {
            return new Books(in);
        }

        @Override
        public Books[] newArray(int size) {
            return new Books[size];
        }
    };

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(author);
        parcel.writeString(title);
    }
}

