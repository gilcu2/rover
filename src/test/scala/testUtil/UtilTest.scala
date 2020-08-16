package testUtil

object UtilTest  {

  implicit class ExtendedString(s: String) {

    def computeCleanLines: Array[String] = s.stripMargin.split("\n").map(_.trim).filter(_.nonEmpty)

  }

}
