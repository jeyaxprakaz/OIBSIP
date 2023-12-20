import java.util.*;
class Train{
    private int trainNumber;
    private String trainName;
    private int totalSeats;
    private int availableSeats;

    public Train(int trainNumber, String trainName, int totalSeats){
        this.trainNumber=trainNumber;
        this.trainName=trainName;
        this.totalSeats=totalSeats;
        this.availableSeats=totalSeats;
    }
    public int getTrainNumber(){
        return trainNumber;
    }
    public String getTrainName(){
        return trainName;
    }
    public int getAvailableSeats() {
        return availableSeats;
    }
    public boolean bookSeats(int numSeats){
        if (numSeats<=0 || numSeats>availableSeats){
            System.out.println("Invalid number of seats.Please choose a valid number");
            return false;
        }
        availableSeats-=numSeats;
        System.out.println("Booking successful. Train: " + trainName + ", Seats booked: " + numSeats);
        return true;
    }
}
class ReservationSystem{
    private ArrayList<Train> trains= new ArrayList<>();
    public void addTrain(Train train){
        trains.add(train);
    }
    public Train getTrainByNumber(int trainNumber){
        for (Train train : trains){
            if(train.getTrainNumber()==trainNumber){
                return train;
            }
        }
        return null;
    }
}
public class OnlineReservationSystem{
    public static void main(String[] args){
        ReservationSystem reservationSystem = new ReservationSystem();
        Scanner in= new Scanner(System.in);
        Train train1 = new Train(101, "Express", 50);
        Train train2 = new Train(102, "SuperFast", 40);
        reservationSystem.addTrain(train1);
        reservationSystem.addTrain(train2);

        while (true){
            System.out.println("\n1.Book Seats\n2. Display Train Information \n3. Exit");
            System.out.println("Enter your choice: ");
            int choice = in.nextInt();

            switch(choice){
                case 1:
                    System.out.println("Enter the train number:");
                    int trainNumber = in.nextInt();
                    Train selectedTrain = reservationSystem.getTrainByNumber(trainNumber);
                    if (selectedTrain != null){
                        System.out.println("Enter the number of seats to book:");
                        int  numSeats = in.nextInt();
                        selectedTrain.bookSeats(numSeats);
                    }
                    else{
                        System.out.println("Train Not Found . Please enter a valid Train number");
                    }
                    break;
                case 2:
                    System.out.println("Enter the train Number:");
                    int  infoTrainNumber = in.nextInt();
                    Train infoTrain = reservationSystem.getTrainByNumber(infoTrainNumber);

                    if(infoTrain!=null){
                        System.out.println("Train Information:");
                        System.out.println("Train Number:" + infoTrain.getTrainNumber());
                        System.out.println("Train Name: "+infoTrain.getTrainName());
                        System.out.println("Available Seats: " + infoTrain.getAvailableSeats());
                    }
                    else{
                        System.out.println("Train not Found. Please enter a valid train Number");
                    }
                    break;
                case 3:
                    System.out.println("Existing the train reservation System. GoodBye ! ");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid Option");
            }
        }

    }
}
