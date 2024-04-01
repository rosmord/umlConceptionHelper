package org.qenherkhopeshef.umlSequenceToClasses;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import javax.swing.SwingUtilities;

import net.sourceforge.plantuml.BlockUml;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.sequencediagram.Event;
import net.sourceforge.plantuml.sequencediagram.Message;
import net.sourceforge.plantuml.sequencediagram.Participant;
import net.sourceforge.plantuml.sequencediagram.SequenceDiagram;
import net.sourceforge.plantuml.sequencediagram.SequenceDiagramFactory;
import net.sourceforge.plantuml.text.StringLocated;
import org.qenherkhopeshef.umlSequenceToClasses.model.GLGSequenceDiagramToClassExporter;

public class Main {
    
    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(
             UmlSequenceToClassesApp::new
        );
        
        /*List<String> lines = Files.readAllLines(Path.of("src/test/resources/seq1.puml"));
        
        GLGSequenceDiagramToClassExporter exporter = new GLGSequenceDiagramToClassExporter();
        exporter.buildClassDiagram(lines);
        String uml = exporter.toUml();
        System.out.println(uml);
*/
      
    }
}
