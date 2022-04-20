package u06lab.solution

/** 1) Implement trait Functions with an object FunctionsImpl such that the code in TryFunctions works correctly. */

trait Functions:
  def sum(a: List[Double]): Double
  def concat(a: Seq[String]): String
  def max(a: List[Int]): Int // gives Int.MinValue if a is empty
  def combine[A](a: List[A], combiner: Combiner[A]): A

object FunctionsImpl extends Functions:
  val sumCombiner: Combiner[Double] = new Combiner[Double] {
    override def unit: Double = 0.0
    override def combine(a: Double, b: Double): Double = a+b
  }

  val concatCombiner: Combiner[String] = new Combiner[String] {
    override def unit: String = ""
    override def combine(a: String, b: String): String = a.concat(b)
  }

  val maxCombiner: Combiner[Int] = new Combiner[Int] {
    override def unit: Int = Int.MinValue
    override def combine(a: Int, b: Int): Int = if a < b then b else a
  }
  
  override def concat(a: Seq[String]): String = combine(a.toList, concatCombiner)
  override def max(a: List[Int]): Int = combine(a, maxCombiner)
  override def sum(a: List[Double]): Double = combine(a, sumCombiner)
  override def combine[A](a: List[A], combiner: Combiner[A]): A =
    if a.isEmpty then
      combiner.unit
    else
      combiner.combine(a.head, combine(a.tail, combiner))


trait Combiner[A]:
  def unit: A
  def combine(a: A, b: A): A

