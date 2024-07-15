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

    public static Role getRoleIta(String role){
        switch(role){
            case "Lettore" -> {return READER;}
            case "Curatore" -> {return CURATOR;}
            default -> {return null;}
        }
    }

    public static String roleToString(Role role){
        switch(role){
            case READER -> {return "Reader";}
            case CURATOR -> {return "Curator";}
            default -> {return null;}
        }
    }
}
