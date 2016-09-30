object legacy {
  trait LowerBound[T] {
    type M >: T;
  }
  trait UpperBound[U] {
    type M <: U;
  }
  trait Upcast[T] {
    type X <: LowerBound[T]
    def compute: X
    final val ub: X = compute
    def upcast(t: T): ub.M = t
  }
  class Coerce[T,U] extends Upcast[T] {
    type X = LowerBound[T] with UpperBound[U]
    override def compute = null
    def coerce(t: T): U = upcast(t)
  }
  def main(args : Array[String]) : Unit = {
    val zero : String = (new Coerce[Int,String]).coerce(0)
    println("...")
  }
}
