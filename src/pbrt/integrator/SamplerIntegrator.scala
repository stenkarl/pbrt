package pbrt.integrator

import pbrt.{Bounds2i, MemoryArena, Point2i, RayDifferential, Scene}

abstract class SamplerIntegrator(sampler:Sampler, camera:Camera) extends Integrator {

  val sampleBounds = camera.film.getSampleBounds()
  val tileSize = 16

  def li(ray:RayDifferential, scene:Scene, tileSampler:Sampler,
                  arena:MemoryArena, depth:Int = 0):Spectrum

  def preprocess(scene:Scene, sampler:Sampler):Unit = {}

  override def render(scene: Scene): Unit = {
    preprocess(scene, sampler)

    renderTiles(scene)

    camera.film.writeImage()
  }

  def renderTiles(scene:Scene):Unit = {
    //compute number of tiles
    val sampleExtent = sampleBounds.diagonal()

    val nTiles = Point2i((sampleExtent.x + tileSize - 1) / tileSize,
                          (sampleExtent.y + tileSize - 1) / tileSize)

    // render section of image corresponding to tile
    // TODO: where does "tile" come from? A custom iterator on bounds.
    renderTile(Point2i(0, 0), nTiles, scene)

  }

  def renderTile(tile: Point2i, nTiles:Point2i, scene:Scene):Unit = {
    val arena = MemoryArena()
    // get sampler instance for tile
    val seed = tile.y * nTiles.x + tile.x
    val tileSampler = sampler.clone(seed)
    val tileBounds = computeSampleBoundsForTile(tile)
    val filmTile = camera.film.getFilmTile(tileBounds)

    val pixels = for (x <- tileBounds.pMin.x to tileBounds.pMax.x;
         y <- tileBounds.pMin.y to tileBounds.pMax.y) yield Point2i(x, y)

    pixels.foreach { pixel =>
      val cameraSample = tileSampler.getCameraSample(pixel)
      val pair = camera.generateRayDifferential(cameraSample)
      val ray = pair._2
      val rayWeight = pair._1

      ray.scaleDifferentials(1 / Math.sqrt(tileSampler.samplesPerPixel()))
      val l = li(ray, scene, tileSampler, arena)
      filmTile.addSample(cameraSample.pFilm, l, rayWeight)
      arena.reset()
      camera.film.mergeFileTile(filmTile)
    }
  }

  def computeSampleBoundsForTile(tile: Point2i):Bounds2i = {
    val x0 = sampleBounds.pMin.x + tile.x * tileSize
    val x1 = Math.min(x0 + tileSize, sampleBounds.pMax.x)
    val y0 = sampleBounds.pMin.y + tile.y * tileSize
    val y1 = Math.min(y0 + tileSize, sampleBounds.pMax.y)

    Bounds2i(Point2i(x0, y0), Point2i(x1, y1))
  }
}
