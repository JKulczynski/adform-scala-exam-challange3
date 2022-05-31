import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.col

object Farm extends App {

  val spark = SparkSession.builder()
    .appName("Farm")
    .master("local[*]")
    .getOrCreate()

  val harvest = "harvest.csv"
  val prices = "prices.csv"

  val show_harvest = spark.read.option("header", "true").csv(harvest)
  // the best gatherer is John with amount:1.04 fruit: apples

  val show_prices = spark.read.option("header", "true").csv(prices)
  //the most value fruit is strawberries with price 1305.52


  show_harvest.join(show_prices).where(show_harvest("fruit") === show_prices("fruit"))
    .orderBy(col("price").desc)
    .orderBy(col("amount").asc)
    .show(false)
  //John is the gatherer who contribute the most to the farm
}
