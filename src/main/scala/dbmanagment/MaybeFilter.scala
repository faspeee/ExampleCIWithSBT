package dbmanagment
import slick.jdbc.SQLServerProfile.api._
import dbmanagment.CaseClassDB.Zona
import dbmanagment.ZonaTable.ZonaTableRep
import dbmanagment.implicitsGeneric.Brands

object t extends App{

  class tt extends Brands[Zona,ZonaTableRep]{
    def f1(x: ZonaTableRep) = x.id === 1 && x.Zone===""
    table.filter(f1)

  }
  new tt()
  while(true){}
}
