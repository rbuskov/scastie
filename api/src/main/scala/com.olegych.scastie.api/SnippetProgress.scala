package com.olegych.scastie
package api

object SnippetProgress {
  def default(snippetId: SnippetId) =
    SnippetProgress(
      snippetId = snippetId,
      userOutput = None,
      sbtOutput = None,
      compilationInfos = Nil,
      instrumentations = Nil,
      runtimeError = None,
      scalaJsContent = None,
      scalaJsSourceMapContent = None,
      done = true,
      timeout = false,
      sbtError = false,
      forcedProgramMode = false
    )
}

case class SnippetProgress(
    snippetId: SnippetId,
    userOutput: Option[String],
    sbtOutput: Option[String],
    compilationInfos: List[Problem],
    instrumentations: List[Instrumentation],
    runtimeError: Option[RuntimeError],
    scalaJsContent: Option[String] = None,
    scalaJsSourceMapContent: Option[String] = None,
    done: Boolean,
    timeout: Boolean,
    sbtError: Boolean = false,
    forcedProgramMode: Boolean
)
