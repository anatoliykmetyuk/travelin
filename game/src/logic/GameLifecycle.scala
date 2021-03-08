package game
package logic

trait GameLifecycle:
  this: Game =>
  private var phase: GamePhase = GamePhase.Setup

  def action(requiredPhase: GamePhase)(task: => GamePhase) =
    if phase != requiredPhase then
      error(s"This action can only be done during the $requiredPhase of the game")  // TODO use Sourcecode to help trace method names
    else phase = task

  def error(text: String) = throw RuntimeException(text)
