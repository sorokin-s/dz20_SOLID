import java.util.ArrayList;
import java.util.List;

public class ViewBookingSystem
{  // класс для представления  информации в интерфейсе пользователя
    public ViewBookingSystem()
    {   displayMenu();
    }
    public void displayMenu() // описание контекстного меню
    {
        System.out.println("Меню пользователя");
        System.out.println("Выбирите действие:");
        System.out.println("1 - Расписание рейсов автобусов");
        System.out.println("2 - Расписание авиа-рейсов");
        System.out.println("3 - Забронировать билет указав место и имя пассажира");
        System.out.println("4 - Вывести список всех забронированных билетов");
        System.out.println("5 - Отменить бронирование по имени пассажира");
        System.out.println("6 - Поиск бронирования по имени пассажира");
        System.out.println("7 - Общее количество бранированных билетов");
        System.out.println("9 - Закончить работу и выйти");
    }
    public void displayAvailableTrips(String type,List<Trip> trips) //список рейсов
    {   System.out.println(type);
        trips.forEach(System.out::println);
    }
    public void displayBookedTickets(List<Ticket> tickets) // список забронированных
    {
       displayMessage("Список всех забронированных билетов:");
       if(tickets.isEmpty())System.out.println("Забронированных билетов нет");
       else tickets.forEach(System.out::println);
       displayMenu();
    }
    public void displayBookedTicketsForName(List<Ticket> tickets) //  выводим бронирование по имени
    {
        if(tickets.isEmpty())System.out.println("Забронированных билетов на данное имя  нет");
        else tickets.forEach(System.out::println);
        displayMenu();
    }
    public void displayAvailableTickets(ArrayList<Ticket> tickets) //список всех свободных билетов
    {
        tickets.forEach(System.out::println);
    }
    public void displayCountyBookedTickets(int numTotalBookedTicket) //список всех забронированных билетов
    {   System.out.println("Количество забронированных билетов на все рейсы");
        System.out.println(numTotalBookedTicket);
    }
    public void displayMessage(String str){System.out.println(str);} // произвольное сообщение


}
