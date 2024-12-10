import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.Callable;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import io.pebbletemplates.pebble.PebbleEngine;
import io.pebbletemplates.pebble.loader.ClasspathLoader;
import io.pebbletemplates.pebble.template.PebbleTemplate;
import jc.core.Sys;
//import qiita.Item;
//import qiita.ItemOrder;

@WebServlet("/MyDate")
public class MyDate extends HttpServlet {
    String[] youbi = {"日", "月", "火", "水", "木", "金", "土"};

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = request.getParameter("page");
        int pageNo = (page == null) ? 1 : Integer.parseInt(page);
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><head></head><body>");

        Calendar cal = Calendar.getInstance();
        out.printf(
                "%d年%d月%d日%s曜日%d時%d分%d秒<br>%n",
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH) + 1,
                cal.get(Calendar.DAY_OF_MONTH),
                youbi[cal.get(Calendar.DAY_OF_WEEK) - 1],
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                cal.get(Calendar.SECOND));

        PebbleEngine engine = new PebbleEngine.Builder()
                .loader(new ClasspathLoader())
                .build();
        //PebbleTemplate compiledTemplate = engine.getTemplate("templateName");
        PebbleTemplate compiledTemplate = engine.getTemplate("tpl/tpl-01.txt");
        Writer writer = new StringWriter();

        Map<String, Object> context = new LinkedHashMap<>();
        context.put("name", "Mitchell");
        /*
        Map<String, Object> context = new LinkedHashMap<>();
        context.put("prevPageNo", pageNo - 1);
        context.put("nextPageNo", pageNo + 1);
        context.put("name", "Mitchell");
        List<Integer> list = new ArrayList<>();
        list.add(11);
        list.add(22);
        list.add(33);
        context.put("list", list);
        String homeDir = System.getenv("HOME");
        //String homeDir = System.getProperty("user.home");
        Path qiitaDbPath = Paths.get(homeDir + "/.qiita-items.db3");
        String databaseUrl = "jdbc:sqlite:" + qiitaDbPath.toString();
        try(ConnectionSource db = new JdbcConnectionSource(databaseUrl)) {
            Dao<Item, String> itemDao = DaoManager.createDao(db, Item.class);
            Dao<ItemOrder, String> itemOrderDao = DaoManager.createDao(db, ItemOrder.class);
            long offset = (pageNo - 1) * 5;
            List<ItemOrder> queryResult =
                    itemOrderDao
                            .queryBuilder()
                            .orderBy("offset_", true)
                            .where()
                            .between("offset_", offset, offset + (5 -1))
                            .query();
            queryResult.forEach(x -> {
                Sys.echo(x, "x");
            });
            context.put("queryResult", queryResult);
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
        */

        compiledTemplate.evaluate(writer, context);
        String output = writer.toString();
        out.print(output);

        out.println("</body></html>");
        out.close();
    }
}
