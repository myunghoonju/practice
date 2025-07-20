package practice.algorithm.prog;

public class Q72410 {

  public String solution(String new_id) {
    String id = new_id;
    id = id.toLowerCase();
    id = id.replaceAll("[^a-zA-Z0-9\\._-]","");
    id = id.replaceAll("\\.+", ".");
    id = id.replaceAll("^\\.+|\\.+$","");

    if (id.isBlank()) {
      id = "a";
    }

    if (id.length() >= 16) {
      id = id.substring(0, 15);
      id = id.replaceAll("\\.+$", "");
    }

    while (id.length() < 3) {
      id += id.charAt(id.length() -1);
    }

    return id;
  }
}
