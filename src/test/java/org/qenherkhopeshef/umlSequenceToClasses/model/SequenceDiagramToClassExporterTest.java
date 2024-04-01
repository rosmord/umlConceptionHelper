package org.qenherkhopeshef.umlSequenceToClasses.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.qenherkhopeshef.umlSequenceToClasses.model.GLGSequenceDiagramToClassExporter.*;

public class SequenceDiagramToClassExporterTest {
    
    public SequenceDiagramToClassExporterTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    @Test
    void testExtractClassName() {
        String expected = "MyClass";
        String result = extractClassNameFromString("t: MyClass");
        Assertions.assertEquals(expected, result);
    }
    
    @Test
    void testExtractClassNameSimple() {
        String expected = "MyClass";
        String result = extractClassNameFromString("MyClass");
        Assertions.assertEquals(expected, result);
    }
    
    @Test
    void testExtractClassNameAvecAccents() {
        String expected = "Méthodique";
        String result = extractClassNameFromString("t: Méthodique");
        Assertions.assertEquals(expected, result);
    }
    
    
}
