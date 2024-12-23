public class Route {

    // класс описание маршрута откуда-куда
    private String source;
    private  String destination;

    @Override
    public String toString() {
        return "Маршрут{" +
                "город отправления='" + source + '\'' +
                ", город прибытия='" + destination + '\'' +
                '}';
    }

    public Route(String source, String destination) {
       // this.id = id;
        this.source = source;
        this.destination = destination;
    }
}
