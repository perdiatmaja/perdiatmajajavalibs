package sqlhelper;

import utils.QueryUtil;

public class SQLHelperDemo {

    private SQLHelperDemo() {
        //no operation
    }

    public static void showDemo() {
        try {
            SQLHelperDemo sqlHelperDemo = new SQLHelperDemo();
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
}
