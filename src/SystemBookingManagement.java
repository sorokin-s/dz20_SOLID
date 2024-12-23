import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class SystemBookingManagement
{   //класс управляющий службой заказа билетов
    // возможно не все обработаны ошибки пользователя, не делал на этом упор
    private Schedule schedule;

    private ViewBookingSystem view;
    Scanner sc = new Scanner(System.in);
    private BookingTickets bookingTickets = new BookingTickets();
    public SystemBookingManagement(Schedule schedule, ViewBookingSystem view) {
        this.schedule = schedule;
        this.view  = view;
        addSeatsToTable(); // заполняем список  мест(билетов) на все рейсы
        bookingSystemRun(); // метод взаимодействия с пользователями

    }


    public void bookingSystemRun()
    {
        while(true)
        {
            switch (sc.nextLine())
            {

                case "1":{view.displayAvailableTrips("Расписание автобусных рейсов",schedule.getTrips().stream().filter(t->t.getNameTransport().equals("Bus")).toList()); }
                break;
                case "2":{view.displayAvailableTrips("Расписание авиа-рейсов",schedule.getTrips().stream().filter(t->t.getNameTransport().equals("Airplane")).toList()); }
                break;
                case "3":{ticketBooking();}
                break;
                case "4":{view.displayBookedTickets(findBookedTicked());}
                break;
                case "5":{cancelTicketBooking(); }
                break;
                case "6":{view.displayBookedTicketsForName(findTicketBookingForName()); }
                break;
                case "7":{view.displayCountyBookedTickets(findBookedTicked().size());}
                break;
                case "9":return;
                default:view.displayMessage("некорректное значение, повторите попытку");
            }
        }
    }


    void addSeatsToTable()
    {  int idTicket=0;
        for(Trip trip:schedule.getTrips())
        {
               for(int i = 1; i<=trip.getNumOfSeats();i++)
               {
                   bookingTickets.fillTableTickets(new Ticket(idTicket+trip.getNameTransport().substring (0,3),trip,i,null));
                   idTicket++;
               }
        }

    }

    public List<Ticket> findBookedTicked()
    {
        return bookingTickets.getTickets().parallelStream()
                .filter(t->t.getNamePassenger()!=null)
                .sequential()
                .toList();
    }

    public void ticketBooking()
    {
        view.displayMessage("Для бронирования билета выберите рейс,\n" +
                "(укажите id)");
        var id = sc.hasNextInt()?sc.nextInt():null;
        if(id==null){view.displayMessage("некорректное значение, повторите операцию");view.displayMenu();return;}

        if (bookingTickets.getTickets().stream()
                .map(t -> t.getTripTicket().getId())
                .filter(_id -> _id.equals(id))
                .findAny().isEmpty()) {view.displayMessage("Вы указали неправильный id, повторите операцию");
                view.displayMenu();
                return;
        }
        else
        {
            for(Trip trip:schedule.getTrips())
            {
                if(trip.getId()==id&&trip.getNumAvailableSeats()==0){view.displayMessage("На выбранном рейсе нет свободных мест\n" +
                        "выберите другой рейс"); return;  }
            }
           var seats = bookingTickets.getTickets().parallelStream()
                        .filter(t -> t.getNamePassenger() == null)
                        .filter(t->t.getTripTicket().getId()==id)
                        .map(Ticket::getNumSeat)
                        .toList();
            view.displayMessage("Свободные места "+ seats+" выберите  место" );
            var seat = sc.hasNextInt()?sc.nextInt():null;
            if(seat==null||!seats.contains(seat)){view.displayMessage("некорректное значение, повторите операцию");view.displayMenu();return;}
           Optional<String> idTicket = bookingTickets.getTickets().parallelStream()
                    .filter(t->t.getTripTicket().getId()==id)
                    .filter(t->t.getNumSeat()==seat)
                    .map(Ticket::getIdTicket)
                    .findAny();
           idTicket.ifPresent(_idTicket->
           {   view.displayMessage("Введите имя пассажира");
               sc = new Scanner(System.in);
               String name = sc.nextLine();
               if(name==null){view.displayMessage("некорректное значение, повторите операцию");return;}
               name = name.toLowerCase();
               for(Ticket t:bookingTickets.getTickets())
               {
                   if(t.getIdTicket().equals((_idTicket)))
                   {
                       t.setNamePassenger(name);
                       view.displayMessage("Билет № "+_idTicket+" забронирован на имя "+ name);
                       updateTableTrips(t.getTripTicket().getId());
                       view.displayMenu();
                   }
               }

           });

        }

    }
    public void cancelTicketBooking()
    {
        view.displayMessage("Введите имя пассажира для отмены брони");
        sc = new Scanner(System.in);
        String name = sc.nextLine();
        if(name==null){view.displayMessage("некорректное значение, повторите операцию");return;}
        name =name.toLowerCase();
        try
        {
            for(Ticket t:bookingTickets.getTickets())
            {
                if(t.getNamePassenger()!=null&&t.getNamePassenger().equals(name))
                {
                  t.setNamePassenger(null);
                  view.displayMessage("Произведена отмена бронирования билета № "+t.getIdTicket()+"  на имя "+ name);
                  updateTableTrips(t.getTripTicket().getId());
                  view.displayMenu();
                }
            }
        }
        catch (NullPointerException e){view.displayMessage(e.getMessage());}

    }
    public List<Ticket> findTicketBookingForName()
    {    view.displayMessage("Введите имя для поиска забронированного билет");
        sc = new Scanner(System.in);
        String name = sc.nextLine();
        if(name==null){view.displayMessage("некорректное значение, повторите операцию");return null;}
        name = name.toLowerCase();
        String finalName = name;
        return   bookingTickets.getTickets().parallelStream()
                .filter(t->t.getNamePassenger()!=null)
                .filter(t->t.getNamePassenger().equals(finalName))
                .toList();

    }
    public void updateTableTrips(int idTrips)
    {   int numBookedTickets=0;
        for(Ticket ticket:bookingTickets.getTickets()){
            if(ticket.getTripTicket().getId()==idTrips&&ticket.getNamePassenger()!=null)
                numBookedTickets++;
        }

        for(Trip trip:schedule.getTrips())
        {   if(trip.getId()==idTrips)
            trip.setNumAvailableSeats(trip.getNumOfSeats()-numBookedTickets);
        }
    }
}
