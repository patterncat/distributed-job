package cn.patterncat.job.starter.jdbc;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

import java.util.Locale;

/**
 * Created by patterncat on 2017-11-06.
 */
public class JobTableNamingStrategy extends SpringPhysicalNamingStrategy {

    String tablePrefix;

    public JobTableNamingStrategy(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        StringBuilder text = new StringBuilder(name.getText().replace('.', '_'));
        for (int i = 1; i < text.length() - 1; i++) {
            if (isUnderscoreRequired(text.charAt(i - 1), text.charAt(i),
                    text.charAt(i + 1))) {
                text.insert(i++, '_');
            }
        }
        return new Identifier(tablePrefix + "_" +text.toString().toLowerCase(Locale.ROOT), name.isQuoted());
    }

    protected boolean isUnderscoreRequired(char before, char current, char after) {
        return Character.isLowerCase(before) && Character.isUpperCase(current)
                && Character.isLowerCase(after);
    }
}
