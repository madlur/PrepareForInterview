package builder;

public class MainApp {
    public static void main(String[] args) {
        Person person = new PersonBuilder()
                .address("Address")
                .age(0)
                .country("Country")
                .gender("Gender")
                .firstName("FirstName")
                .middleName("MiddleName")
                .lastName("LastName")
                .phone("Phone")
                .build();
        System.out.println(person);
    }
}