package pbrt.integrator
import pbrt.{MemoryArena, RayDifferential, Scene, SurfaceInteraction}

class WhittedIntegrator(maxDepth:Int, camera:Camera, sampler:Sampler) extends SamplerIntegrator(sampler, camera) {

  val isect = new SurfaceInteraction()
  val wo = isect.wo

  override def li(ray: RayDifferential, scene: Scene, tileSampler: Sampler, arena: MemoryArena, depth: Int): Spectrum = {
    // find closest ray intersection or return background radiance
    var spectrum = findClosestRayIntersection(ray, scene)

    computeEmittedAndReflectedLight(ray, arena)

    spectrum = isect.le(wo)

    spectrum
  }

  def findClosestRayIntersection(ray:RayDifferential, scene:Scene):Spectrum = {
    var spectrum = Spectrum()
    if (!scene.intersect(ray, isect)) {
      for (light <- scene.lights) {
        spectrum = spectrum + light.le(ray)
      }
    }
    spectrum
  }

  def computeEmittedAndReflectedLight(ray:RayDifferential, arena: MemoryArena):Spectrum = {
    //val n = isect.shading.n
    isect.computeScatteringFunctions(ray, arena)

    Spectrum()
  }

}
