package practice.others.archive;

import java.util.ArrayList;
import java.util.List;

public class UpdateClient {

  static final List<String> NAMES = new ArrayList<>();

  public static void main(String[] args) {
    new UpdateClient().info();
  }

  public void info() {
    Receiver receiver = new Receiver();
    receiver.setFilePath("src/main/resources/archive");
    receiver.setCreateDateDirectory(ADSUtils.YYYYMMDD);

    try {
      ReceiveDatas receiveDatas = receiver.receiveAddr("U01TX0FVVEgyMDI1MDIyMDIzNDAwNDExNTQ5MDU=", "D", "100001", "N", "20250222", "20250222");
      if (receiveDatas.getReceiveDatas() != null) {
        if (receiveDatas.getResult() == -1) {
          System.out.println("error");
        }

        List<ReceiveData> receiveDatas1 = receiveDatas.getReceiveDatas();
        receiveDatas1.forEach(d -> {
          try {
            ArchiveFile.unzip(NAMES, d.getFileDate(), d.getFileName());
            NAMES.forEach(name -> ArchiveFile.read(d, name));
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
        });
      }

      ArchiveFile.delete("20250222");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }
}
