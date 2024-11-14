import java.util.ArrayList;
import java.util.List;

public class Task4 {
    private List<RouteInfo> routes;

    public Task4() {
        routes = new ArrayList<>();
    }

    // Внутренний класс для хранения информации о маршруте
    public class RouteInfo {
        private String routeNumber;
        private String startTime;
        private String endTime;
        private List<String> stops;
        private double fare;

        public RouteInfo(String routeNumber, String startTime, String endTime, double fare) {
            this.routeNumber = routeNumber;
            this.startTime = startTime;
            this.endTime = endTime;
            this.stops = new ArrayList<>();
            this.fare = fare;
        }

        public void addStop(String stop) {
            stops.add(stop);
        }

        @Override
        public String toString() {
            return "Маршрут " + routeNumber + ": " +
                    "Время работы " + startTime + " - " + endTime + ", " +
                    "Остановки: " + String.join(" -> ", stops) + ", " +
                    "Стоимость проезда: " + fare + " руб.";
        }
    }

    public void addRoute(String routeNumber, String startTime, String endTime, double fare) {
        routes.add(new RouteInfo(routeNumber, startTime, endTime, fare));
    }

    public void addStopToRoute(String routeNumber, String stop) {
        for (RouteInfo route : routes) {
            if (route.routeNumber.equals(routeNumber)) {
                route.addStop(stop);
                return;
            }
        }
        System.out.println("Маршрут " + routeNumber + " не найден.");
    }

    public void displayAllRoutes() {
        for (RouteInfo route : routes) {
            System.out.println(route);
        }
    }

    public static void main(String[] args) {
        Task4 infoSystem = new Task4();

        infoSystem.addRoute("1", "06:00", "23:00", 30.0);
        infoSystem.addStopToRoute("1", "Вокзал");
        infoSystem.addStopToRoute("1", "Центр");
        infoSystem.addStopToRoute("1", "Парк");
        infoSystem.addStopToRoute("1", "Университет");

        infoSystem.addRoute("2", "05:30", "22:30", 35.0);
        infoSystem.addStopToRoute("2", "Аэропорт");
        infoSystem.addStopToRoute("2", "Центр");
        infoSystem.addStopToRoute("2", "Больница");
        infoSystem.addStopToRoute("2", "Стадион");

        System.out.println("Информация о маршрутах общественного транспорта:");
        infoSystem.displayAllRoutes();
    }
}
