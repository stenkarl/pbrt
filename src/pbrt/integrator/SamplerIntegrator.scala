package pbrt.integrator

import pbrt.{Point2i, Scene}

class SamplerIntegrator(sampler:Sampler, camera:Camera) extends Integrator {


  def preprocess(scene:Scene, sampler:Sampler):Unit = {}

  override def render(scene: Scene): Unit = {
    preprocess(scene, sampler)

    renderTiles()

    // save final image
  }

  def renderTiles():Unit = {
    //compute number of tiles
    val sampleBounds = camera.film.getSampleBounds()
    val sampleExtent = sampleBounds.diagonal()
    val tileSize = 16

    val nTiles = Point2i((sampleExtent.x + tileSize - 1) / tileSize,
                          (sampleExtent.y + tileSize - 1) / tileSize)

    // render section of image corresponding to tile
  }
}
