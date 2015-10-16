/**
 * Created by hw on 10/16/2015.
 */
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Noveler {
    static String content = "";
    public static void writeFile(FileOutputStream os) throws IOException {
        InputStream is = new ByteArrayInputStream(content.getBytes());
        int len;
        byte[] bs = new byte[1024];
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        os.close();
        is.close();
    }
    public static void getContent(String url) throws IOException {
        // TODO Auto-generated method stub
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Elements precontent = doc.getElementsByClass("Section1");
        content += (precontent.text() + "\r\n");
    }

    public static void main(String[] args) throws IOException {
        String url = "http://www.readers365.com/fuermosi/xzyj/index.htm";
        Document doc = Jsoup.connect(url).get();

        File f = new File("F:\\JavaTest");
        if (!f.exists()) {
            f.mkdirs();
        }
        FileOutputStream os = new FileOutputStream(f.getPath() + "\\" + "a.txt");
        Elements as = doc.select("div.TitleLinks");
        Elements links = as.select("a");
        for (Element link : links) {
            String urls = link.attr("href");
            //System.out.println(link);
            System.out.println("http://www.readers365.com/fuermosi/bswk/" + urls);
            getContent("http://www.readers365.com/fuermosi/bswk/" + urls);
        }
        writeFile(os);
        os.close();
    }
}
