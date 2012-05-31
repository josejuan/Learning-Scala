class Pair(val a: Int, val b: Int) {
	def print = println("I'm a pair (%d, %d)!" format a, b)
}
object s09_Patterns {
	def plist(xs: List[Int]) = xs match {
		case 1 :: x :: ts => println("Inicio 1, segundo %d" format x)
		case 2 :: u :: (q@v :: ts) => println ("Inicio 2, sublista con %d elementos" format q.length)
		case _ => println("Ningún match")
	}

	def main(args: Array[String]) {
		plist(List(1, 2, 3))
		plist(List(2, 1, 3, 5, 6, 7))
	}
}
