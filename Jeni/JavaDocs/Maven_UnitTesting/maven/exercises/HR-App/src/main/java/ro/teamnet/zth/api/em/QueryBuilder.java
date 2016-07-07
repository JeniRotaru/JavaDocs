package ro.teamnet.zth.api.em;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by user on 7/7/2016.
 */
public class QueryBuilder {

    public String getValueForQuery(Object value) {

        if (value.getClass().equals(String.class)) {
            return "'"+value+"'";
        }
        if (value.getClass().equals(Date.class)) {
            DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
            return "TO_DATE('"+dateFormat.format((Date)value)+"','mm-dd-YYYY'";
        }
        return "";
    }

}
