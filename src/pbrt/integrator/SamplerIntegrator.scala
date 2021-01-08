package pbrt.integrator

import pbrt.Scene

abstract class SamplerIntegrator(sampler:Sampler, camera:Camera) extends Integrator {


  def preprocess(scene:Scene, sampler:Sampler):Unit = {}
}
