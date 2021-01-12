package pbrt.integrator
import pbrt.{MemoryArena, RayDifferential, Scene, SurfaceInteraction}

class WhittedIntegrator(maxDepth:Int, camera:Camera, sampler:Sampler) extends SamplerIntegrator(sampler, camera) {

  val isect = new SurfaceInteraction()
  val wo = isect.wo

  override def li(ray: RayDifferential, scene: Scene, tileSampler: Sampler, arena: MemoryArena, depth: Int): Spectrum = {
    // find closest ray intersection or return background radiance
    if (!scene.intersect(ray, isect)) {
      for (light <- scene.lights) {
        val spectrum = light.le(ray)
      }
    }

    computeEmittedAndReflectedLight()
  }

  def computeEmittedAndReflectedLight():Spectrum = {
    //val n = isect.shading.n

    Spectrum()
  }

}
