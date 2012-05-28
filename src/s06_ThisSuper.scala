class Animal(val name: String) {
	def Name: String = "Animal: %s" format name
}

trait FormatName extends Animal {
	override def Name: String = "This animal name is %s" format name
}

trait FormatName2 extends Animal {
	override def Name: String = "animal := {name: '%s'}" format name
}

class Dog(name: String) extends Animal(name) with FormatName with FormatName2 {
	override def Name: String = "Dog: %s" format name

	def Name(oldName: Boolean): String = if(oldName) super.Name else this.Name

	def Name1: String = super[FormatName].Name

	def Name2: String = super[FormatName2].Name
}

object s06_ThisSuper {
	def main(args: Array[String]) {
		val d = new Dog("Puppy")
		println("d.Name ......... %s" format d.Name)
		println("d.Name(true) ... %s" format d.Name(true))
		println("d.Name1 ........ %s" format d.Name1)
		println("d.Name2 ........ %s" format d.Name2)
	}
}
