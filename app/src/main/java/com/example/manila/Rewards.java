package com.example.manila;

public class Rewards {
    private String preference;
    private String Name;

    public Rewards(String preference, String name) {
        this.preference = preference;
        Name = name;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
