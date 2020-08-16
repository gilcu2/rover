package avira

import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import org.apache.spark.sql.{DataFrame, SparkSession}

object SparkChargePoints {

	val input = "data/input/electric-chargepoints-2017.csv"
	val output = "data/output/chargepoints-2017-analysis"

	val event = "CharguingEvent"
	val cpid = "CPID"
	val startDate = "StartDate"
	val startTime = "StartTime"
	val endDate = "EndDate"
	val endTime = "EndTime"
	val energy = "Energy"
	val end = "End"
	val start = "Start"
	val seconds = "Seconds"
	val hours="Hours"

	val schema = StructType(Array(
		StructField(event, IntegerType, false),
		StructField(cpid, StringType, false),
		StructField(startDate, StringType, false),
		StructField(startTime, StringType, false),
		StructField(endDate, StringType, false),
		StructField(endTime, StringType, false),
		StructField(energy, DoubleType, false)
	))

	val spark = SparkSession.builder
		.master("local[*]")
		.appName("SparkChargePoints")
		.getOrCreate()

	def main(args: Array[String]): Unit = {
		load(transform(extract()))
	}

	def extract(): DataFrame = {
		spark.read.option("header", "true")
			.option("delimiter", ",")
			.schema(schema)
			.csv(input)
	}

	def transform(df: DataFrame): DataFrame = {
		val withTimeStamp=df.withColumn(end, to_timestamp(concat(col(endDate),lit(" "), col(endTime))))
			.withColumn(start, to_timestamp(concat(col(startDate),lit(" "), col(startTime))))
		val withDifferenceHours=withTimeStamp
			.withColumn(seconds
				,col(end).cast(LongType)-col(start).cast(LongType))
			.withColumn(hours,round(col(seconds)/3600))
		withDifferenceHours.groupBy(cpid).agg(max(hours),avg(hours))
	}

	def load(df: DataFrame): DataFrame = {
		???
	}
}


//// Avr and maximun times in hours
//
//SELECT a.name from phones a
//join (select caller,sum(duration) as sumd from calls group by caller ) b
//on a.phone_number=b.caller
//where b.sumd>=10
//union
//(
//SELECT d.name from phones d
//join (select callee,sum(duration) as sume from calls group by callee ) c
//on d.phone_number=c.callee
//where c.sume>=10
//order by d.name
//)
//
//insert into phones values ('Jack', 1234);
//insert into phones values ('Lena', 3333);
//insert into phones values ('Mark', 9999);
//insert into phones values ('Anna', 7582);
//insert into calls values (25, 1234, 7582, 8);
//insert into calls values (7, 9999, 7582, 1);
//insert into calls values (18, 9999, 3333, 4);
//insert into calls values (2, 7582, 3333, 3);
//insert into calls values (3, 3333, 1234, 1);
//insert into calls values (21, 3333, 1234, 1);
