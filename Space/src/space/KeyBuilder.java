/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

/**
 *
 * @author Sergey
 */
public class KeyBuilder
{
    public static final char DELIMITER = ' ';
    
    public static String build(Object ... args)
    {
        if (args == null)
        {
            return "";
        }
        StringBuilder result = new StringBuilder();
        String str;
        for (Object arg : args)
        {
            if (arg == null)
            {
                str = "";
            }
            else
            {
                str = arg.toString();
            }
            result = result.append(str.length());
            result = result.append(DELIMITER);
            result = result.append(str);
        }
        return result.toString();
    }
}
