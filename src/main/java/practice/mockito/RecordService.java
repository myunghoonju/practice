package practice.mockito;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RecordService {

    private DatabaseDAO databaseDAO;
    private NetworkDAO networkDAO;

    public RecordService(DatabaseDAO databaseDAO, NetworkDAO networkDAO) {
        this.databaseDAO = databaseDAO;
        this.networkDAO = networkDAO;
    }

    public boolean save() {
        System.out.println("RecordService");
        databaseDAO.save();
        networkDAO.save();

        return true;
    }
}
