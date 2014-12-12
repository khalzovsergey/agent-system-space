/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Sergey
 */
public class KeyBuilder
{
    public static final char SEPARATOR = ' ';
    
    public static String build(Object ... args)
    {
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
            result = result.append(SEPARATOR);
            result = result.append(str);
        }
        return result.toString();
    }
    
    public static String build(KeyValueList list)
    {
        return build(list.getValues());
    }
    
    private String[] keys;
    
    public KeyBuilder(String ... keys)
    {
        this.keys = new String[keys.length];
        System.arraycopy(keys, 0, this.keys, 0, keys.length);
    }
    
    public KeyBuilder(KeyValueList list)
    {
        keys = list.getKeys();
    }
    
    public String build(Map<String, Object> content)
    {
        StringBuilder result = new StringBuilder();
        String str;
        Object value;
        for (String key : keys)
        {
            value = content.get(key);
            if (value == null)
            {
                str = "";
            }
            else
            {
                str = value.toString();
            }
            result = result.append(str.length());
            result = result.append(SEPARATOR);
            result = result.append(str);
        }
        return result.toString();
    }
}
