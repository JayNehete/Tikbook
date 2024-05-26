package TIK.services;

import TIK.entities.Train;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TrainService {

    private ObjectMapper objectMapper = new ObjectMapper();
    private List<Train> train_list;
    private static final String TRAIN_PATH = "../DB/trains.json";

    public TrainService() throws IOException{
        File trains = new File(TRAIN_PATH);
        train_list = objectMapper.readValue(trains, new TypeReference<List<Train>>() {});
    }

    public List<Train> searchTrain(String source, String destination){
        return train_list.stream().filter(train -> validTrain(train, source, destination)).collect(Collectors.toList());
    }

    private boolean validTrain(Train train, String source, String destination){
        List<String> stations = train.getStations();
        int sourceIdx = stations.indexOf(source.toLowerCase());
        int destinationIdx = stations.indexOf(destination.toLowerCase());
        return sourceIdx!=-1 && destinationIdx !=-1 && sourceIdx<destinationIdx;

    }

    private void saveTrainListToFile(){
        try {
            objectMapper.writeValue(new File(TRAIN_PATH), train_list);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addTrain(Train mytrain){
        Optional<Train> doesTrainExist = train_list.stream().filter(train -> train.getTrainID().equalsIgnoreCase(mytrain.getTrainID())).findFirst();
        if(doesTrainExist.isPresent()){
            updateTrain(mytrain);
        } else {
            train_list.add(mytrain);
            saveTrainListToFile();
        }
    }

    //Find the
    public void updateTrain(Train updatetrain){
        OptionalInt idx = IntStream.range(0, train_list.size()).filter(pred -> train_list.get(pred).getTrainID().equalsIgnoreCase(updatetrain.getTrainID())).findFirst();
        if(idx.isPresent()) {
            train_list.set(idx.getAsInt(), updatetrain);
        } else {
            addTrain(updatetrain);
        }
    }
}
