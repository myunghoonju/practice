package practice.mockito.bdd;

public interface PhoneBookRepository {

    void insert(String name, String phone);

    String getPhoneNumberByName(String name);

    boolean contains(String name);
}
