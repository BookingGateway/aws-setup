import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import jc.core.Json;
import jc.core.Sys;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@WebServlet("/LastEpoch")
public class LastEpochServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        String body = "";
        BufferedReader br = new BufferedReader(request.getReader());
        String line = br.readLine();
        while (line != null) {
            body = body + line;
            line = br.readLine();
        }
        Sys.echo(body, "body");
        var jo = Json.read(body);
        try {
            String homeDir =
                    System.getProperty("os.name").startsWith("Windows")
                            ?
                            System.getProperty("user.home")
                            :
                            "/home/ubuntu"
                    ;
            Path qiitaDbPath = Paths.get(homeDir + "/.lastepoch.db3");
            String databaseUrl = "jdbc:sqlite:" + qiitaDbPath.toString();
            try(ConnectionSource db = new JdbcConnectionSource(databaseUrl)) {
                TableUtils.createTableIfNotExists(db, Item.class);
                Dao<Item, String> itemDao = DaoManager.createDao(db, Item.class);
                TransactionManager.callInTransaction(
                        db,
                        (Callable<Void>) () -> {
                            var item = new Item(jo.toString(false));
                            itemDao.create(item);
                            return null;
                        });
                }
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
        PrintWriter out = response.getWriter();
        out.print(jo.toString(true));
        out.flush();
        out.close();
    }
}
