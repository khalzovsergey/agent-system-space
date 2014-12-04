/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.planets;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sergey
 */
public class Planet
{
//    private String name;
//     location, orientation, etc.
//    private PlanetCoordinates coordinates;
//    private Map<String, VarDouble> coordinates;
//    private Map<String, VarDouble> resources;
//    private List<Civilization> civilizations;
    private Map<String, Object> attributes;
    
    public Planet()
    {
        attributes = new HashMap<>();
    }
    
    public Map<String, Object> getAttributes()
    {
        return attributes;
    }
}
