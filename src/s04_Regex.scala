// scala.util.matching is default imported
/*

   Detailed regular expressions information at:

	   http://docs.oracle.com/javase/1.4.2/docs/api/java/util/regex/Pattern.html

   Embedded activation flags:

	   (?d)	UNIX_LINES, only '\n' char is end of line.
	   (?i)	CASE_INSENSITIVE, by default only US-ASCII are used to match case ('a' == 'A'), use with (?u) to unicode case insensitive.
	   (?x)	COMMENTS, whitespaces and content from # to end of line are ignored.
	   (?m)	MULTILINE, if activated, '^' and '$' match not only at begin and end, even each end of line.
	   (?s)	DOTALL, if activated, all chars (even end of line) match with '.' pattern. Like "single line mode".
	   (?u)	UNICODE_CASE, to perform a unicode case insensitive this options must be specified.

*/
object s04_Regex {
	def main(args: Array[String]) {
		val text = "To be, or not to be, that is the question: Whether 'tis nobler in the mind to suffer The slings and arrows of outrageous fortune Or to take arms against a sea of troubles, And by opposing end them? To die, to sleep, No more; and by a sleep to say we end The heartache, and the thousand natural shocks That flesh is heir to, 'tis a consummation Devoutly to be wish'd. To die, to sleep; To sleep! perchance to dream: ay, there's the rub; For in that sleep of death what dreams may come, When we have shuffled off this mortal coil, Must give us pause: there's the respect That makes calamity of so long life; For who would bear the whips and scorns of time, The oppressor's wrong, the proud man's contumely, The pangs of despis'd love, the law's delay, The insolence of office, and the spurns That patient merit of the unworthy takes, When he himself might his quietus make With a bare bodkin? who would these fardels bear, To grunt and sweat under a weary life, But that the dread of something after death, The undiscover'd country, from whose bourn No traveller returns, puzzles the will, And makes us rather bear those ills we have Than fly to others that we know not of? Thus conscience does make cowards of us all; And thus the native hue of resolution Is sicklied o'er with the pale cast of thought; And enterprises of great pith and moment, With this regard, their currents turn awry, And lose the name of action. Soft you now! The fair Ophelia! Nymph, in thy orisons Be all my sins remember'd."

		// regular expression definition (mode 1)
		val rx1 =
			"""(?ui)[^a-z]([a-z])([a-z]*)\1""".r

		// match into vars
		for(rx1(a, b) <- rx1 findAllIn text)
			print("%s - %s - %s; " format(a, b, a))
		println

		// regular expression with names (mode 2)
		val rx2 = new scala.util.matching.Regex( """(?ui)[^a-z]([a-z])([a-z]*)\1""", "A", "B")

		// using named groups
		for(g <- rx2 findAllIn text matchData)
			print("%s - %s - %s; " format(g.group("A"), g.group("B"), g.group("A")))
		println
	}
}
