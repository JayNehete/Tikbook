package TIK.entities;

import java.util.List;

public class User {
    private String name;
    private String password;
    private String hashPassword;
    private List<Ticket> bookedTickets;
    private String userID;

    public User(String name, String password, String hashPassword, List<Ticket> bookedTickets, String userID){
        this.name = name;
        this.password = password;
        this.hashPassword = hashPassword;
        this.bookedTickets= bookedTickets;
        this.userID = userID;
    }

    public User(){}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public List<Ticket> getBookedTickets() {
        return bookedTickets;
    }

    public void setBookedTickets(List<Ticket> bookedTickets) {
        this.bookedTickets = bookedTickets;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void printTickets() {
        for(int i=0; i<bookedTickets.size(); i++){
            System.out.println(bookedTickets.get(i).getTicketInfo());
        }
    }
}
