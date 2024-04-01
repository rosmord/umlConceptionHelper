package org.qenherkhopeshef.umlSequenceToClasses.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 * GUI for Sequence diagrams to classes.
 * @author rosmord
 */
public class UmlSeq2ClassesUI extends JFrame {
    
    /**
     * The place where the sequence diagram will be copied.
     */
    private JTextArea sequenceArea = new JTextArea(20, 80);
    
    /**
     * The place where the resulting class Diagram will appear.
     */
    
    private JTextArea docArea;
    
    private JTextArea classArea = new JTextArea(20, 80);

    private JButton convertButton = new JButton("convert");
    
    private String documentation = 
            """
            Convertit les diagrammes de séquences en diagramme de classe.
            Très primitif pour l'instant (voir le README pour les avancées planifiées).
            
            Copiez le code plantuml (avec la balise @startuml) d'UN diagramme
            de séquence dans la zone ci-dessous. 
            
            Si vous voulez mélanger plusieurs diagrammes, supprimez les balises 
            intermédiaires @startuml et @enduml.
            
            Pressez le bouton, le diagramme de classes correspondant apparaît en bas.
            """;

    public UmlSeq2ClassesUI() {
        setLayout(new GridBagLayout());
        docArea = new JTextArea(documentation);
        docArea.setEditable(false);
        classArea.setEditable(false);
        GridBagConstraints cc = new GridBagConstraints();
        cc.weightx = 1;
        cc.weighty = 0;
        cc.gridy = 0;
        cc.insets = new Insets(10,5,5,5);
        cc.fill = GridBagConstraints.HORIZONTAL;        
        //add(new JScrollPane(docArea), cc);        
        add(docArea, cc);        
        cc.insets = new Insets(0,5,5,5);
        cc.gridy++;
        add(new JLabel("sequence diagram"), cc);
        cc.gridy++;
        cc.fill = GridBagConstraints.BOTH;
        cc.weighty = 1;
        add(new JScrollPane(sequenceArea), cc);
        cc.fill = GridBagConstraints.HORIZONTAL;
        cc.weighty = 0;      
        cc.gridy++;
        add(convertButton, cc);
        cc.gridy++;
        add(new JLabel("class diagram"), cc);
        cc.gridy++;
        cc.fill = GridBagConstraints.BOTH;
        cc.weighty = 1;
        cc.insets = new Insets(0,5,10,5);
        add(new JScrollPane(classArea), cc);

        pack();        
    }
    
    
    
    public JTextArea getClassArea() {
        return classArea;
    }

    public JTextArea getSequenceArea() {
        return sequenceArea;
    }

    public JButton getConvertButton() {
        return convertButton;
    }
    
    
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UmlSeq2ClassesUI ui = new UmlSeq2ClassesUI();
            ui.setVisible(true);
            ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }
    
}

