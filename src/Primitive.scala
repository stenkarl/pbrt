
trait Primitive {
  def worldBound():Bounds3f
}

class Aggregate extends Primitive {
  override def worldBound(): Bounds3f = ???
}
