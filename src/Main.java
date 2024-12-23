public class Main {
    public static void main(String[] args)
    {
        System.out.println("Программа заказа билетов");


        Schedule schedule = new Schedule();
        ViewBookingSystem view = new ViewBookingSystem();
        SystemBookingManagement managementSchedule = new SystemBookingManagement(schedule,view);
    }
}