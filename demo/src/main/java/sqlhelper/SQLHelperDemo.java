package sqlhelper;

import id.perdiatmaja.sqlhelper.utils.QueryUtil;

public class SQLHelperDemo {

    private SQLHelperDemo() {
        //no operation
    }

    public static void showDemo() {
        try {
            SQLHelperDemo sqlHelperDemo = new SQLHelperDemo();

            System.out.println("Select All Query : " + sqlHelperDemo.getSelectAllQueryUtilResult());
            System.out.println("Select Query : " + sqlHelperDemo.getSelectQueryUtilResult());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getSelectQueryUtilResult() throws Exception {
        return new QueryUtil
                        .Builder
                        .Select()
                        .table(TableExample.class)
                        .columns(TargetedTableExample.getColumns())
                        .targetClass(TargetedTableExample.class)
                        .build();
    }

    public String getSelectAllQueryUtilResult() throws Exception {
        return new QueryUtil
                        .Builder
                        .Select()
                        .all()
                        .table(TableExample.class)
                        .build();
    }
}
