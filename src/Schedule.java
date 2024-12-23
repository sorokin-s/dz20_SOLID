import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Schedule
{  // класс для хранения коллеции расписаний рейсов различных транспортных средств


    public ArrayList<Trip> getTrips() {
        return trips;
    }
    private ArrayList<Trip> trips = new ArrayList<>();
    public Schedule()
    {
        addTrip(new Trip(1,"Bus",new Date(2024-1900, Calendar.DECEMBER,26,11,10), new Route("Samara","Moscow"),40,40));
        addTrip(new Trip(2,"Bus",new Date(2024-1900, Calendar.DECEMBER,26,14,30), new Route("Moscow","Samara"),40,40));
        addTrip(new Trip(3,"Airplane",new Date(2024-1900, Calendar.DECEMBER,27,2,10), new Route("Samara","Moscow"),170,170));
        addTrip(new Trip(4,"Airplane",new Date(2024-1900, Calendar.DECEMBER,27,15,30), new Route("Moscow","Samara"),170,170));
        addTrip(new Trip(5,"Airplane",new Date(2024-1900, Calendar.DECEMBER,28,15,30), new Route("Moscow","Samara"),170,0));
    }

    public void addTrip(Trip trip)
    {
      trips.add(trip);
    }

}
