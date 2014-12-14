/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.common;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sergey
 */
public class KeyValueList
{
    private String[] keys;
    private Object[] values;
    private Map<String, Integer> valueIndexes;
    
    public KeyValueList(String ... keys)
    {
        this.keys = new String[keys.length];
        System.arraycopy(keys, 0, this.keys, 0, keys.length);
        values = new Object[keys.length];
        valueIndexes = new HashMap<>(keys.length * 2);
        int i = 0;
        for (String key : keys)
        {
            valueIndexes.put(key, i++);
        }
    }
    
    public Object getValue(String key)
    {
        return values[valueIndexes.get(key)];
    }
    
    public Object setValue(String key, Object value)
    {
        return values[valueIndexes.get(key)] = value;
    }
    
    public String[] getKeysCopy()
    {
        String[] result = new String[keys.length];
        System.arraycopy(keys, 0, result, 0, keys.length);
        return result;
    }
    
    public Object[] getValues()
    {
        return values;
    }
    
    public void clearValues()
    {
        for (int i = 0; i < values.length; i++)
        {
            values[i] = null;
        }
    }
}
