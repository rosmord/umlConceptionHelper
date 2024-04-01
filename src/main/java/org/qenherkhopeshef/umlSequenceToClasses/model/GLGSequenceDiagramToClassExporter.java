/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.qenherkhopeshef.umlSequenceToClasses.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.sourceforge.plantuml.BlockUml;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.sequencediagram.Event;
import net.sourceforge.plantuml.sequencediagram.Message;
import net.sourceforge.plantuml.sequencediagram.Participant;
import net.sourceforge.plantuml.sequencediagram.SequenceDiagram;
import net.sourceforge.plantuml.sequencediagram.SequenceDiagramFactory;
import net.sourceforge.plantuml.stereo.Stereotype;
import net.sourceforge.plantuml.text.StringLocated;

/**
 * Expert for exporting an UML Sequence diagram to a UML Class diagram.
 * 
 * This class should be cut in two :
 * 
 * <ul>
 * <li> The exporter (as is currently done)
 * <li> the resulting class diagram, with the toUml() method
 * </ul>
 * 
 * This will be useful for the next step, where we add the possibility 
 * to combine class diagrams together.
 * @author rosmord
 */
public class GLGSequenceDiagramToClassExporter {
    /**
     * A map from participants to actual classes.
     */
    private Map<String,GLGClass> participantToClassMap = new HashMap<>();
    
    /**
     * A map from class names to classes, to ensure unicity of classes.
     */
    private Map<String,GLGClass> nameToClassMap = new HashMap<>();
    
    
    public void buildClassDiagram(List<String> text) {
        List<StringLocated> stringLocatedList = BlockUml.convert(text);

        UmlSource source = UmlSource.create(stringLocatedList, false);
        
        Map<String, String> skinParams = Collections.emptyMap();
        
        SequenceDiagram sequenceDiagram = (SequenceDiagram)new SequenceDiagramFactory().createSystem(source, skinParams);

        for (Participant p : sequenceDiagram.participants()) {
            addParticipant(p);
        }

        // extract method calls from the diagram...
        for (Event e : sequenceDiagram.events()) {
            if (e instanceof Message m) {                
                // Check if m is a method call and not a return : m.getArrowConfiguration().isRightToLeft()
                if (m.getArrowConfiguration().isDotted()) {
                    continue;
                }                
                addMethodCall(m);
            } else {
                // System.out.println("Not a message " + e.toString());
            }            
        }        
    }

    private void addMethodCall(Message m) {
        // Not useful.
        // String codeSource = m.getParticipant1().getCode();
        String targetCode = m.getParticipant2().getCode();
        List<? extends CharSequence> callList = m.getLabel().asList();
        String methodCall = callList.get(callList.size() -1).toString();
        GLGClass clazz = getOrCreateClazz(targetCode);
        clazz.addMethod(methodCall);
    }

    private void addParticipant(Participant p) {
        // the code is the name given to the participant,
        // it is reused afterwards in the rest of the diagram.
        // it might be the name of the class, or a shortcut.
        String code = p.getCode();
        String stereotypeName = extractStereotype(p);       
        // The last element of the display contains the class name.
        Display d = p.getDisplay(false);
        String className = extractClassNameFromDisplay(d);
        addClassWithCode(code, className, stereotypeName);
    }

    
    /**
     * Extract the class name from the display.
     * The last element of the display contains the class name.
     * @param d
     * @return 
     */
    String extractClassNameFromDisplay(Display d) {        
        List<? extends CharSequence> l = d.asList();
        if (l.isEmpty())
            return "";
        return extractClassNameFromString(l.get(l.size() -1));
    }
    
    static String extractClassNameFromString(CharSequence s) {
        StringBuilder builder = new StringBuilder();
        int pos = s.length() - 1;
        while (pos >= 0 && ! Character.isJavaIdentifierPart(s.charAt(pos))) {
            pos --;
        }
        while (pos >= 0 && Character.isJavaIdentifierPart(s.charAt(pos))) {
            builder.append(s.charAt(pos));
            pos--;
        }
        return builder.reverse().toString();
    }

    private String extractStereotype(Participant p) {
        // the stereotype can be null, 
        // and is a relatively free String. Currently,
        // it's mainly used for <<lifecycle>>
        Stereotype stereotype = p.getStereotype();        
        if (stereotype != null) {
            return stereotype.toString();
        } else {
            // the name of the participant String
            // actually, most stereotypes are found there.        
            switch (p.getType()) {
                case BOUNDARY:
                    return "<<boundary>>";
                case CONTROL:
                    return "<<control>>";
                case ENTITY:
                    return "<<entity>>";
                default:
                    return "";                                    
            }
        }
    }

    private void addClassWithCode(String code, String className, String stereotypeName) {
        // Find the class if already available.
        GLGClass clazzFromCode = participantToClassMap.get(code);
        GLGClass clazzFromName = nameToClassMap.get(className); 
        GLGClass clazz;
        if (clazzFromCode != null) {
            clazz = clazzFromCode;
        } else if (clazzFromName != null) {
            clazz = clazzFromName;
        } else {
            clazz = new GLGClass(className);
        }
        
        clazz.setStereotype(stereotypeName);
        
        if (clazzFromCode == null) {
            participantToClassMap.put(code, clazz);
        }
        
        if (clazzFromName == null) {
            nameToClassMap.put(className, clazz);
        }
        
    }

    private GLGClass getOrCreateClazz(String targetCode) {
        GLGClass clazzFromCode = participantToClassMap.get(targetCode);
        if (clazzFromCode == null) {
            clazzFromCode = new GLGClass(targetCode);
            participantToClassMap.put(targetCode, clazzFromCode);
            nameToClassMap.put(targetCode, clazzFromCode);
        }
        return clazzFromCode;
    }
    
    public String toUml() {
        StringBuilder builder= new StringBuilder();
        builder.append("@startuml\n");
        for (GLGClass clazz : nameToClassMap.values()) {
            addClassToUml(builder, clazz);
        }
        builder.append("@enduml\n");
        return builder.toString();
    }

    private void addClassToUml(StringBuilder builder, GLGClass clazz) {
        builder.append("class ");
        builder.append(clazz.getClassName());
        builder.append("{\n");
        for (String method : clazz.getMethods()) {
            builder.append("\t").append(method).append("\n");
        }
        builder.append("}\n");
    }
}
