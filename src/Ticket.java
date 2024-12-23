import java.util.Date;

public class Ticket
{  // Билет содержит всю необходимую информацию о поездке
    private String idTicket; // номер билета
    private Trip tripTicket;  // рейс по данному билету
    private int numSeat;  // номер места в салоне
    private String namePassenger; // имя пассажира при бронировании

    public String getIdTicket() {
        return idTicket;
    }


    public Trip getTripTicket() {
        return tripTicket;
    }


    public int getNumSeat() {
        return numSeat;
    }

    public String getNamePassenger() {
        return namePassenger;
    }

    public void setNamePassenger(String namePassenger) {
        this.namePassenger = namePassenger;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "idTicket='" + idTicket + '\'' +
                ", tripTicket=" + tripTicket +
                ", numSeat=" + numSeat +
                ", namePassenger='" + namePassenger + '\'' +
                '}';
    }

    public Ticket(String idTicket, Trip tripTicket, int numSeat, String namePassenger) {
        this.idTicket = idTicket;
        this.tripTicket = tripTicket;
        this.numSeat = numSeat;
        this.namePassenger = namePassenger;
    }
}
