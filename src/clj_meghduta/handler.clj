(ns clj-meghduta.handler
  (:require [aleph.http :as http]
            [clj-meghduta.queue :as queue]))

(defn handle-request [req]
  (case [(:request-method req) (:uri req)]
    [:post "/meghduta/queue/push"] (queue/validate-and-push req)
    [:post "/meghduta/queue/pull"] (queue/validate-and-pull req)
    {:status  404
     :body    "Invalid API or command"}))