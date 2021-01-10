package pbrt.integrator

import pbrt.Bounds2i

class Film {

  def getSampleBounds():Bounds2i = ???

  def getFilmTile(tileBounds:Bounds2i):FilmTile = ???

  def mergeFileTile(filmTile:FilmTile):Unit = ???

  def writeImage():Unit = ???

}
