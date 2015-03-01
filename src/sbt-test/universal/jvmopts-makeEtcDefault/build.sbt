enablePlugins(JavaServerAppPackaging)

name := "simple-test"

version := "0.1.0"

javaOptions in Linux ++= Seq(
  "-J-Xmx64m", "-J-Xms64m", "-Dproperty=true"
)

TaskKey[Unit]("check") := {
  val etc = (target in Universal).value / "tmp" / "etc" / "default" / name.value
  val content = IO.read(etc)
  val options = (javaOptions in Linux).value
  options.foreach { opt => 
    assert(content.contains(opt), "Option [" + opt + "] is missing")
  }
}
