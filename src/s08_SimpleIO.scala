import java.io.PrintWriter
import java.io.File
import scala.io.Source

object s08_SimpleIO {
	def main(args: Array[String]) {
		val F = "/data/path/file.txt"
		var s = Source.fromFile(F).mkString.replace('a', 'b')
		var w = new PrintWriter(new File(F))
		w.write(s)
		w.close()
	}
}
