package com.android.server;


import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private String name;

    public Book() {

    }

    public Book(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.name);

    }

    public void readFromParcel(Parcel dest) {
        this.name = dest.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    protected Book(Parcel in) {
        this.name = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}