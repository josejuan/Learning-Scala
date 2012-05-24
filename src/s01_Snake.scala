// Arrow left/right to move snake!

import java.awt.Color
import java.awt.event.{ActionEvent, ActionListener}
import scala.Array.canBuildFrom
import scala.swing.{Dimension, Graphics2D, MainFrame, Panel, SimpleSwingApplication}
import scala.swing.event.{Key, KeyPressed}
import javax.swing.Timer

object s01_Snake extends SimpleSwingApplication {
	val S = 11 // ground has SxS cells
	val W = 10 // cell has WxW pixels
	val D = Array(1, 0, 0, -1, -1, 0, 0, 1) // move types: right, up, left, down
	var d = 0 // actual move type
	var s = Array((S >> 1, S >> 1)) // snake body FILO
	val rnd = new java.util.Random() // I can't remember...
	var f = food // actual food pos

	// return a free cell (to put food)
	def food = (0 to S * S - 1 map (p => (p % S, p / S)) filterNot s.contains)(rnd nextInt S * S)

	def top = new MainFrame {
		title = "Snake!"
		contents = new Panel {
			focusable = true // mandatory, if not, not key/mouse events
			preferredSize = new Dimension(S * W, S * W)
			override def paint(g: Graphics2D) {
				g.setBackground(Color.white)
				g.clearRect(0, 0, S * W, S * W)
				g.setColor(Color.green)
				s.foreach { case (x, y) => g.fillRect(x * W, y * W, W, W) }
				g.setColor(Color.red)
				g.fillRect(f._1 * W, f._2 * W, W, W)
				if(!t.isRunning) {
					g.setColor(Color.black)
					g.drawString("Ouch!", 10, 10)
				}
			}
			listenTo(keys)
			reactions += {case KeyPressed(_, Key.Left, _, _) => d = (d + 1) % 4}
			reactions += {case KeyPressed(_, Key.Right, _, _) => d = (d + 3) % 4}
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
