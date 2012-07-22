package org.grules.ast

import groovy.inspect.swingui.AstNodeToScriptVisitor

import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.ModuleNode
import org.codehaus.groovy.runtime.StackTraceUtils
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.ast.GroovyClassVisitor

/**
 * The class is common ancestor for grules script transformations.
 */
abstract class GrulesASTTransformation implements ASTTransformation {

  private GrulesASTTransformationLogger logger
  private GroovyClassVisitor astNodeLoggerVisitor

  /**
   * Initializes a transformation class.
   *
   * @param className a name of the transformed class
   */
  protected void init(String className) {
    logger = new GrulesASTTransformationLogger((className.split('\\.') as List).last())
    astNodeLoggerVisitor = new AstNodeToScriptVisitor(logger.writer)
  }

  /**
   * Visits the module node and applies AST transformations.
   *
   * @param moduleNode a module node
   * @param node a transformed node
   */
  protected void visit(ModuleNode moduleNode, node) {
    try {
      visitModule(moduleNode, node)
      logSource(node)
    } catch (Throwable exception) {
      Writer stringWriter = new StringWriter()
      Throwable sanitizedException = StackTraceUtils.deepSanitize(exception)
      sanitizedException.printStackTrace(new PrintWriter(stringWriter))
      log(stringWriter)
    } finally {
      close()
    }
  }

  abstract protected void visitModule(ModuleNode moduleNode, node)

  /**
   * Writes a message to a log.
   *
   * @param message a message to log
   */
  protected void log(message) {
    logger.write(message.toString())
  }

  /**
   * Writes a message to a log. The message is prepended by the specifed label.
   *
   * @param message a message to log
   */
  protected void log(String label, message) {
    log(label + ': ' + message)
  }

  private void close() {
    log('\nTransformation complete')
    logger.close()
  }

  private void logSource(ClassNode node) {
    astNodeLoggerVisitor.visitClass(node)
  }

  private void logSource(MethodNode node) {
    astNodeLoggerVisitor.visitMethod(node)
  }
}
