package org.grules.ast

import java.security.AccessControlException

import org.codehaus.groovy.control.io.NullWriter
import org.grules.config.AstTransformationConfigFactory

/**
 * A logger for grules script transformations.
 */
class GrulesASTTransformationLogger implements Closeable {

  private static final String LOG_DIR = (new AstTransformationConfigFactory()).createConfig().compilerLogPath
  protected final Writer writer

  GrulesASTTransformationLogger(String filename) {
    try {
      File logDir = new File(LOG_DIR)
      logDir.mkdirs()
      File logFile = new File("$LOG_DIR/${filename}.log")
      logFile.createNewFile()
      writer = new BufferedWriter(new FileWriter(logFile))
    } catch (IOException e) {
      writer = NullWriter.DEFAULT
    } catch (AccessControlException e) {
      writer = NullWriter.DEFAULT
    }
  }

  /**
   * Write the specified message to a log.
   *
   * @param message
   */
  void write(String message) {
    String sanitizedMessage = message.replaceAll(org.codehaus.groovy.ast.ClassNode.package.name + '.', '')
    sanitizedMessage = sanitizedMessage.replaceAll('@\\w+', '')
    writer.write(sanitizedMessage + '\n\n')
  }

  /**
   * Closes the logger writer.
   */
   void close() {
    writer.close()
  }
}
