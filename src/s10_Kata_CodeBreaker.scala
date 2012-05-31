import util.Random

object s10_Kata_CodeBreaker {

	val DIGIT = "RAMVNI"
	val KEYSTRENGTH = 4
	val COUNTDOWN = 10

	def getKey = 0.until(KEYSTRENGTH).foldLeft("")((s, _) => s + DIGIT(Random.nextInt(DIGIT.length)))

	def checkKey(a: Array[Char], b: Array[Char]) = {
		var R = ""
		val f = (r: Char, s: Int, t: Int) => {R += r; a(s) = '+'; b(t) = '-'}
		0.until(a.length).filter(n => a(n) == b(n)).foreach(n => f('X', n, n))
		0.until(a.length).filter(n => a contains b(n)).foreach(n => f('*', a indexOf b(n), n))
		R
	}

	def main(args: Array[String]) {
		var i = true
		println("(Enter \"quit\" to exit.)")
		while(i) {
			var e = true
			var r = COUNTDOWN
			val k = getKey
			println("Key has changed (%s). Enter key:" format k)
			while(i && e && r > 0) {
				val l = Console.readLine
				i = l != "quit"
				e = k != l
				r -= 1
				println(
					if(e) "Result \"%s\". %d retries.".format(checkKey(k.toArray, l.toArray), r)
					else "Correct!")
			}
		}
		println("Bye!")
	}
}
