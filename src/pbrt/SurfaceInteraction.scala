package pbrt

import pbrt.integrator.Spectrum


class SurfaceInteraction {

  val wo = Vector3f()

  def computeScatteringFunctions(ray:RayDifferential, arena:MemoryArena):Unit = ???

  def le(wo:Vector3f):Spectrum = ???
}
