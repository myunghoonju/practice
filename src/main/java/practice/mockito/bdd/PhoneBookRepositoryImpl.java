package practice.mockito.bdd;

public class PhoneBookRepositoryImpl implements PhoneBookRepository {

    @Override
    public void insert(String name, String phone) {
        System.out.println("PhoneBookRepositoryImpl insert()");
    }

    @Override
    public String getPhoneNumberByName(String name) {
        System.out.println("PhoneBookRepositoryImpl getPhoneNumberByName()");
        return name;
    }

    @Override
    public boolean contains(String name) {
        System.out.println("PhoneBookRepositoryImpl contains()");
        return false;
    }
}
