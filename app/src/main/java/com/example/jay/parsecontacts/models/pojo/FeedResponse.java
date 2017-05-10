package com.example.jay.parsecontacts.models.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jay on 5/8/17.
 */

public class FeedResponse {

    @SerializedName("contacts")
    private List<Contacts> contacts;

    public FeedResponse(){
        contacts = new ArrayList<>();
    }

    public List<Contacts> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contacts> contacts) {
        this.contacts = contacts;
    }
}
