import java.awt.Color
import java.awt.event.{ActionEvent, ActionListener}
import scala.Array.canBuildFrom
import scala.swing.{Dimension, Graphics2D, MainFrame, Panel, SimpleSwingApplication}
import scala.swing.event.{Key, KeyPressed}
import javax.swing.Timer

object s01_Snake extends SimpleSwingApplication {
	val S = 11
	val W = 10
	val S2 = S * S
	val vs = S * W
	var d = 0
	val D = Array(1, 0, 0, -1, -1, 0, 0, 1)
	var s = Array((S >> 1, S >> 1))
	val rnd = new java.util.Random()
	var f = food

	def food = {
		val l = 0.to(S2 - 1).map(p => (p % S, p / S)).filterNot(s.contains)
		l(rnd.nextInt(l.length))
	}

	def top = new MainFrame {
		title = "Snake!"
		contents = new Panel {
			focusable = true
			background = Color.white
			preferredSize = new Dimension(vs, vs)

			override def paint(g: Graphics2D) {
				g.setBackground(Color.white)
				g.clearRect(0, 0, vs, vs)
				g.setColor(Color.green)
				s.foreach {
					case (x, y) => g.fillRect(x * W, y * W, W, W)
				}
				g.setColor(Color.red)
				g.fillRect(f._1 * W, f._2 * W, W, W)
				if(!t.isRunning) {
					g.setColor(Color.black)
					g.drawString("Ouch!", 10, 10)
				}
			}

			listenTo(keys)
			reactions += {
				case KeyPressed(_, Key.Left, _, _) => d = (d + 1) % 4
				case KeyPressed(_, Key.Right, _, _) => d = (d + 3) % 4
			}
		}
		val t: Timer = new Timer(250, new ActionListener() {
			def actionPerformed(e: ActionEvent) {
				val n = ((S + s(0)._1 + D(d << 1)) % S, (S + s(0)._2 + D((d << 1) + 1)) % S)
				if(s contains n) t.stop()
				s +:= n
				if(n == f) f = food else s = s take s.length - 1
				repaint()
			}
		})
		t.start()
	}
}
