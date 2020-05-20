package model

import utils.caseclass.CaseClassDB.Zona

trait ZonaModel{
  def getZona(idZona:Int):Option[Zona]
  def getAllZona:List[Zona]
}
object ZonaModel extends ZonaModel {
  override def getZona(idZona: Int): Option[Zona] = ???

  override def getAllZona: List[Zona] = ???
}
