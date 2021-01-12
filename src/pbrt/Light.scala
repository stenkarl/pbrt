package pbrt

import pbrt.integrator.Spectrum


class Light {

  def preprocess(scene:Scene):Unit = ???

  def le(ray:RayDifferential):Spectrum = ???

}
