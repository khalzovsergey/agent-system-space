/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import java.io.Serializable;

/**
 *
 * @author Sergey
 */
public class VariableDouble implements Serializable
{
    public double value;
    
    public VariableDouble()
    {
        this.value = 0.0;
    }
    
    public VariableDouble(double value)
    {
        this.value = value;
    }
}
