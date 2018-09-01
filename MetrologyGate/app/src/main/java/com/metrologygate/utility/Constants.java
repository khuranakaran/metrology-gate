package com.metrologygate.utility;


public class Constants {
    public static final String MG_PREFS = "mg prefs";
    public static final String LOGGED_IN = "logged in";
    public static final String USER_NAME = "username";
    public static final String PHONE_NUMBER = "phone";
    public static final String PASSWORD = "pass";

    public static String getTypeName(int typeId){
        switch (typeId){
            case 10:
                return "Collision";
            case 11:
                return "Emergency pressed";
            case 14:
                return "Generic error";
            case 46:
                return "Work piece not found";

        }
        return "Unknown Type";
    }
}
