/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.common;

import java.util.Map;

/**
 *
 * @author Sergey
 */
public class SimpleKeyBuilder implements KeyBuilder
{
    public static final char SEPARATOR = ' ';
    
    public static String join(Object ... args)
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
    
    public static String getKey(KeyValueList list)
    {
        return join(list.getValues());
    }
    
    private String[] keys;
    
    public SimpleKeyBuilder(String ... keys)
    {
        this.keys = new String[keys.length];
        System.arraycopy(keys, 0, this.keys, 0, keys.length);
    }
    
    public SimpleKeyBuilder(KeyValueList list)
    {
        keys = list.getKeysCopy();
    }
    
    public String getKey(Map<String, Object> msgContent)
    {
        StringBuilder result = new StringBuilder();
        String str;
        Object value;
        for (String key : keys)
        {
            value = msgContent.get(key);
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
