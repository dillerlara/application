package br.com.application.dialects;

import org.hibernate.dialect.MySQL5Dialect;

import br.com.application.function.GroupConcatFunction;

public class CustomMySQLDialect extends MySQL5Dialect {
    public CustomMySQLDialect() {
        super();
        registerFunction("group_concat", new GroupConcatFunction());
    }
}