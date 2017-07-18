;;   Copyright (c) Nicola Mometto & contributors.
;;   The use and distribution terms for this software are covered by the
;;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;;   which can be found in the file epl-v10.html at the root of this distribution.
;;   By using this software in any fashion, you are agreeing to be bound by
;;   the terms of this license.
;;   You must not remove this notice, or any other, from this software.

(ns clojure.tools.decompiler.utils
  (:require [clojure.string :as s])
  (:import clojure.lang.Compiler
           java.io.Writer))

(defn ungensym [s]
  (s/replace s #"(__[0-9]+)" ""))

(defn demunge [s]
  (symbol (Compiler/demunge s)))

(defn find-methods [methods matches]
  (for [method methods
        :when (= matches (select-keys method (keys matches)))]
    method))

(defn find-method [methods matches]
  ;; assert just 1
  (first (find-methods methods matches)))

(defmethod print-method (Class/forName "[Ljava.lang.Object;") [o w]
  (.write w "#array")
  (.write w " ")
  (print-method (vec o) w))
