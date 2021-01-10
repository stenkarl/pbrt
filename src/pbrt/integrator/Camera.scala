package pbrt.integrator

import pbrt.RayDifferential

class Camera (val film: Film) {

  def generateRayDifferential(cameraSample:CameraSample):(Double, RayDifferential) = ???

}
