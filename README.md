I gave a talk on tools.decompiler at Clojure/Conj in October 2017. Video [here](https://www.youtube.com/watch?v=2SGFeegEt9E)

# Dependencies:

Leiningen:
```clojure
[bronsa/tools.decompiler "0.1.0-alpha1"]
```

# Usage:

Use `lein javac` to AOT compile `clojure.tools.decompiler.RetrieveClasses` then you can use `lein repl` or `clj` to launch a repl

Use `decompile-classfiles `to decompile AOT compiled classes:

```clojure
user=> (require '[clojure.tools.decompiler :as d])
nil
user=> (d/decompile-classfiles {:input-path "path/to/root/classes/directory" :output-path "path/to/src"})
;; with no :output-path, decompile to stdout
[...]
```

You can use `decompile-classes` to decompile in memory classes, but to do so you must start the JVM using the java agent provided with `tools.decompiler` (use e.g. `lein jar` to build the jar):

```clojure
[~/src/tools.decompiler] clj -J-javaagent:tools.decompiler.jar
user=> (require '[clojure.tools.decompiler :as d])
nil
user=> (defn foo [a] a)
#'user/foo
user=> (decompile-classes {:classes #{"user$foo"}}) ;; optionally :output-path to decompile to disk
Decompiling user$foo
(fn foo
  ([a] a)
nil
```
