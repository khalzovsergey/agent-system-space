/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.common;

import java.io.Serializable;

/**
 *
 * @author Sergey
 */
public class Variable<T> implements Serializable
{
    public T value;
    
    public Variable()
    {
    }
    
    public Variable(T value)
    {
        this.value = value;
    }
}
