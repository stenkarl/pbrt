package pbrt.integrator

import pbrt.Scene

trait Integrator {

  def render(scene: Scene): Unit

}
