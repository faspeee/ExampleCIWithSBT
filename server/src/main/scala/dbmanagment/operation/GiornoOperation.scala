package dbmanagment.operation

import utils.caseclass.CaseClassDB.Giorno

import dbmanagment.operation.ImplicitCrudG.CrudGiorno

trait GiornoOperation extends OperationCrud[Giorno]{

}
object GiornoOperation extends GiornoOperation {

}
