package com.augustnagro.magnum

import javax.sql.DataSource

/** A read & write data repository
  *
  * @tparam EC
  *   'Entity Creator', which should have all fields of E minus those
  *   auto-generated by the database. Can be the same type as E.
  * @tparam E
  *   database entity class
  * @tparam ID
  *   id type of E
  */
open class Repo[EC, E, ID](using defaults: RepoDefaults[EC, E, ID])
    extends ImmutableRepo[E, ID]:

  /** Deletes an entity using its id */
  def delete(entity: E)(using DbCon): Unit = defaults.delete(entity)

  /** Deletes an entity using its id */
  def deleteById(id: ID)(using DbCon): Unit = defaults.deleteById(id)

  /** Deletes ALL entities */
  def truncate()(using DbCon): Unit = defaults.truncate()

  /** Delete all provided entities */
  def deleteAll(entities: Iterable[E])(using DbCon): BatchUpdateResult =
    defaults.deleteAll(entities)

  /** Deletes all entities with an Iterable of ids */
  def deleteAllById(ids: Iterable[ID])(using DbCon): BatchUpdateResult =
    defaults.deleteAllById(ids)

  /** Insert and return entity E */
  def insert(entityCreator: EC)(using DbCon): Unit =
    defaults.insert(entityCreator)

  /** Insert and return all new entities */
  def insertAll(entityCreators: Iterable[EC])(using DbCon): Unit =
    defaults.insertAll(entityCreators)

  def insertReturning(entityCreator: EC)(using DbCon): E =
    defaults.insertReturning(entityCreator)

  def insertAllReturning(entityCreators: Iterable[EC])(using DbCon): Vector[E] =
    defaults.insertAllReturning(entityCreators)

  /** Update the entity */
  def update(entity: E)(using DbCon): Unit = defaults.update(entity)

  /** Update all entities */
  def updateAll(entities: Iterable[E])(using DbCon): BatchUpdateResult =
    defaults.updateAll(entities)

end Repo
