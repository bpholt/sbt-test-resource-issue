# sbt Test Resource Loader Issue

Demonstrates [sbt#3515](https://github.com/sbt/sbt/issues/3515).

Run `./demo.sh` and you should see output like that displayed below.

## Individual `sbt` executions

```sbtshell
$ sbt clean; stty echo
[info] Loading settings from idea.sbt ...
[info] Loading global plugins from ~/.sbt/1.0/plugins
[info] Loading project definition from ~/sbt-issue/project
[info] Loading settings from build.sbt ...
[info] Set current project to test-resources (in build file:~/sbt-issue/)
[success] Total time: 0 s, completed Sep 11, 2017 1:59:43 PM

$ sbt test; stty echo
[info] Loading settings from idea.sbt ...
[info] Loading global plugins from ~/.sbt/1.0/plugins
[info] Loading project definition from ~/sbt-issue/project
[info] Loading settings from build.sbt ...
[info] Set current project to test-resources (in build file:~/sbt-issue/)
[info] Updating {file:~/sbt-issue/}app...
[info] Done updating.
[info] Compiling 1 Scala source to ~/sbt-issue/target/scala-2.12/test-classes ...
[info] Done compiling.
[info] TestLoadingFileSpec
[info] classloader should
[error]   ! be able to load a test resource
[error]    java.lang.NullPointerException: null (TestLoadingFileSpec.scala:9)
[error] TestLoadingFileSpec$Setup.$init$(TestLoadingFileSpec.scala:9)
[error] TestLoadingFileSpec$$anon$1.<init>(TestLoadingFileSpec.scala:13)
[error] TestLoadingFileSpec.$anonfun$new$2(TestLoadingFileSpec.scala:13)
[info] Total for specification TestLoadingFileSpec
[info] Finished in 218 ms
[info] 1 example, 0 failure, 1 error
[error] Error: Total 1, Failed 0, Errors 1, Passed 0
[error] Error during tests:
[error] 	TestLoadingFileSpec
[error] (test:test) sbt.TestsFailedException: Tests unsuccessful
[error] Total time: 4 s, completed Sep 11, 2017 1:59:59 PM

$ sbt test; stty echo
[info] Loading settings from idea.sbt ...
[info] Loading global plugins from ~/.sbt/1.0/plugins
[info] Loading project definition from ~/sbt-issue/project
[info] Loading settings from build.sbt ...
[info] Set current project to test-resources (in build file:~/sbt-issue/)
[info] TestLoadingFileSpec
[info] classloader should
[info]   + be able to load a test resource
[info] Total for specification TestLoadingFileSpec
[info] Finished in 167 ms
[info] 1 example, 0 failure, 0 error
[info] Passed: Total 1, Failed 0, Errors 0, Passed 1
[success] Total time: 2 s, completed Sep 11, 2017 2:00:11 PM
```

## `sbt` Interactive Shell 

```sbtshell
$ sbt
[info] Loading settings from idea.sbt ...
[info] Loading global plugins from ~/.sbt/1.0/plugins
[info] Loading project definition from ~/sbt-issue/project
[info] Loading settings from build.sbt ...
[info] Set current project to test-resources (in build file:~/sbt-issue/)
[info] sbt server started at 127.0.0.1:5216

sbt:test-resources> clean
[success] Total time: 0 s, completed Sep 11, 2017 2:08:40 PM

sbt:test-resources> test
[info] Updating {file:~/sbt-issue/}app...
[info] Done updating.
[info] Compiling 1 Scala source to ~/sbt-issue/target/scala-2.12/test-classes ...
[info] Done compiling.
[info] ExampleSpec
[error] ! Load a file from test resources
[error]  java.lang.NullPointerException: null (ExampleSpec.scala:9)
[error] ExampleSpec$Setup.$init$(ExampleSpec.scala:9)
[error] ExampleSpec$$anon$1.<init>(ExampleSpec.scala:12)
[error] ExampleSpec.$anonfun$new$1(ExampleSpec.scala:12)
[info] Total for specification ExampleSpec
[info] Finished in 136 ms
[info] 1 example, 0 failure, 1 error
[error] Error: Total 1, Failed 0, Errors 1, Passed 0
[error] Error during tests:
[error] 	ExampleSpec
[error] (test:test) sbt.TestsFailedException: Tests unsuccessful
[error] Total time: 4 s, completed Sep 11, 2017 2:08:45 PM

sbt:test-resources> test
[info] ExampleSpec
[error] ! Load a file from test resources
[error]  java.lang.NullPointerException: null (ExampleSpec.scala:9)
[error] ExampleSpec$Setup.$init$(ExampleSpec.scala:9)
[error] ExampleSpec$$anon$1.<init>(ExampleSpec.scala:12)
[error] ExampleSpec.$anonfun$new$1(ExampleSpec.scala:12)
[info] Total for specification ExampleSpec
[info] Finished in 201 ms
[info] 1 example, 0 failure, 1 error
[error] Error: Total 1, Failed 0, Errors 1, Passed 0
[error] Error during tests:
[error] 	ExampleSpec
[error] (test:test) sbt.TestsFailedException: Tests unsuccessful
[error] Total time: 1 s, completed Sep 11, 2017 2:08:48 PM

sbt:test-resources> reload
[info] Loading settings from idea.sbt ...
[info] Loading global plugins from ~/.sbt/1.0/plugins
[info] Loading project definition from ~/sbt-issue/project
[info] Loading settings from build.sbt ...
[info] Set current project to test-resources (in build file:~/sbt-issue/)

sbt:test-resources> test
[info] ExampleSpec
[error] ! Load a file from test resources
[error]  java.lang.NullPointerException: null (ExampleSpec.scala:9)
[error] ExampleSpec$Setup.$init$(ExampleSpec.scala:9)
[error] ExampleSpec$$anon$1.<init>(ExampleSpec.scala:12)
[error] ExampleSpec.$anonfun$new$1(ExampleSpec.scala:12)
[info] Total for specification ExampleSpec
[info] Finished in 127 ms
[info] 1 example, 0 failure, 1 error
[error] Error: Total 1, Failed 0, Errors 1, Passed 0
[error] Error during tests:
[error] 	ExampleSpec
[error] (test:test) sbt.TestsFailedException: Tests unsuccessful
[error] Total time: 1 s, completed Sep 11, 2017 2:08:53 PM

sbt:test-resources> exit

$ sbt
[info] Loading settings from idea.sbt ...
[info] Loading global plugins from ~/.sbt/1.0/plugins
[info] Loading project definition from ~/sbt-issue/project
[info] Loading settings from build.sbt ...
[info] Set current project to test-resources (in build file:~/sbt-issue/)
[info] sbt server started at 127.0.0.1:5216

sbt:test-resources> test
[info] ExampleSpec
[info] + Load a file from test resources
[info] Total for specification ExampleSpec
[info] Finished in 141 ms
[info] 1 example, 0 failure, 0 error
[info] Passed: Total 1, Failed 0, Errors 0, Passed 1
[success] Total time: 2 s, completed Sep 11, 2017 2:09:06 PM
```
