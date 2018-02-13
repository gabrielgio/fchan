(defproject fchan "0.1.4"
  :description "A simple 4Chan Api wrapper"
  :url "https://github.com/gabrielgio/fchan"
  :license {:name "MIT License"
            :url "https://raw.githubusercontent.com/gabrielgio/fchan/master/LICENSE"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/data.json "0.2.6"]
                 [clj-http "3.6.1"]]
  :main ^:skip-aot fchan.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
