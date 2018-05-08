/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.s2graph.core

import org.apache.s2graph.core.storage.{MutateResponse, SKeyValue}

import scala.concurrent.{ExecutionContext, Future}

trait Mutator {
  def mutateVertex(zkQuorum: String, vertex: S2VertexLike, withWait: Boolean)(implicit ec: ExecutionContext): Future[MutateResponse]

  def mutateStrongEdges(zkQuorum: String, _edges: Seq[S2EdgeLike], withWait: Boolean)(implicit ec: ExecutionContext): Future[Seq[Boolean]]

  def mutateWeakEdges(zkQuorum: String, _edges: Seq[S2EdgeLike], withWait: Boolean)(implicit ec: ExecutionContext): Future[Seq[(Int, Boolean)]]

  def incrementCounts(zkQuorum: String, edges: Seq[S2EdgeLike], withWait: Boolean)(implicit ec: ExecutionContext): Future[Seq[MutateResponse]]

  def updateDegree(zkQuorum: String, edge: S2EdgeLike, degreeVal: Long = 0)(implicit ec: ExecutionContext): Future[MutateResponse]

  def deleteAllFetchedEdgesAsyncOld(stepInnerResult: StepResult,
                                    requestTs: Long,
                                    retryNum: Int)(implicit ec: ExecutionContext): Future[Boolean]
}

