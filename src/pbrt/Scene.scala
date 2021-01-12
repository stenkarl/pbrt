package pbrt


class Scene(aggregate:Primitive, val lights:List[Light]) {

  private val worldBound = aggregate.worldBound()

  lights.foreach(_.preprocess(this))

  def intersect(ray:RayDifferential, isect:SurfaceInteraction):Boolean = {
    aggregate.intersect(ray, isect)
  }

  def intersectP(ray:Ray, isect:SurfaceInteraction):Boolean = {
    aggregate.intersectP(ray, isect)
  }

}
