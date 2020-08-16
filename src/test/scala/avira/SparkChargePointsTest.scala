package avira

import SparkChargePoints.schema
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import testUtil.SparkSessionTestWrapper
import testUtil.UtilTest.ExtendedString

class SparkChargePointsTest extends AnyFlatSpec with Matchers with SparkSessionTestWrapper {

	val data=
		"""
			|ChargingEvent,CPID,StartDate,StartTime,EndDate,EndTime,Energy
			|16673806,AN11719,2017-12-31,14:46:00,2017-12-31,18:00:00,2.4
			|16670986,AN01706,2017-12-31,11:25:00,2017-12-31,13:14:00,6.1
			|3174961,AN18584,2017-12-31,11:26:11,2018-01-01,12:54:11,24
			|16674334,AN00812,2017-12-31,15:18:00,2018-01-01,14:06:00,6.7
			|3176831,AN24139,2017-12-31,18:25:18,2018-01-01,13:09:18,6.1
			|16673920,AN03984,2017-12-31,14:54:00,2017-12-31,19:19:00,5.6
		""".computeCleanLines

	import spark.implicits._

	"Transform" should "compute the avrg and maximun time per charging station" in {

		val lines=spark.createDataset(data)
//		lines.show()
		val df=spark.read
			.option("header", "true")
			.option("delimiter", ",")
			.schema(schema)
			.csv(lines)

		df.show

		val transformed=SparkChargePoints.transform(df)

		transformed.show()

	}

}
