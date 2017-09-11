import org.specs2.mutable.Specification
import org.specs2.specification.Scope

import scala.io.Source

class ExampleSpec extends Specification {

  trait Setup extends Scope {
    // this classloader is the one used by TypeSafe ConfigFactory.load("filename")
    val classLoader = Thread.currentThread.getContextClassLoader

    val fileContent = Option(classLoader.getResourceAsStream("testfile.txt")).map(Source.fromInputStream(_).mkString.trim)
  }

  "Load a file from test resources" in new Setup {
    fileContent must beSome("This is a test resource")
  }
}
