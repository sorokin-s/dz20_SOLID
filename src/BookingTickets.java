import java.util.ArrayList;

public class BookingTickets
{// класс содержит коллекцию всех возможных билетов

  private ArrayList<Ticket> tickets = new ArrayList<>();

  public ArrayList<Ticket> getTickets() {
    return tickets;
  }

  void fillTableTickets(Ticket ticket){tickets.add(ticket);}

}
