package dbmanagment.operation

import utils.caseclass.CaseClassDB.Richiesta

import dbmanagment.operation.ImplicitCrudG.CrudRichiesta
trait RichiestaOperation extends OperationCrud[Richiesta]{

}
object RichiestaOperation extends RichiestaOperation {

}