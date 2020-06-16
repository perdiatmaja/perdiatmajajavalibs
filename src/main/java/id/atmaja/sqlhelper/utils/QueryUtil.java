package id.atmaja.sqlhelper.utils;

import id.atmaja.sqlhelper.exception.NoColumnSelectedException;

public class QueryUtil {

    private static final String REGEX_COLUMN_REPLACE = "\\%(column)\\%";

    private static final String REGEX_TABLE_REPLACE = "\\%(table)\\%";

    private static final String WHERE = " WHERE ";

    private static final String LIKE = " LIKE ";

    private static final String OR = " OR ";

    private static final String AND = " AND ";

    private static final String ALL_COLUMN = "*";

    private static final String LIMIT = " limit ";

    private static final String ORDER_BY = " ORDER BY ";

    private static final String ASC = " ASC ";

    private static final String DESC = " DESC ";

    public static class ColumnsBuilder {

        private String column = "";

        public ColumnsBuilder columns(String... columns) {
            for (String column : columns) {
                this.column += column + ", ";
            }

            return this;
        }

        public ColumnsBuilder columnsWithAlias(String alias, String... columns) {
            for (String column : columns) {
                this.column += alias+ "." + column + ", ";
            }

            return this;
        }

        public String build() {
            return column.substring(0, column.length()-2);
        }
    }

    public static class Builder {

        public static class Select {

            private static final String SELECT_QUERY = "SELECT %column% FROM %table%";

            private String query = SELECT_QUERY;

            private String column = "";

            private boolean queryAll;

            private String targetClass;

            public Select table(Class table, String alias) {
                query = query.replaceFirst(REGEX_TABLE_REPLACE, table.getSimpleName() + " " + alias);
                return this;
            }

            public Select table(Class table) {
                query = query.replaceFirst(REGEX_TABLE_REPLACE, table.getSimpleName());
                return this;
            }

            public Select columns(String... columns) {
                for (String column : columns) {
                    this.column += column + ", ";
                }

                return this;
            }

            public Select columnsWithAlias(String alias, String... columns) {
                for (String column : columns) {
                    this.column += alias+ "." + column + ", ";
                }

                return this;
            }

            public Select targetClass(Class targetClass) {
                this.targetClass = targetClass.getName();
                return this;
            }

            public Select all() {
                query = query.replaceFirst(REGEX_COLUMN_REPLACE, ALL_COLUMN);
                queryAll = true;
                return this;
            }

            public Select WHERE() {
                query += WHERE;
                return this;
            }

            public Select condition(String condition) {
                query += condition;
                return this;
            }

            public Select condition(String condition, String requestParam) {
                query += condition + "=:" + requestParam;
                return this;
            }

            public Select OR () {
                query += OR;
                return this;
            }

            public Select AND () {
                query += AND;
                return this;
            }

            public Select limit(int limit) {
                query += LIMIT + limit;
                return this;
            }

            public Join INNER_JOIN(Class classTable, String alias) {
                return new Join().INNER_JOIN(classTable.getSimpleName(), alias);
            }

            public Select ORDER_BY(String column) {
                query += ORDER_BY + column;
                return this;
            }

            public Select ASC() {
                query += ASC;
                return this;
            }

            public Select DESC() {
                query += DESC;
                return this;
            }

            public String build() throws Exception{
                if (!queryAll) {
                    query = query.replaceFirst(REGEX_COLUMN_REPLACE, createSelectedColumn());
                }

                return query;
            }

            private String createSelectedColumn() throws Exception {
                if (TextUtil.isEmpty(column)) {
                    throw new NoColumnSelectedException();
                }

                String targetedClass = "new " + targetClass + "(";
                targetedClass += column.substring(0, column.length()-2);
                return targetedClass + ")";
            }

            private Select setJoin(String joinQuery) {
                query += joinQuery;
                return this;
            }

            public class Join {

                private static final String JOIN = "JOIN ";

                private static final String INNER = " INNER ";

                private static final String ON = " ON ";

                private String joinState = "";

                private Join INNER_JOIN(String tableJoin, String alias) {
                    joinState += INNER + JOIN + tableJoin + " " + alias;
                    return this;
                }

                public Join ON(String aliasTable1, String aliasTable2, String table1Column, String table2Column) {
                    String table1Join = aliasTable1 + "." + table1Column;
                    String table2Join = aliasTable2 + "." + table2Column;
                    joinState += ON + table1Join + "=" + table2Join;
                    return this;
                }

                public Join ON(String aliasTable1, String aliasTable2, String column) {
                    return ON(aliasTable1, aliasTable2, column, column);
                }

                public Select THEN() {
                    return Select.this.setJoin(joinState);
                }
            }
        }
    }
}
