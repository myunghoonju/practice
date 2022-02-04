package practice.mockito.bdd;

public class PhoneBookService {

    private PhoneBookRepository repository;

    public PhoneBookService(PhoneBookRepository repository) {
        this.repository = repository;
    }

    public void register(String name, String phone) {
        if(!name.isEmpty() && !phone.isEmpty() && !repository.contains(name)) {
            repository.insert(name, phone);
        }
    }

    public String search(String name) {
        if(!name.isEmpty() && repository.contains(name)) {
            return repository.getPhoneNumberByName(name);
        }
        return null;
    }
}
