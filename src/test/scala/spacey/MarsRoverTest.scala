package spacey

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MarsRoverTest extends AnyFlatSpec with Matchers {

	"Planet" can "define its size" in {
		val mars = Planet(5, 6)
		mars.size.x should be(5)
		mars.size.y should be(6)
	}

	"Rover" should "accept starting point (X,Y) of a rover and the direction (N,S,E,W) it is facing" in {
		val rover = Rover(12, 42, 'E')
		rover.coordinates.x should be(12)
		rover.coordinates.y should be(42)
		rover.direction should be(Vector(1,0))
	}

	it should "be able to move forward (f)" in {
		val rover = Rover(12, 42, 'E')
		val roverAfter = rover.sendCommands("f")
		roverAfter.coordinates.x should be(13)
		roverAfter.coordinates.y should be(42)
	}

	it should "be able to move backward (b)" in {
		val rover = Rover(12, 42, 'E')
		val roverAfter = rover.sendCommands("b")
		roverAfter.coordinates.x should be(11)
		roverAfter.coordinates.y should be(42)
	}

	it should "be able to turn left (l)" in {
		val rover = Rover(12, 42, 'N')
		val roverAfter = rover.sendCommands("l")
		roverAfter.direction should be(Vector(-1,0))
	}

	  it should "be able to receive a character array of commands" in {
	    val rover = Rover(12, 42, 'E')
			val roverAfter=rover.sendCommands("flf")
	    roverAfter.coordinates.x should be (13)
	    roverAfter.coordinates.y should be (43)
	    roverAfter.direction should be (Vector(0,1))
	  }

	  it should "wrap from one edge of the grid to another" in {
	    val rover = Rover(1, 1, 'E', Planet(3, 3))
	    rover.sendCommands("fff")
	    rover.coordinates.x should be (1)
	    rover.sendCommands("rfff")
	    rover.coordinates.y should be (1)
	  }

}
