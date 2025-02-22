package practice.others.archive;

import java.util.List;

public class UpdateClient {

  public static void main(String[] args) {
    new UpdateClient().info();
  }

  public void info() {
    Receiver receiver = new Receiver();
    receiver.setFilePath("/Users/ju/Downloads");
    receiver.setCreateDateDirectory(ADSUtils.YYMMDD);

    try {
      ReceiveDatas receiveDatas = receiver.receiveAddr("U01TX0FVVEgyMDI1MDIyMDIzNDAwNDExNTQ5MDU=", "D", "100001", "N", "20250220", "20250220");
      if (receiveDatas.getResult() != 0) {
        if (receiveDatas.getResult() == -1) {
          System.out.println("error");
        }

        List<ReceiveData> receiveDatas1 = receiveDatas.getReceiveDatas();
        receiveDatas1.stream().forEach(d -> System.out.println("- > " + d));
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }
}
