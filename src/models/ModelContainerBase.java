/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author thonon
 */
public abstract class ModelContainerBase {
    
    public abstract void insert(ModelBase model);
    
    public abstract void delete(ModelBase model);
    
    public abstract void update(ModelBase model);
    
    public abstract void selectAll();
}
