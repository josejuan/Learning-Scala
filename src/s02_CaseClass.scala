object s02_CaseClass {

	class Node[T <% Ordered[T]](val value: T) {

		def <~(n: Node[T]): Node[T] = {
			val b =
				if(n.value < value)
					this match {
						case Branch(_, null, r) => (n, r)
						case Branch(_, l, r) => (l <~ n, r)
						case _ => (n, null)
					}
				else
					this match {
						case Branch(_, l, null) => (l, n)
						case Branch(_, l, r) => (l, r <~ n)
						case _ => (null, n)
					}
			Branch[T](value, b._1, b._2)
		}

		private def print(value: T): String = "%s, " format value.toString

		private def print(n: Node[T]): String = n match {
			case null => ""
			case Branch(value, left, right) => "%s%s%s" format(print(left), print(value), print(right))
			case Leaf(value) => print(value)
		}

		override def toString: String = print(this)

	}

	case class Branch[T <% Ordered[T]](x: T, left: Node[T], right: Node[T]) extends Node[T](x)

	case class Leaf[T <% Ordered[T]](x: T) extends Node[T](x)

	implicit def stringWrapper(s: String) = Leaf[String](s)

	def main(args: Array[String]) {
		println("Pablo" <~ "Tyrone" <~ "Uniqua" <~ "Tasha" <~ "Austin")
	}

}
