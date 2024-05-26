package TIK.entities;

import java.util.Date;

public class Ticket {
    private String ticketID;
    private String userID;
    private String source;
    private String destination;
    private Date travelDate;
    private Train train;

    public Ticket(String ticketID, String userID, String source, String destination, Date travelDate, Train train) {
        this.ticketID = ticketID;
        this.userID = userID;
        this.source = source;
        this.destination = destination;
        this.travelDate = travelDate;
        this.train = train;
    }

    public Ticket(){}

    public String getTicketInfo(){
        return String.format("Ticket ID: %s belongs to User %s from %s to %s on %s", ticketID, userID, source, destination, travelDate);
    }

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(Date travelDate) {
        this.travelDate = travelDate;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }
}
