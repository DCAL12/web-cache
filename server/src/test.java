import com.github.dcal12.web_cache.server.utility.FileBrowser;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Douglas Callaway on 8/11/16.
 */
public class test {

    public static void main(String args[]) {
        FileBrowser fileBrowser = FileBrowser.getInstance();
        List<String> downloadFile = null;
        try {
            downloadFile = fileBrowser.downloadFile("/home/user/classes/COMPSCI-711/assignments/web-cache/server/files/", "grocery list");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (downloadFile != null) {

            System.out.println(Arrays.toString(downloadFile.toArray(new String[0])));
        }


    }


}
