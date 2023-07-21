import service.GetAdressService;

public class Main {
    public static void main(String[] args) {

        try {

            GetAdressService getAdressService = new GetAdressService();
            getAdressService.getAddressByDate();                        // решение 1 задачи
            getAdressService.getFullAddress();                          // решение 2 задачи

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
