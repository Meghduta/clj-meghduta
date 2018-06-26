(ns clj-meghduta.core
  (:gen-class)
  (:require [aleph.http :as http]
            [clj-meghduta.handler :as handler]))

(defn -main [& args]
  (println "Meghduta server started")
  (http/start-server handler/handle-request {:port 6600 :compression? true :compression-level 3}))
