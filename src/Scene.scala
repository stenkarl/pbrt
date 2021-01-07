
class Scene(aggregate:Primitive, lights:List[Light]) {

  private val worldBound = aggregate.worldBound()

  lights.foreach(_.preprocess(this))

}
