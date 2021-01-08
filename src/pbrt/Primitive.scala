package pbrt


trait Primitive {
  def worldBound():Bounds3f
  def intersect(ray:Ray, isect:SurfaceInteraction):Boolean
  def intersectP(ray:Ray, isect:SurfaceInteraction):Boolean

}

class Aggregate extends Primitive {
  override def worldBound(): Bounds3f = ???

  override def intersect(ray: Ray, isect: SurfaceInteraction): Boolean = ???

  override def intersectP(ray: Ray, isect: SurfaceInteraction): Boolean = ???
}
