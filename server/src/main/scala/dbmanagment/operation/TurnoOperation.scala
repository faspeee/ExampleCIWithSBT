package dbmanagment.operation

import utils.caseclass.CaseClassDB.Turno

import dbmanagment.operation.ImplicitCrudG.CrudTurno
trait TurnoOperation extends OperationCrud[Turno]{

}
object TurnoOperation extends TurnoOperation {

}