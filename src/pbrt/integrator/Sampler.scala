package pbrt.integrator

import pbrt.Point2i

class Sampler(val seed:Int) {

  def clone(seed:Int):Sampler = new Sampler(seed)

  def getCameraSample(pixel:Point2i):CameraSample = ???

  def samplesPerPixel():Int = ???

}
