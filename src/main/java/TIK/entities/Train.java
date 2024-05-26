package TIK.entities;

import java.util.List;
import java.util.Map;

public class Train {
    private String trainID;
    private String trainNo;
    private Map<String, String> stationTimes;
    private List<String> stations;
    private List<List<Integer>> seats;

    public Train(){}

    public Train(String trainID, String trainNo, Map<String, String> stationTimes, List<String> stations, List<List<Integer>> seats) {
        this.trainID = trainID;
        this.trainNo = trainNo;
        this.stationTimes = stationTimes;
        this.stations = stations;
        this.seats = seats;
    }

    public String getTrainID() {
        return trainID;
    }

    public void setTrainID(String trainID) {
        this.trainID = trainID;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public Map<String, String > getStationTimes() {
        return stationTimes;
    }

    public void setStationTimes(Map<String, String> stationTimes) {
        this.stationTimes = stationTimes;
    }

    public List<String> getStations() {
        return stations;
    }

    public void setStations(List<String> stations) {
        this.stations = stations;
    }

    public List<List<Integer>> getSeats() {
        return seats;
    }

    public void setSeats(List<List<Integer>> seats) {
        this.seats = seats;
    }

    public String getTrainInfo(){
        return String.format("Train number: %s , Train ID: %s", trainNo, trainID);
    }
}
