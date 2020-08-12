package spacey

case class Rover(coordinates: Vector, direction: Vector, planet: Planet) {

	def sendCommands(movement: String): Rover =
	movement.foldLeft (this) ((r: Rover, op: Char) => r.doOneCommand (op) )

	def doOneCommand(movement: Char): Rover =
	movement match {
	case 'f' => move (1)
	case 'b' => move (- 1)
	case 'l' => rotateLeft
	case _ => this
}

	private def move(step: Int) = {
	val pos = coordinates + direction * step
	val posByX = pos.copy (x = keepInside (pos.x, 0, planet.size.x) )
	val posByY = posByX.copy (y = keepInside (pos.y, 0, planet.size.y) )
	this.copy (coordinates=posByY)
}

	private def keepInside(v: Int, l0: Int, l1: Int): Int =
	if (v < 0) l1 else if (v > l1) 0 else v

	private def rotateLeft = this.copy (direction= Rover.leftRotation * direction)
	private def rotateRight = this.copy (direction=  Rover.rightRotation * direction)

}

object Rover {

	val leftRotation = Matrix(0, -1, 1, 0)
	val rightRotation = Matrix(0, 1, -1, 0)

	def apply(x: Int, y: Int, direction: Char, planet: Planet = Planet(1000, 1000)): Rover =
		new Rover(Vector(x, y), directionToVector(direction), planet)

	private def directionToVector(direction: Char): Vector = direction match {
		case 'N' => Vector(0, 1)
		case 'E' => Vector(1, 0)
		case 'S' => Vector(0, -1)
		case 'W' => Vector(-1, 0)
	}

}





