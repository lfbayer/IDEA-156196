package com.lbayer.idea156196;

import java.sql.PreparedStatement;
import java.util.List;

import com.zaxxer.sansorm.OrmElf;
import com.zaxxer.sansorm.SqlClosure;

/**
 * This class is an example of the parsing failure found in IntelliJ bug 156196
 *
 * <a href="https://youtrack.jetbrains.com/issue/IDEA-156196">IDEA-156196</a>
 *
 * This code can't actually be run, but it should it should be compilable.
 */
public class Reproduction
{
    /**
     * IntelliJ shows no errors for this method.
     */
    public static List<Reproduction> parseSucceeds()
    {
        return SqlClosure.execute(connection -> {
            PreparedStatement stmt = connection.prepareStatement("foo");
            List<Reproduction> result = OrmElf.statementToList(stmt, Reproduction.class, 1, "");
            return result;
        });
    }

    /**
     * IntelliJ highlights the following lambda with an error
     */
    public static List<Reproduction> parseFails()
    {
        return SqlClosure.execute(connection -> {
            PreparedStatement stmt = connection.prepareStatement("foo");
            return OrmElf.statementToList(stmt, Reproduction.class, 1, "");
        });
    }
}
