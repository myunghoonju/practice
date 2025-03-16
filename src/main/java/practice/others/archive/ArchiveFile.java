package practice.others.archive;

import com.tc.util.io.FileUtils;
import org.springframework.util.FileSystemUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

// EUC-KR files
public class ArchiveFile {

  private static final String DIR = "src/main/resources/archive";

  public static void main(String[] args) {
    try {
      LocalDate now = LocalDate.now();
      System.out.println(now.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
      System.out.println();
      //unzip();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public static void unzip(List<String> names, String fileDate, String zipName) throws Exception {
    byte[] buffer = new byte[1024];
    File destDir = new File(DIR + "/" + fileDate);
    ZipInputStream zis = new ZipInputStream(new FileInputStream(DIR + "/" + fileDate + "/" + zipName));
    ZipEntry entry = zis.getNextEntry();
    while (entry != null) {
      names.add(entry.getName());
      File newFile = file(destDir, entry);
      if (entry.isDirectory()) {
        validDirectory(newFile);
        return;
      }

      File parent = newFile.getParentFile();
      validDirectory(parent);

      FileOutputStream stream = new FileOutputStream(newFile);
      int len;
      while ((len = zis.read(buffer)) > 0) {
        stream.write(buffer, 0, len);
      }
      stream.close();

      entry = zis.getNextEntry();
    }

    zis.closeEntry();
    zis.close();
  }

  static File file(File whereTo, ZipEntry entry) throws Exception {
    File target = new File(whereTo, entry.getName());
    String whereToPath = whereTo.getCanonicalPath();
    String filePath = target.getCanonicalPath();
    if (!filePath.startsWith(whereToPath + File.separator)) {
      throw new IOException("zip slip detected: " + entry.getName());
    }

    return target;
  }

  static void validDirectory(File file) {
    if (!file.isDirectory() && !file.mkdirs()) {
      throw new RuntimeException("unable to create directory: " + file);
    }
  }

  public static void read(ReceiveData d, String name) {
    String s = d.getFilePath().replace(d.getFileName(), name);
    try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of(s), Charset.forName("EUC-KR"))) {
      bufferedReader.lines().forEach(l -> System.out.println(l.replace("|", " ")));
    } catch (Exception e) {
      System.err.println("fail to read lines " + e.getMessage());
    }
  }

  static void delete(String dir) {
    FileSystemUtils.deleteRecursively(new File(DIR + "/" + dir));
  }
}
