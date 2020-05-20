package model

trait GenericModel {
  protected val dispatcher = ModelDispatcher()

  protected implicit val system = dispatcher.system

}

