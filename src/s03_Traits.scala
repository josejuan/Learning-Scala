object s03_Traits {

	// a generic string processor
	abstract class StringPipeline(val name: String) {
		def process(value: String): String
	}

	// some pipeline steps

	trait ToUpper extends StringPipeline {
		abstract override def process(value: String): String = super.process(value.toUpperCase)
	}

	trait ToLower extends StringPipeline {
		abstract override def process(value: String): String = super.process(value.toLowerCase)
	}

	trait Reverse extends StringPipeline {
		abstract override def process(value: String): String = super.process(value.reverse)
	}

	trait Capitalize extends StringPipeline {
		abstract override def process(value: String): String = super.process(value(0).toUpper + value.substring(1))
	}

	// one "real" pipeline processor

	class StringCounter(name: String) extends StringPipeline(name) {
		private var n: Int = 0

		def process(value: String): String = {
			n += 1
			println("%s %d: %s" format(name, n, value))
			value
		}
	}

	// some instances combining multiple pipeline steps, pipe from right to left!

	object upreverse extends StringCounter("UpReverse") with ToUpper with Reverse

	object recap extends StringCounter("ReCap") with Capitalize with ToLower with Reverse

	// test!
	def main(args: Array[String]) {
		for(s <- Array("apple", "BANANA", "Cherry", "orange", "BLUEBERRY", "Kiwifruit")) {
			upreverse process s
			recap process s
		}
	}
}
