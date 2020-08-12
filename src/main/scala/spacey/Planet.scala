package spacey


case class Planet(size: Vector)

object Planet {

	def apply(x: Int, y: Int): Planet = Planet(Vector(x,y))

}

