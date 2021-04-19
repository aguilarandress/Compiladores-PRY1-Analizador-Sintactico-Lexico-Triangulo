/*
 * IDE-Triangle v1.0
 * Compiler.java 
 */

package Triangle;

import Triangle.CodeGenerator.Frame;
import java.awt.event.ActionListener;
import Triangle.SyntacticAnalyzer.SourceFile;
import Triangle.SyntacticAnalyzer.Scanner;
import Triangle.AbstractSyntaxTrees.Program;
import Triangle.SyntacticAnalyzer.Parser;
import Triangle.ContextualAnalyzer.Checker;
import Triangle.CodeGenerator.Encoder;
import Triangle.AbstractSyntaxTrees.SimpleProgram;
import Triangle.AbstractSyntaxTrees.CompoundProgram;



/** 
 * This is merely a reimplementation of the Triangle.Compiler class. We need
 * to get to the ASTs in order to draw them in the IDE without modifying the
 * original Triangle code.
 *
 * @author Luis Leopoldo P�rez <luiperpe@ns.isi.ulatina.ac.cr>
 */
public class IDECompiler {

    // <editor-fold defaultstate="collapsed" desc=" Methods ">
    /**
     * Creates a new instance of IDECompiler.
     *
     */
    public IDECompiler() {
    }
    
    /**
     * Particularly the same compileProgram method from the Triangle.Compiler
     * class.
     * @param sourceName Path to the source file.
     * @return True if compilation was succesful.
     */
    public boolean compileProgram(String sourceName) {
        System.out.println("********** " +
                           "Triangle Compiler (IDE-Triangle 1.0)" +
                           " **********");
        
        System.out.println("Syntactic Analysis ...");
        SourceFile source = new SourceFile(sourceName);
        Scanner scanner = new Scanner(source);
        report = new IDEReporter();
        Parser parser = new Parser(scanner, report);
        boolean success = false;
        
        
        // @author        Andres
        // @descripcion   Determinar que tipo de Program parsear
        // @funcionalidad Parsear Program
        // @codigo        A.146
        parser.parseProgram();
        isSimpleProgram = parser.getIsSimpleProgram();
        if (isSimpleProgram) {
            simpleProgramAST = parser.getSimpleProgram();
        } else {
            compoundProgramAST = parser.getCompoundProgram();
        }
        /*
            rootAST = parser.parseProgram();
        */
        // END CAMBIO Andres
        if (report.numErrors == 0) {
            //System.out.println("Contextual Analysis ...");
            //Checker checker = new Checker(report);
            //checker.check(rootAST);
            if (report.numErrors == 0) {
               // System.out.println("Code Generation ...");
                //Encoder encoder = new Encoder(report);
               //encoder.encodeRun(rootAST, false);
                
                if (report.numErrors == 0) {
                    //encoder.saveObjectProgram(sourceName.replace(".tri", ".tam"));
                    success = true;
                }
            }
        }

        if (success)
            //Satanas mas playo
            System.out.println("Compilation was successful.");
        else
            System.out.println("Compilation was unsuccessful.");
        
        return(success);
    }
      
    /**
     * Returns the line number where the first error is.
     * @return Line number.
     */
    public int getErrorPosition() {
        return(report.getFirstErrorPosition());
    }
        
    
    
    // @author        Andres
    // @descripcion   Determinar que tipo de Program parsear
    // @funcionalidad Parsear Program
    // @codigo        A.144
    public boolean getIsSimpleProgram() {
        return this.isSimpleProgram;
    }
    
    public SimpleProgram getSimpleProgram() {
        return simpleProgramAST;
    }
    
    public CompoundProgram getCompoundProgram() {
        return compoundProgramAST;
    }
    /*
        public Program getAST() {
            return(rootAST);
        }
    */
    // END CAMBIO Andres
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Attributes ">
    
    
    // @author        Andres
    // @descripcion   Determinar que tipo de Program parsear
    // @funcionalidad Parsear Program
    // @codigo        A.145
    private boolean isSimpleProgram;
    private SimpleProgram simpleProgramAST;        
    private CompoundProgram compoundProgramAST;
    private IDEReporter report;     // Our ErrorReporter class.
    /*
        private Program rootAST;        // The Root Abstract Syntax Tree.    
    private IDEReporter report;     // Our ErrorReporter class.
    */
    // END CAMBIO Andres
    // </editor-fold>
}
