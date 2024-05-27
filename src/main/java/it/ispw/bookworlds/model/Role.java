package it.ispw.bookworlds.model;

public enum Role {
    CURATOR,
    READER;

    public static Role getRole(String role){
        switch(role){
            case "Reader" -> {return READER;}
            case "Curator" -> {return CURATOR;}
            default -> {return null;}
        }
    }
}
