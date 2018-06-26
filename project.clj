(defproject clj-meghduta "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [aleph "0.4.6"]
                 [cheshire "5.8.0"]]
  :main ^:skip-aot clj-meghduta.core
  :target-path "target/%s"
  :plugins [[lein-auto "0.1.3"]]
  :global-vars {*warn-on-reflection* true}
  :profiles {:uberjar {:aot :all}})
