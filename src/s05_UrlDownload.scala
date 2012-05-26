import io.Source

object s05_UrlDownload {
	def main(args: Array[String]) {
		val url1 = "http://jose-juan.computer-mind.com"
		val url2 = "http://www.google.com"

		// if we know the source encoding and is our default encoding
		val data1 = Source.fromURL(url1).mkString
		println("%s -> %d length" format(url1, data1 length))

		// if encoding is not set correctly then a exception occur
		try {
			println("%d" format Source.fromURL(url2).mkString.length)
		} catch {
			case e: Exception => println("Exception! %s" format e.getMessage)
		}

		// we can force a specific charset encoding
		val data2 = Source.fromURL(url2)("ISO-8859-1").mkString
		println("%s -> %d length" format(url2, data2 length))

		// what do if unknow charset?
		// a interesting discussion at http://www.scala-lang.org/node/1127
		// in a http context we can do
		val data22 = DownloadString(url2)
		println("%s -> %d length" format(url2, data22 length))
	}
	// why is not this function in Scala (or Java) standard API?
	def DownloadString(url: String): String = {
		DownloadString(new java.net.URL(url))
	}
	def DownloadString(url: java.net.URL): String = {
		import java.io.{InputStreamReader, BufferedReader}
		val r = new BufferedReader(new InputStreamReader(url.openStream))
		val o = new StringBuilder
		while(r.ready)
			o.append(r.readLine)
		r.close()
		return o.toString
	}
}
