object s02_CaseClass {

	class Node[T](val value: T) {

		private def print(value: T): String = "%s, " format value.toString

		private def print(n: Node[T]): String = n match {
			case null => ""
			case Branch(value, left, right) => "%s%s%s" format(print(left), print(value), print(right))
			case Leaf(value) => print(value)
		}

		override def toString: String = print(this)

	}

	case class Branch[T](x: T, left: Node[T], right: Node[T]) extends Node[T](x)

	case class Leaf[T](x: T) extends Node[T](x)

	def add[T <% Ordered[T]](tree: Node[T], node: Node[T]): Node[T] = tree match {
		case Branch(value, left, right) =>
			if(node.value < value)
				Branch[T](value, add(left, node), right)
			else
				Branch[T](value, left, add(right, node))
		case Leaf(value) =>
			if(node.value < value)
				Branch[T](value, node, null)
			else
				Branch[T](value, null, node)
		case _ =>
			node
	}

	def main(args: Array[String]) {
		val tree =
			add(
				add(
					add(
						add(
							Leaf[String]("Peter"),
							Leaf[String]("John")
						),
						Leaf[String]("Austin")
					),
					Leaf[String]("Iniqua")
				),
				Leaf[String]("Tairon")
			)

		println(tree)
	}

}
