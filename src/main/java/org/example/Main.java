package org.example;

import TIK.entities.Train;
import TIK.entities.User;
import TIK.services.UserService;
import TIK.util.UserServiceUtil;

import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Opt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.println("Tikbook: Ticket Booking System");
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        UserService userService;
        try{
            userService = new UserService();
        } catch (Exception e){
            System.out.println("something went wrong");
            return;
        }

        while(option!=7){
            System.out.println();
            System.out.println("Choose option");
            System.out.println("1. Sign up");
            System.out.println("2. Login");
            System.out.println("3. Fetch Bookings");
            System.out.println("4. Search Trains");
            System.out.println("5. Book a Seat");
            System.out.println("6. Cancel my Booking");
            System.out.println("7. Exit the App");
            Train trainSelectedForBooking = new Train();
            option = scanner.nextInt();
            switch (option){
                case 1:
                    System.out.println("Enter name for sign up");
                    String name = scanner.next();
                    System.out.println("Enter password");
                    String pass = scanner.next();
                    User userSignup = new User(name, pass, UserServiceUtil.hashPassword(pass), new ArrayList<>(), UUID.randomUUID().toString());
                    userService.signup(userSignup);
                    break;

                case 2:
                    System.out.println("Enter name for login");
                    String namel = scanner.next();
                    System.out.println("Enter password for login");
                    String passl = scanner.next();
                    User loginUser = new User(namel, passl, UserServiceUtil.hashPassword(passl), new ArrayList<>(), UUID.randomUUID().toString());
                    try{
                        userService = new UserService(loginUser);
                    } catch (Exception e){
                        return;
                    }
                    break;

                case 3:
                    System.out.println("Fetching your bookings");
                    userService.fetchBooking();
                    break;
                case 4:
                    System.out.println("Enter your source station");
                    String source = scanner.next();
                    System.out.println("Enter your destination station");
                    String dest = scanner.next();
                    List<Train> trains = userService.getTrains(source, dest);
                    int index = 1;
                    for (Train t: trains){
                        System.out.println(index+" Train ID : "+t.getTrainID());
                        for (Map.Entry<String, String> entry: t.getStationTimes().entrySet()){
                            System.out.println("station "+entry.getKey()+" time: "+entry.getValue());
                        }
                    }
                    System.out.println("Select a train by typing 1,2,3...");
                    trainSelectedForBooking = trains.get(scanner.nextInt());
                    break;
                case 5:
                    System.out.println("Select a seat out of these seats");
                    List<List<Integer>> seats = userService.fetchSeats(trainSelectedForBooking);
                    for (List<Integer> row: seats){
                        for (Integer val: row){
                            System.out.print(val+" ");
                        }
                        System.out.println();
                    }
                    System.out.println("Select the seat by typing the row and column");
                    System.out.println("Enter row");
                    int row = scanner.nextInt();
                    System.out.println("Enter column");
                    int col = scanner.nextInt();
                    System.out.println("Booking your seat");
                    Boolean booked = userService.bookTrainSeat(trainSelectedForBooking, row, col);
                    if(booked.equals(Boolean.TRUE)){
                        System.out.println("Booked!");
                    }else{
                        System.out.println("Cannot book this seat");
                    }
                    break;

                default:
                    break;
            }
        }
    }
}