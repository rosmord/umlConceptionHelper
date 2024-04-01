package org.qenherkhopeshef.umlSequenceToClasses.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * A class in the exported class diagram.
 * @author rosmord
 */
public class GLGClass {
    private String className;
    private String stereotype = "";
    private Set<String> methods = new HashSet<>();

    
    public GLGClass(String className) {
        this.className = className;
    }

    /**
     * The name of the class.
     * @return the name of the class
     */
    public String getClassName() {
        return className;
    }

    public void setStereotype(String stereotypeName) {
        this.stereotype = stereotypeName;
    }
    
    public Collection<String> getMethods() {
        return methods;
    }
    
    public void addMethod(String methodCall) {
        methods.add(methodCall);
    }

    public String getStereotype() {
        return stereotype;
    }
    
    
    
}
