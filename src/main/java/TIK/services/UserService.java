package TIK.services;

import TIK.entities.Train;
import TIK.entities.User;
import TIK.util.UserServiceUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {

    private User user;
    private List<User> user_list;

    private static final String USER_PATH = "../DB/users.json";
//    private static final String USER_PATH = "/Users/jaynehete/Documents/Tikbook/src/main/java/TIK/DB/users.json";
    private ObjectMapper objectMapper = new ObjectMapper();

    public UserService(User user1) throws IOException {
        this.user = user1;
        loadUsers();
    }

    public UserService() throws IOException {
        loadUsers();
    }

    public void loadUsers() throws IOException {
        File users = new File(USER_PATH);
        user_list = objectMapper.readValue(users, new TypeReference<List<User>>() {});
    }

    public Boolean login() {
        Optional<User> userFound = user_list.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashPassword());
        }).findFirst();
        return userFound.isPresent();
    }

    public void saveUserListToFile() throws IOException {
        File usersFile = new File(USER_PATH);
        // serialization
        objectMapper.writeValue(usersFile, user_list);
    }

    public Boolean signup(User user1){
        try {
            user_list.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }

    public void fetchBooking() {
        user.printTickets();
    }

    public Boolean cancelBooking(String ticketID){
        try{
            user.getBookedTickets().removeIf(ticket -> ticket.getTicketID().equals(ticketID));
            saveUserListToFile();
            return Boolean.TRUE;
        } catch (IOException e){
            return Boolean.FALSE;
        }
    }

    public List<Train> getTrains(String source, String destination){
        try{
            TrainService trainService = new TrainService();
            return trainService.searchTrain(source, destination);
        }catch(IOException ex){
            return new ArrayList<>();
        }
    }

    public List<List<Integer>> fetchSeats(Train train){
        return train.getSeats();
    }

    public Boolean bookTrainSeat(Train train, int row, int seat) {
        try{
            TrainService trainService = new TrainService();
            List<List<Integer>> seats = train.getSeats();
            if (row >= 0 && row < seats.size() && seat >= 0 && seat < seats.get(row).size()) {
                if (seats.get(row).get(seat) == 0) {
                    seats.get(row).set(seat, 1);
                    train.setSeats(seats);
                    trainService.addTrain(train);
                    return true; // Booking successful
                } else {
                    return false; // Seat is already booked
                }
            } else {
                return false; // Invalid row or seat index
            }
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }

}
