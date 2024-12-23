import java.util.Date;

public class Trip
{   //класс рейс-поездка  конкретного тарнспортного средства (условно)
    private int id; //  номер рейса

    public int getId() {
        return id;
    }


    private String nameTransport; // тип транспорта
    private Date date;            // дата отправления
    private Route route;          // маршрут
    private int numOfSeats;       // количество мест в тр средстве
    private int numAvailableSeats; // количество свободных мест

    public Route getRoute() {
        return route;
    }

    public String getNameTransport() {
        return nameTransport;
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }


    public int getNumAvailableSeats() {
        return numAvailableSeats;
    }

    public void setNumAvailableSeats(int numAvailableSeats) {
        this.numAvailableSeats = numAvailableSeats;
    }


    @Override
    public String toString() {
        return "Рейс{" +
                "id=" + id +
                ", nameTransport='" + nameTransport + '\'' +
                ", date=" + date +
                ", route=" + route +
                ", numOfSeats=" + numOfSeats +
                ", numAvailableSeats=" + numAvailableSeats +
                '}';
    }


    public Trip(int id, String nameTransport, Date date, Route route, int numOfSeats, int numAvailableSeats) {
        this.id = id;
        this.nameTransport = nameTransport;
        this.date = date;
        this.route = route;
        this.numOfSeats = numOfSeats;
        this.numAvailableSeats = numAvailableSeats;
    }
}
