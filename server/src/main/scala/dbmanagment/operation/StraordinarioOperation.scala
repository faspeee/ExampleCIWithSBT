package dbmanagment.operation

import utils.caseclass.CaseClassDB.Straordinario

import dbmanagment.operation.ImplicitCrudG.CrudStraordinario
trait StraordinarioOperation extends OperationCrud[Straordinario]{

}
object StraordinarioOperation extends StraordinarioOperation {

}