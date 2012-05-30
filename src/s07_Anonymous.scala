object s07_Anonymous {

	def isPrime(x: Int): Boolean = !2.until(math.sqrt(x).toInt + 1).exists(x % _ == 0)

	def tree(n: Int) = 1.until(n + 1).
		map(q => 1.until(n + q).foldLeft("")((s, c) => s + (if(c <= n - q) ' ' else '*'))).
		foldLeft("")(_ + _ + '\n')

	def main(args: Array[String]) {
		val f = (x: String, n: Int) => "%s, %d" format(x, n)
		println("Prime list%s" format 2.until(50).filter(isPrime).aggregate("")(f, _ + _))
		println("nums.map(_ * 4).max(_ %% 3) := %d" format 1.until(11).map(_ * 4).max)
		println("%s" format tree(4))
	}
}
