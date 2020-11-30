package usecases

import entities.Entity
import gateways.Gateway

abstract class UseCase[T <: Entity] {
  def gateway: Gateway[T]
  def apply(): Unit
}
