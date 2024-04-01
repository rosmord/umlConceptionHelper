package org.qenherkhopeshef.umlSequenceToClasses;

import java.util.*;
import java.util.stream.Collectors;
import javax.swing.JFrame;
import org.qenherkhopeshef.umlSequenceToClasses.model.GLGSequenceDiagramToClassExporter;
import org.qenherkhopeshef.umlSequenceToClasses.ui.UmlSeq2ClassesUI;

/**
 * Code for the application itself.
 * @author rosmord
 */
public class UmlSequenceToClassesApp {
    
    private UmlSeq2ClassesUI ui = new UmlSeq2ClassesUI();

    public UmlSequenceToClassesApp() {
        ui.setVisible(true);
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui.getConvertButton().addActionListener(e -> convertSequenceDiagram());
        Thread.setDefaultUncaughtExceptionHandler((thread,exception) -> handleException(thread, exception));
    }

    private void convertSequenceDiagram() {        
        String sequenceDiagram = ui.getSequenceArea().getText();
        
        List<String> lines= sequenceDiagram.lines().collect(Collectors.toList());
        
        GLGSequenceDiagramToClassExporter exporter = new GLGSequenceDiagramToClassExporter();
        exporter.buildClassDiagram(lines);
        String uml = exporter.toUml();
        ui.getClassArea().setText(uml);
    }

    private void handleException(Thread thread, Throwable exception) {
        StringBuilder builder = new StringBuilder();
        builder.append("Problem in UML Sequence diagram, leading to exception\n");        
        builder.append('\n');
        String errorMessage = exception.toString();
        builder.append(errorMessage);
        ui.getClassArea().setText(builder.toString());
    }
    
}
