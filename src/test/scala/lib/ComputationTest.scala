package lib

import org.scalatest.{BeforeAndAfterAll, FlatSpec, GivenWhenThen, Matchers}

class ComputationTest extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfterAll {

	behavior of "Computation"

	it should "compute the sum" in {
		Given("2 values")
		val a=1
		val b=2

		When("compute the sum")
		val result=Computation.sum(a,b)

		Then("the result must be the expected")
		result shouldBe a+b
	}


}
