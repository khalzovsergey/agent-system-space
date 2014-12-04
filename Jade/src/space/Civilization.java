/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sergey
 */
public class Civilization
{
    private Map<String, Object> attributes;
    
    public Civilization()
    {
        attributes = new HashMap<>();
    }
    
    public Map<String, Object> getAttributes()
    {
        return attributes;
    }
}
